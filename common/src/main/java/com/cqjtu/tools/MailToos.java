package com.cqjtu.tools;

import com.cqjtu.messages.MailMessage;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/8.
 */
public class MailToos {


    /**
     *
     * @param to 接收者邮箱
     * @param mess 要发送的消息
     * @param subject 邮件主题
     * @param sendMail 发送者的邮箱地址
     * @param sendPass 发送者的校验码（安全码）
     * @return mailMessage 返回发送结果
     */
    public static MailMessage sendMail(String to, String mess, String subject, String sendMail, String sendPass,String smtpServer){

        MailMessage mailMessage = null;

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props=new Properties();
        props.setProperty("mail.host", smtpServer);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.auth", "true");

        Session session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendMail, sendPass);
            }
        });

        Message message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sendMail));
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            //TODO 部署之前删除这个输出
            System.out.println(mess);
            message.setContent(mess, "text/html;charset=utf-8");
            Transport.send(message);
            mailMessage = MailMessage.getMailSendSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("邮件发送出错");
            mailMessage = MailMessage.getMailServerError();
        }
        return mailMessage;
    }

}
