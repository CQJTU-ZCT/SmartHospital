package com.cqjtu.controller;

import com.cqjtu.domain.MailServerInfo;
import com.cqjtu.messages.MailMessage;
import com.cqjtu.tools.MailToos;
import com.cqjtu.tools.NumberVerifyCodeTool;
import com.cqjtu.tools.RegularTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;

/**
 * @author zjhfyq
 * @Desc 邮箱相关验证控制器
 * @date 2017/12/8.
 */
@RestController
public class MailController {

    @Autowired
    private MailServerInfo mailServerInfo ;

    @RequestMapping(value = "/getAndSendMailVerifyCode/{receiverMail:.+}")
    public MailMessage getAndSendMailVerifyCode(@PathVariable("receiverMail") String receiverMailBeforeDecode){
        System.out.println(receiverMailBeforeDecode);
        String receiverMail = null;
        MailMessage mailMessage = null;
        receiverMail =new String(Base64.getDecoder().decode(receiverMailBeforeDecode));
        if (RegularTool.isMail(receiverMail)){
            //邮箱格式正确
            String verifyCode = NumberVerifyCodeTool.getNumberVerifyCode(6);

            mailMessage = MailToos.sendMail(receiverMail, "验证码："+ verifyCode, "大吉大利，你吃不到鸡",
                    mailServerInfo.getMailUsername(), mailServerInfo.getMailPassword(),mailServerInfo.getSmtpServer());
            if (mailMessage.getCode() != 1){

            }else {
                mailMessage.put("verifyCode",verifyCode);
            }

        }else {
            //邮箱格式验证出错
            mailMessage = MailMessage.getMailFormatError();
        }
        return mailMessage;
    }

    @RequestMapping(value = "/getAndSendMailVerifyCode")
    public MailMessage getAndSendMailVerifyCodeWithoutMail(){
        return MailMessage.getMailAddressNotAllowedNull();
    }



}
