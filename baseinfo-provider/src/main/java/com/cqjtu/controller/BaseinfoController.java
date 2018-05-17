package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Nation;
import com.cqjtu.model.Sex;
import com.cqjtu.service.NationService;
import com.cqjtu.service.NationServiceImpl;
import com.cqjtu.service.SexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc 基础信息控制器
 * @date 2018/2/6.
 */
@RestController
@RequestMapping("/baseinfo")
public class BaseinfoController {

    @Autowired
    private NationService nationService;


    @Autowired
    private SexService sexService;

    @RequestMapping(value = {"/nation","/nation/"},method = RequestMethod.GET)
    public Message getNations(){
        Message message = new Message();
        try {
            List<Nation> nations = nationService.getNations();
            message.setInfo("获取民族信息成功");
            message.setCode(200);
            message.put("nations",nations);
        }catch (Exception e){
            e.printStackTrace();
            message.setInfo("获取民族信息失败，请稍后再试");
            message.setCode(500);
        }
        return message;
    }


    @RequestMapping(value = {"/sex","/sex/"},method = RequestMethod.GET)
    public Message getSexs(){
        Message message = new Message();
        try {
            List<Sex> sexs = sexService.getSexs();
            message.setInfo("获取性别信息成功");
            message.setCode(200);
            message.put("sexs",sexs);
        }catch (Exception e){
            e.printStackTrace();
            message.setInfo("获取性别信息失败，请稍后再试");
            message.setCode(500);
        }
        return message;
    }



}
