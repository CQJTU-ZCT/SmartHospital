package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Emr;
import com.cqjtu.service.EmrServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/28.
 */
@RestController
public class EmrController {

    @Autowired
    EmrServiceImpl service;





    @RequestMapping(value = "/getEmrByUsername/{username}")
    public Message getEmrByUsername(@PathVariable(value = "username")String username){
        return new Message().put("username",username);
    }

    @RequestMapping(value = "/emr", method = RequestMethod.POST)
    public Message add(Emr emr) {
        Message message = new Message();
        message.setCode(200);
        Emr emr1 = service.insert(emr);
        if (null != emr1) {
            message.setInfo("添加emr数据成功");
            message.put("emr", emr1);
        } else {
            message.setInfo("添加emr数据失败");
        }
        return message;
    }

    @RequestMapping(value = "/emr", method = RequestMethod.GET)
    public Message getEmrs() {
        Message msg = new Message();
        msg.setCode(200);
        msg.setInfo("查找数据成功");
        msg.put("emrs", service.getAll());
        return msg;
    }

    @RequestMapping(value = "/emr/{emrId}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("emrId") String id) {
        Emr emr = service.delete(id);
        Message msg = new Message(200);
        if (null == emr) {
            msg.setInfo("删除emr数据失败");
        } else {
            msg.setInfo("删除emer数据成功");
            msg.put("emr", emr);
        }
        return msg;
    }

    @RequestMapping(value = "/emr", method = RequestMethod.PUT)
    public Message update(Emr emr) {
        Message msg = new Message(200);
        Emr emr1 = service.update(emr);
        if (null == emr1) {
            msg.setInfo("更新emr数据失败");
        } else {
            msg.setInfo("更新emr数据成功");
            msg.put("emr", emr1);
        }
        return msg;
    }





}
