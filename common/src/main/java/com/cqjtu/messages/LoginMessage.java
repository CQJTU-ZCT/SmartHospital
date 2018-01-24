package com.cqjtu.messages;


/**
 * @author zjhfyq
 * @Desc 登录信息对象
 * @date 2017/12/2.
 */
public class LoginMessage extends Message{


    /**
     * 获取登录成功的消息
     * @return
     */
    public static LoginMessage getSuccessMessage(){
        LoginMessage message = new LoginMessage();
        message.setCode(1);
        message.setInfo("登录成功");
        return message;
    }


    /**
     * 获取密码错误的消息
     * @return
     */
    public static LoginMessage getErrorPasswordMessage(){
        LoginMessage message = new LoginMessage();
        message.setCode(-1);
        message.setInfo("登录失败，密码错误");
        return message;
    }


    /**
     * 获取用户不存在的消息
     * @return
     */
    public static LoginMessage getUserNotExistMessage(){
        LoginMessage message = new LoginMessage();
        message.setCode(-2);
        message.setInfo( "登录失败，用户不存在");
        return message;
    }



    /**
     * 获取登录参数错误的消息的消息
     * @return
     */
    public static LoginMessage getParaErrorMessage(){
        LoginMessage message = new LoginMessage();
        message.setCode(-3);
        message.setInfo("登录失败，登录参数错误");
        return message;
    }


    /**
     * 不允许重复登录的消息
     * @return
     */
    public static LoginMessage getRepeatLoginMessage(){
        LoginMessage message = new LoginMessage();
        message.setCode(-4);
        message.setInfo("用户已经登录，不允许重复登录");
        return message;
    }

}
