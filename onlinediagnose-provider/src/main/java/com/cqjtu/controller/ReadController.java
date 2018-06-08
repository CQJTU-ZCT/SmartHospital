package com.cqjtu.controller;

import com.cqjtu.domain.DoctorOnline;
import com.cqjtu.messages.Message;
import com.cqjtu.websocket.ChatWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-08 01:39
 **/

@Controller
public class ReadController {


    @RequestMapping(value = {"/doctor","/doctor/"},method = RequestMethod.GET)
    public Message NumOfDoctorOnline(){
        Message message = new Message();

        List<DoctorOnline> doctors = ChatWebSocketHandler.getDoctors();
        message.put("doctors",doctors);
        return  message;
    }

}
