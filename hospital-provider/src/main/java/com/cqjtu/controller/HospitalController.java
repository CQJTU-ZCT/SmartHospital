package com.cqjtu.controller;

import com.cqjtu.messages.Message;
import com.cqjtu.model.Hospital;
import com.cqjtu.tools.AdminToken;
import com.cqjtu.tools.MailTool;
import com.cqjtu.tools.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author zjhfyq
 * @Desc 医院控制器
 * @date 2018/1/25.
 */
@RestController
@RequestMapping("/hospital")
public class HospitalController {


    private String lastTimeTag = "lastTime";

    @Value("${sendTokenToAdminMail.adminMail}")
    private String adminMail;

    @Value("${sendTokenToAdminMail.keepTime}")
    private String keepTimeString;

    @Value("${sendTokenToAdminMail.sendMail}")
    private String sendMail;

    @Value("${sendTokenToAdminMail.sendPass}")
    private String sendPass;

    @Value("${sendTokenToAdminMail.smtpServer}")
    private String smtpServer;


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public Message test(){
        return  new Message().put("time",System.currentTimeMillis());
    }


    @RequestMapping(value = "/register/{adminToken}",method ={ RequestMethod.POST})
    public Message registerHospitalByPathToken( @PathVariable("adminToken") String adminToken){
        Message message = new Message();
        if (AdminToken.token == null || AdminToken.token.length() <=0){
            message.setInfo("管理员令牌失效，请重新获取");
        }else {
            if (!adminToken.equals(AdminToken.token)){
                message.setInfo("管理员令牌不正确，请联系管理员");
            }else {
                //todo 完成insert操作
            }
        }
        return message;
    }


    @RequestMapping(value = "/register",method = {RequestMethod.POST})
    public Message registerHospitalByHeaderToken(HttpServletRequest request){
        Message message = new Message();
        String adminToken = request.getHeader("adminToken");
        if (adminToken == null || adminToken.length() <=0){
            message.setInfo("请提供管理员令牌");
        }else{
            if (AdminToken.token == null || AdminToken.token.length() <=0){
                message.setInfo("管理员令牌失效，请重新获取");
            }else {
                if (!adminToken.equals(AdminToken.token)){
                    message.setInfo("管理员令牌不正确，请联系管理员");
                }else {
                    //todo 完成insert操作
                }
            }
        }
        return message;
    }


    /**
     * 获取管理员动态密码
     * @return
     */
    @RequestMapping(value = "/getAdminToken",method = RequestMethod.GET)
    public Message getAdminToken(HttpServletRequest request, HttpSession session){
        Message message = new Message();
        Long lastTime = -1L;

        lastTime = (Long) session.getAttribute(lastTimeTag);
        Long keepTime = -1L;
        //设置保存时效
        if (keepTimeString !=null && keepTimeString.length() >0){
            //配置过获取令牌的最小时间间隔
            keepTime = Long.valueOf(keepTimeString);
        }else {
            //设置默认的获取令牌的最小时间间隔
            keepTime = 60*60L;
        }

        if (session.getAttribute(lastTimeTag)!= null){
            //已经获取过令牌
            if (System.currentTimeMillis() < keepTime+lastTime){
                //最小时间间隔内
                message.setCode(-1);
                message.setInfo("不能频繁获取令牌");
            }else {
                message = getLegalTokenMessgae(keepTime,message,session);
            }
        }else {
            message = getLegalTokenMessgae(keepTime,message,session);
        }
        return message;
    }

    private Message getLegalTokenMessgae(Long keepTime,Message message,HttpSession session){
        String token = Token.getToken32WithoutLine();
        //还原之前的值
        AdminToken.reduction();
        //设置新的值
        AdminToken.keepTime = keepTime;
        AdminToken.sendTime = System.currentTimeMillis();
        AdminToken.adminMail = adminMail;
        AdminToken.token = token;
        //保存这次获取令牌的时间
        session.setAttribute(lastTimeTag,AdminToken.sendTime);
        //发送邮件
        MailTool.sendMail(adminMail,"临时令牌:"+token,"令牌",sendMail,sendPass,smtpServer);
        message.setCode(1);
        message.setInfo("成功获取令牌，请去管理员邮箱查看");
        //开启线程去监督token的时效
        AdminToken.AdminTokenLife tokenLife = new AdminToken.AdminTokenLife();
        tokenLife.start();
        return  message;
    }






}
