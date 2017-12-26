package com.cqjtu.messages;

import com.cqjtu.domain.User;

/**
 * @author zjhfyq
 * @Desc 登录信息对象
 * @date 2017/12/2.
 */
public class LoginMessage {

    private int code;


    private String desc;


    private String token;


    private String originalUrl;




    private User user;


    private LoginMessage() {
    }


    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public int getCode() {
        return code;
    }



    public String getDesc() {
        return desc;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    /**
     * 获取登录成功的消息
     * @return
     */
    public static LoginMessage getSuceesssMessage(){
        LoginMessage message = new LoginMessage();
        message.code = 1;
        message.desc = "登录成功";
        return message;
    }


    /**
     * 获取密码错误的消息
     * @return
     */
    public static LoginMessage getErrorPasswordMessage(){
        LoginMessage message = new LoginMessage();
        message.code = -1;
        message.desc = "登录失败，密码错误";
        return message;
    }


    /**
     * 获取用户不存在的消息
     * @return
     */
    public static LoginMessage getUserNotExistMessage(){
        LoginMessage message = new LoginMessage();
        message.code = -2;
        message.desc = "登录失败，用户不存在";
        return message;
    }



    /**
     * 获取登录参数错误的消息的消息
     * @return
     */
    public static LoginMessage getParaErrorMessage(){
        LoginMessage message = new LoginMessage();
        message.code = -3;
        message.desc = "登录失败，登录参数错误";
        return message;
    }

}
