package com.cqjtu.messages;

import com.cqjtu.domain.User;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public class ValidateMessage {

    private int code;

    private String desc;


    private User user;


    private  String originalURL;




    private ValidateMessage(){

    }


    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
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

    public void setUser(User user){
        this.user = user;
    }


    /**
     * 获取验证失败的验证信息
     * @return
     */
    public static ValidateMessage getFailMeassage(){
        ValidateMessage message = new ValidateMessage();
        message.code = -1;
        message.desc = "验证令牌失败";
        return message;
    }

    /**
     * 获取验证成功的验证信息
     * @return
     */
    public static ValidateMessage getSuccessMeassage(){
        ValidateMessage message = new ValidateMessage();
        message.code = 1;
        message.desc = "验证令牌成功";
        return message;
    }



}
