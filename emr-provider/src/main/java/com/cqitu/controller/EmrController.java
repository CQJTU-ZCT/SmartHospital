package com.cqitu.controller;

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
public class EmrController {

    @RequestMapping(value = {"/getEmrByUsername/{username}"})
    public Message getEmrByUsername(@PathVariable(value = "username")String username){
        return new Message().put("username",username);
    }


}
