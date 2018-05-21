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
//@Component
public class CorsFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        System.out.println("post filter");
        context.addZuulRequestHeader("Access-Control-Allow-Origin","*");
        context.addZuulRequestHeader("Access-Control-Allow-Methods","*");
        context.addZuulRequestHeader("Access-Control-Allow-Headers","*");


        context.addZuulResponseHeader("Access-Control-Allow-Origin","*");
        context.addZuulResponseHeader("Access-Control-Allow-Methods","*");
        context.addZuulResponseHeader("Access-Control-Allow-Headers","*");


        context.addOriginResponseHeader("Access-Control-Allow-Origin","*");
        context.addOriginResponseHeader("Access-Control-Allow-Methods","*");
        context.addOriginResponseHeader("Access-Control-Allow-Headers","*");
        return null;
    }
}
