package com.cqjtu.controller;


import com.cqjtu.messages.ExceptionMessage;
import com.cqjtu.messages.Message;
import com.cqjtu.tools.LoggerTool;
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


    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Message error(HttpServletRequest request,Exception exception) {
        Message message  = new ExceptionMessage();;
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
        }else {
            message.setCode(503);
            message.setInfo("服务器忙，请稍后再试...");
        }
        LoggerTool.getLogger(this.getClass()).info(exception.getMessage());
        return message;
    }
}
