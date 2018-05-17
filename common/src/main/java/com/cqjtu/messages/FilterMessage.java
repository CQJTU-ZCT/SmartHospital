package com.cqjtu.messages;

/**
 * @author zjhfyq
 * @Desc
 * @date 2018/1/23.
 */
public class FilterMessage extends Message {

    private FilterMessage() {
    }

    /**
     * token无效
     * @return
     */
    public static FilterMessage getNoTokenMessage(){
        FilterMessage message = new FilterMessage();
        message.setCode(-1);
        message.setInfo("token无效");
        return message;
    }

    /**
     * 没有登录
     * @return
     */
    public static FilterMessage getNoLoginMessage(){
        FilterMessage message = new FilterMessage();
        message.setCode(-2);
        message.setInfo("用户没有登录");
        return message;
    }

    /**
     * token不合法
     * @return
     */
    public static FilterMessage getTokenIllegalMessage(){
        FilterMessage message = new FilterMessage();
        message.setCode(-3);
        message.setInfo("token不合法");
        return message;
    }


    /**
     * 验证服务超时
     * @return
     */
    public static FilterMessage getTimeoutMessage(){
        FilterMessage message = new FilterMessage();
        message.setCode(-4);
        message.setInfo("验证服务超时");
        return message;
    }


    /**
     * 验证服务出错
     * @return
     */
    public static FilterMessage getErrorMessage(){
        FilterMessage message = new FilterMessage();
        message.setCode(-5);
        message.setInfo("验证服务出错，请稍后再试");
        return message;
    }

}
