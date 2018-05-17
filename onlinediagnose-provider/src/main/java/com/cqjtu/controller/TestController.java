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


    @RequestMapping(value ={"/","test"})
    public Message test(){
        return  new Message().put("时间：",new Date());
    }


}
