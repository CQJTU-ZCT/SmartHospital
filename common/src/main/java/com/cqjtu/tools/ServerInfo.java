package com.cqjtu.tools;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author zjhfyq
 * @Desc 单点登录服务器端的信息，包括登录地址/验证地址/登出地址
 * @date 2017/12/2.
 */
public class ServerInfo {


    @Value("${SSOServer.loginAddres}")
    private static String loginAddress ;

    @Value("${SSOServer.validateAddres}")
    private static String validateAddress ;


    @Value("${SSOServer.logoutAddres}")
    private static  String logoutAddress;

    public static String getLoginAddress() {
        return loginAddress;
    }

    public static void setLoginAddress(String loginAddress) {
        ServerInfo.loginAddress = loginAddress;
    }

    public static String getValidateAddress() {
        return validateAddress;
    }

    public static void setValidateAddress(String validateAddress) {
        ServerInfo.validateAddress = validateAddress;
    }

    public static String getLogoutAddress() {
        return logoutAddress;
    }

    public static void setLogoutAddress(String logoutAddress) {
        ServerInfo.logoutAddress = logoutAddress;
    }
}
