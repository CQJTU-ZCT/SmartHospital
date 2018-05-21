package com.cqjtu.filter;

import com.cqjtu.messages.ExceptionMessage;
import com.cqjtu.messages.Message;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/4/8.
 */
public class ErrorFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
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
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(ctx.getResponse().getStatus());
        Message message  = new ExceptionMessage();
        message.setCode(500);
        message.setInfo("没有相关服务或服务正忙...");


        ctx.setResponseBody(message.toString());
        ctx.sendZuulResponse();
        return null;
    }
}
