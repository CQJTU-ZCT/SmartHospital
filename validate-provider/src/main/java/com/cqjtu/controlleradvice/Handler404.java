package com.cqjtu.controlleradvice;

import com.cqjtu.messages.FzfMessage;
import com.cqjtu.messages.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zjhfyq
 * @Desc 统一处理404
 * @date 2017/12/8.
 */
@ControllerAdvice
public class Handler404 {


    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public Message handler404(HttpServletRequest request , Exception e){
        FzfMessage message = FzfMessage.get404Message();
        message.setInfo(e.getMessage());
        return message;
    }


}
