package com.cqjtu.filter;


import com.cqjtu.messages.FilterMessage;
import com.cqjtu.messages.Message;
import com.cqjtu.messages.ValidateMessage;
import com.cqjtu.model.Users;
import com.cqjtu.tools.JsonUtil;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.ServerInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/26.
 */
@WebFilter(filterName = "AuthenticationFilter",urlPatterns = "/*")
public class ValidateFilterWithRestAndRibbon implements Filter{

    private int targetResponseCode = 200;


    private  String originalUrlTag ="originalUrl";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private  ServerInfo serverInfo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @HystrixCommand(fallbackMethod = "validateFailed",ignoreExceptions = {java.io.FileNotFoundException.class})
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        Message responseMessage;

        //先从头部获取token
        String token = request.getHeader("token");
        if (token == null){
            //如果头部没有，则尝试从查询参数中获取token
            try{
                token = (String) request.getParameter("token");
            }catch (Exception e){
                LoggerTool.getLogger(this.getClass()).info("request请求参数中的token并不是一个令牌");
            }
        }
        if (token == null ||token.length()<=0){
            LoggerTool.getLogger(this.getClass()).info("token不存在 2");
            //token不存在
            responseMessage =  FilterMessage.getNoTokenMessage();
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(new String(JsonUtil.praseBeanToJson(responseMessage).getBytes(),"UTF-8"));
        }else {
            //token存在
            String result  = restTemplate.getForEntity(serverInfo.getValidateService() + "?token=" + token, String.class).getBody();
            Message message = (Message) JsonUtil.praseJsonToBean(result,ValidateMessage.class);
            if (message.getCode() != 1){
                LoggerTool.getLogger(this.getClass()).info("token无效");
                //如果token是失效的
                responseMessage =  FilterMessage.getTokenIllegalMessage();
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(new String(JsonUtil.praseBeanToJson(responseMessage).getBytes(),"UTF-8"));
            }else {
                LoggerTool.getLogger(this.getClass()).info("token有效");
                //token是有效的
                request.setAttribute("token",token);
                request.setAttribute("user",JsonUtil.praseJsonToBean(message.getMap().get("user").toString(),Users.class));
                filterChain.doFilter(request,response);
            }
        }


    }

    @Override
    public void destroy() {

    }


    private void validateFailed(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        LoggerTool.getLogger(this.getClass()).info("验证服务出错");
        //如果token是失效的
        FilterMessage responseMessage =  FilterMessage.getErrorMessage();
        servletResponse.setContentType("application/json;charset=utf-8");
        try {
            servletResponse.getWriter().print(new String(JsonUtil.praseBeanToJson(responseMessage).getBytes(),"UTF-8"));
        } catch (IOException e) {
            LoggerTool.getLogger(this.getClass()).info("验证服务响应出错");
        }
    }


}
