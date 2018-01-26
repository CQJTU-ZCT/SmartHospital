package com.cqjtu.filter;

import com.cqjtu.messages.FilterMessage;
import com.cqjtu.messages.Message;
import com.cqjtu.messages.ValidateMessage;
import com.cqjtu.model.Users;
import com.cqjtu.tools.JsonUtil;

import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.ServerInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/26.
 */
@WebFilter(filterName = "AuthenticationFilter",urlPatterns = "/*")
public class ValidateFilter implements Filter{

    private int targetResponseCode = 200;


    private  String originalUrlTag ="originalUrl";

    @Autowired
    private  ServerInfo serverInfo;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

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
                token = (String) request.getAttribute("token");
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
            //response.getOutputStream().write(JsonUtil.praseBeanToJson(responseMessage).getBytes("UTF-8"));
        }else {
            //token存在
            URL url = new URL(serverInfo.getValidateAddress()+"/"+token);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == targetResponseCode){
                //验证服务正常
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = reader.readLine();
                StringBuilder resultBuilder = new StringBuilder();
                while (line != null){
                    resultBuilder.append(line);
                    line =  reader.readLine();
                }
                Message message =  (Message) JsonUtil.praseJsonToBean(resultBuilder.toString(), Message.class);

                if (message.getCode() != 1){
                    LoggerTool.getLogger(this.getClass()).info("token无效");
                    //如果token是失效的
                    responseMessage =  FilterMessage.getTokenIllegalMessage();
                    response.setContentType("application/json;charset=utf-8");
                    response.getWriter().print(new String(JsonUtil.praseBeanToJson(responseMessage).getBytes(),"UTF-8"));
                    //response.getOutputStream().write(JsonUtil.praseBeanToJson(responseMessage).getBytes("UTF-8"));
                }else {
                    LoggerTool.getLogger(this.getClass()).info("token有效");
                    //token是有效的
                    request.setAttribute("token",token);
                    request.setAttribute("user",JsonUtil.praseJsonToBean(message.getMap().get("user").toString(), Users.class));
                    filterChain.doFilter(request,response);
                }
            }else {
                LoggerTool.getLogger(this.getClass()).info("token验证服务器出错");
                //验证服务出错
                InputStream errorStream = connection.getErrorStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
                String line = reader.readLine();
                StringBuilder resultBulider = new StringBuilder();
                while (line != null){
                    resultBulider.append(line);
                    line = reader.readLine();
                }
                Log log = LogFactory.getLog(this.getClass());
                log.info("验证服务时服务出错："+resultBulider.toString());
                //如果验证服务出错，比如验证服务器宕机之类
                responseMessage =  FilterMessage.getTimeoutMessage();
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().print(new String(JsonUtil.praseBeanToJson(responseMessage).getBytes(),"UTF-8"));
                //response.getOutputStream().write(JsonUtil.praseBeanToJson(responseMessage).getBytes("UTF-8"));
            }
        }


    }

    @Override
    public void destroy() {

    }
}
