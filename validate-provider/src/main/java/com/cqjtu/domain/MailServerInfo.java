package com.cqjtu.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/8.
 */
@Component("mailServerInfo")
public class MailServerInfo {

    @Value("${mail.mailUsername}")
    private  String mailUsername;

    @Value("${mail.mailPassword}")
    private  String mailPassword;

    @Value("${mail.smtpServer}")
    private    String smtpServer;


    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
}
