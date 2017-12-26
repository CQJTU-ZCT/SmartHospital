package com.cqjtu.messages;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public class LogoutMessage {


    private int code ;

    private String desc;

    private LogoutMessage() {
    }


    public int getCode() {
        return code;
    }



    public String getDesc() {
        return desc;
    }


    /**
     * 获取登出成功的消息
     * @return
     */
    public static LogoutMessage getSuccessMessage(){
        LogoutMessage message = new LogoutMessage();

        message.code =1;
        message.desc = "退出成功";

        return  message;
    }


    /**
     * 获取登出失败并且参数错误的消息
     * @return
     */
    public static LogoutMessage getParaErrorMessage(){
        LogoutMessage message = new LogoutMessage();

        message.code =-1;
        message.desc = "退出失败，参数错误";

        return  message;
    }



    /**
     * 获取登出失败并且参数错误的消息
     * @return
     */
    public static LogoutMessage getUserNotLoginMessage(){
        LogoutMessage message = new LogoutMessage();

        message.code =-2;
        message.desc = "退出失败，用户未登录";

        return  message;
    }

}
