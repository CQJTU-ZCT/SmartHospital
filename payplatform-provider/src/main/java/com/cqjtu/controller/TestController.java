package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@RestController
public class TestController {

    @RequestMapping(value= {"/{username}","/"})
    public Message test(@PathVariable("username")String username){
        Message message = new Message();
        message.put("username",username);
        return message;
    }
}
