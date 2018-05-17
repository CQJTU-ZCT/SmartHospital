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
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        log.error("this is a ErrorFilter : {}", throwable.getCause().getMessage());

        //过滤该请求，不往下级服务去转发请求，到此结束
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(ctx.getResponse().getStatus());
        Message message  = new ExceptionMessage();
        message.setCode(500);
        message.setInfo("没有相关服务或服务正忙...");
        ctx.setResponseBody(message.toString());
        return null;
    }
}
