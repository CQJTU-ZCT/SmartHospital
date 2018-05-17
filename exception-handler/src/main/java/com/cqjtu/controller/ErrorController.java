package com.cqjtu.controller;


import com.cqjtu.messages.ExceptionMessage;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/19.
 */
@RestController
public class ErrorController extends AbstractErrorController {

    private Logger logger = LoggerFactory.getLogger(ErrorController.class);

    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Message error(HttpServletRequest request,Exception exception) {
        logger.info(request.getRemoteAddr() + " " + request.getMethod() + " " + request.getRequestURL()+ " " +getStatus(request));
        Message message  = new ExceptionMessage();
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.BAD_REQUEST){
            message.setCode(400);
            message.setInfo("请求错误");
        }else if (status == HttpStatus.NOT_FOUND){
            message.setCode(404);
            message.setInfo("请求资源不存在");
        }else if(status == HttpStatus.BAD_GATEWAY){
            message.setCode(502);
            message.setInfo("网关错误");
        }else if (status == HttpStatus.UNAUTHORIZED){
            message.setCode(401);
            message.setInfo("未授权");
        }else if (status == HttpStatus.FORBIDDEN){
            message.setCode(403);
            message.setInfo("未授权");
        }else if (status == HttpStatus.INTERNAL_SERVER_ERROR){
            message.setCode(500);
            message.setInfo("没有相关服务或服务正忙");
        }

        return message;
    }
}
