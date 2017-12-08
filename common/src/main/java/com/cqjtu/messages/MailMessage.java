package com.cqjtu.messages;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjhfyq
 * @Desc 邮箱返回信息PO
 * @date 2017/12/8.
 */
public class MailMessage extends Message {


    /**
     * 获取邮件发送成功消息
     * @return
     */
    public static MailMessage  getMailSendSuccess(){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setCode(1);
        mailMessage.setInfo("邮件发送成功");
        return mailMessage;
    }

    /**
     * 获取邮箱格式错误消息
     * @return
     */
    public static MailMessage  getMailFormatError(){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setCode(-1);
        mailMessage.setInfo("邮箱格式错误");
        return mailMessage;
    }

    /**
     * 获取邮箱服务器出错消息
     * @return
     */
    public static MailMessage  getMailServerError(){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setCode(-2);
        mailMessage.setInfo("邮箱服务器出错");
        return mailMessage;
    }

    /**
     * 获取邮件模板文件未找到消息
     * @return
     */
    public static MailMessage  getMailTemplateNotFound(){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setCode(-3);
        mailMessage.setInfo("邮件模板文件未找到");
        return mailMessage;
    }


    /**
     * 收件人邮箱地址不允许为空
     * @return
     */
    public static MailMessage  getMailAddressNotAllowedNull(){
        MailMessage mailMessage = new MailMessage();
        mailMessage.setCode(-4);
        mailMessage.setInfo("收件人邮箱地址不允许为空");
        return mailMessage;
    }




}
