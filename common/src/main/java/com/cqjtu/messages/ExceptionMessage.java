package com.cqjtu.messages;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/8.
 */
public class ExceptionMessage extends Message {

    public static ExceptionMessage getServerErrorMessage(){
        ExceptionMessage message = new ExceptionMessage();
        message.setCode(500);
        message.setInfo("服务器开小差了...请稍后再试");
        return message;
    }

    public static ExceptionMessage  getExceptionMessage(int code ,String info){
        ExceptionMessage message = new ExceptionMessage();
        message.setCode(code);
        message.setInfo(info);
        return message;
    }

}
