package com.cqjtu.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author zjhfyq
 * @Desc 单点登录服务器端的信息，包括登录地址/验证地址/登出地址
 * @date 2017/12/2.
 */
@Component
public class  ServerInfo {


    @Value("${SSOServer.loginAddress}")
    private  String loginAddress ;

    @Value("${SSOServer.validateAddress}")
    private  String validateAddress ;


    @Value("${SSOServer.logoutAddress}")
    private   String logoutAddress;

    @Value("${SSOServer.loginService}")
    private String loginService;


    @Value("${SSOServer.logoutService}")
    private String logoutService;


    @Value("${SSOServer.validateService}")
    private String validateService;

    public ServerInfo() {

    }

    public String getLoginAddress() {
        return loginAddress;
    }

    public void setLoginAddress(String loginAddress) {
        this.loginAddress = loginAddress;
    }

    public String getValidateAddress() {
        return validateAddress;
    }

    public void setValidateAddress(String validateAddress) {
        this.validateAddress = validateAddress;
    }

    public String getLogoutAddress() {
        return logoutAddress;
    }


    public void setLogoutAddress(String logoutAddress) {
        this.logoutAddress = logoutAddress;
    }


    public String getLoginService() {
        return loginService;
    }

    public void setLoginService(String loginService) {
        this.loginService = loginService;
    }

    public String getLogoutService() {
        return logoutService;
    }

    public void setLogoutService(String logoutService) {
        this.logoutService = logoutService;
    }

    public String getValidateService() {
        return validateService;
    }

    public void setValidateService(String validateService) {
        this.validateService = validateService;
    }
}
