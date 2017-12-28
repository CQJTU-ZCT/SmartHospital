package com.cqjtu.messages;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public class LogoutMessage  extends Message{


    /**
     * 获取登出成功的消息
     * @return
     */
    public static LogoutMessage getSuccessMessage(){
        LogoutMessage message = new LogoutMessage();

        message.setCode(1);
        message.setInfo("退出成功");

        return  message;
    }


    /**
     * 获取登出失败并且参数错误的消息
     * @return
     */
    public static LogoutMessage getParaErrorMessage(){
        LogoutMessage message = new LogoutMessage();

        message.setCode(-1);
        message.setInfo("退出失败，参数错误");

        return  message;
    }



    /**
     * 获取登出失败并且参数错误的消息
     * @return
     */
    public static LogoutMessage getUserNotLoginMessage(){
        LogoutMessage message = new LogoutMessage();

        message.setCode(-2);
        message.setInfo("退出失败，用户未登录");
        return  message;
    }

}
