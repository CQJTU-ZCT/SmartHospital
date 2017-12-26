package com.cqjtu.filter;

import com.cqjtu.messages.ValidateMessage;
import com.cqjtu.tools.JSONUtil;

import com.cqjtu.tools.ServerInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/26.
 */
public class ValidateFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return;
        }

        String token = null;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("token")){
                token = cookie.getValue();
                break;
            }
        }

        //本次请求的完整路径
        String originalUrl = request.getRequestURL().toString();
        String method = request.getMethod();
        String queryString = request.getQueryString();

        if (queryString!=null){
            originalUrl += queryString;
        }


        if (token == null){
            //token不存在
            response.sendRedirect(ServerInfo.getLoginAddress()+"/"+ URLEncoder.encode(originalUrl,"UTF-8"));
        }else {
            //token存在
            URL url = new URL(ServerInfo.getValidateAddress()+"/"+token);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                //验证服务正常
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = reader.readLine();
                StringBuilder resultBuilder = new StringBuilder();
                while (line != null){
                    resultBuilder.append(line);
                    line =  reader.readLine();
                }
                JSONObject object = new JSONObject(resultBuilder.toString());
                ValidateMessage message = (ValidateMessage) JSONUtil.praseJsonToBean(object, ValidateMessage.class);
                if (message.getCode() != 1){
                    //如果token是失效的，就 重定向 去登录地址
                    response.sendRedirect(ServerInfo.getLoginAddress()+"/"+ URLEncoder.encode(originalUrl,"utf-8"));
                }else {
                    //token是有效的
                    request.setAttribute("token",token);
                    request.setAttribute("user",message.getUser());
                    filterChain.doFilter(request,response);
                }
            }else {
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
                //如果验证服务出错，比如验证服务器宕机之类，就直接 重定向 去登录
                response.sendRedirect(ServerInfo.getLoginAddress()+"/"+URLEncoder.encode(originalUrl,"utf-8"));
            }

        }



    }

    @Override
    public void destroy() {

    }
}
