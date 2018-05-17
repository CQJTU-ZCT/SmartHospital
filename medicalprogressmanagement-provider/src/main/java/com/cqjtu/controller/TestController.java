package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@RestController
public class TestController {


    @RequestMapping(value = {"/","/1","/test"})
    public Message get(){
        Message message = new Message();
        message.put("appName","医疗进度管理");
        message.put("date",new Date().toString());
        return message;
    }
}
