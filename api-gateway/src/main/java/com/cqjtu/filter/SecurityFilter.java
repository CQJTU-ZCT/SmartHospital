package com.cqjtu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @author zjhfyq
 * @Desc  网关前置过滤，为通过网关请求provider的加上安全认证的请求头
 * @date 2017/12/11.
 */
@Component
public class SecurityFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        String auth = "test:test";
        //加密处理
        byte[] bytes = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        //注意"Basic "后面追加一个空格
        String authResult = "Basic "+new String(bytes);
        context.addZuulRequestHeader("Authorization",authResult);
        return null;
    }
}
