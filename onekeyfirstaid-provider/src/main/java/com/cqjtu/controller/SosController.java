package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.SosRecord;
import com.cqjtu.model.Users;
import com.cqjtu.service.SosService;
import com.cqjtu.tools.LoggerTool;
import com.cqjtu.tools.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-06-09 11:08
 **/
@RestController
@RequestMapping(value = {"/sos","/sos/"})
public class SosController {

    @Autowired
    private SosService sosService;

    @RequestMapping(value = {""} ,method = RequestMethod.POST)
    public Message insert(String token , HttpServletRequest request,SosRecord sosRecord){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            message.setCode(401);
            message.setInfo("未登录");
        }
        if(request.getAttribute("user") == null){
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            try{
                Users users = (Users) request.getAttribute("user");
                sosRecord.setIdCard(users.getIdCard());
                if (sosRecord.getLongitude() == null || sosRecord.getLatitude() == null){
                    message.setInfo("坐标不合法");
                }else {
                    sosRecord.setSosId(Token.getToken32WithoutLine());
                    sosRecord.setStatusId(1);
                    if (sosService.insert(sosRecord) == 1){
                        message.setInfo("请求急救成功");
                        message.setCode(200);
                    }else {
                        message.setInfo("请求急救失败，请重新尝试");
                    }
                }
            }catch (Exception e){
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                message.setCode(401);
                message.setInfo("未登录");
            }
        }
        message.put("sosRecord",sosRecord);
        return message;
    }



    @RequestMapping(value = {""} ,method = RequestMethod.GET)
    public Message select(String token , HttpServletRequest request,SosRecord sosRecord){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            message.setCode(401);
            message.setInfo("未登录");
        }
        if(request.getAttribute("user") == null){
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            try{
                Users users = (Users) request.getAttribute("user");
                if (users.getRoleId() == 1){
                    //普通用户只能查询自己的记录
                   sosRecord.setIdCard(users.getIdCard());
                }
                List<SosRecord>  list = sosService.select(sosRecord);
                message.setInfo("查询成功");
                message.put("sosRecords",list);
            }catch (Exception e){
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                message.setCode(401);
                message.setInfo("未登录");
            }
        }
        return message;
    }

    @RequestMapping(value = {""} ,method = RequestMethod.PUT)
    public Message finish(String token , HttpServletRequest request , String sosId){
        Message message = new Message();
        message.setCode(200);
        if (token == null || token.length() <=0){
            token = request.getHeader("token");
        }
        if (token == null || token.length() <=0 ){
            message.setCode(401);
            message.setInfo("未登录");
        }
        if(request.getAttribute("user") == null){
            message.setCode(401);
            message.setInfo("未登录");
        }else {
            try{
                Users users = (Users) request.getAttribute("user");
                if (sosId == null || sosId.length() <=0){
                    message.setInfo("急救编号不合法");
                }else {
                    if (users.getRoleId() == 1 ){
                        message.setCode(403);
                        message.setInfo("没有操作权限");
                    }else {
                        if (sosService.finishSos(sosId) == 1){
                            message.setInfo("完成一键急救成功");
                        }else {
                            message.setInfo("不存在的一键急救编号");
                        }
                    }
                }
            }catch (Exception e){
                LoggerTool.getLogger(this.getClass()).info(e.getMessage());
                message.setCode(401);
                message.setInfo("未登录");
            }
        }
        return message;
    }


}
