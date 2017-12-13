package com.cqjtu.controller;

import com.cqjtu.messages.ExceptionMessage;
import com.cqjtu.messages.Message;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc 异常控制器
 * @date 2017/12/11.
 */
@RestController
@RequestMapping("/error")
public class ErrorController extends AbstractErrorController{

    @Autowired
    public ErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public Message error(HttpServletRequest request) {
        Message message  = new ExceptionMessage();;
        HttpStatus status = getStatus(request);
        if (status == HttpStatus.BAD_REQUEST){
            message.setCode(400);
            message.setInfo("请求错误");
        }else if(status == HttpStatus.NOT_FOUND){
            message.setCode(404);
            message.setInfo("请求路径不存在");
        }else if(status == HttpStatus.BAD_GATEWAY){
            message.setCode(502);
            message.setInfo("网关错误");
        }else{
            message.setCode(110);
            message.setInfo("未知错误，请稍后再试...");
        }
        return message;
    }

}
