package com.cqjtu.messages;

import com.cqjtu.domain.User;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/2.
 */
public class ValidateMessage extends Message {




    /**
     * 获取验证失败的验证信息
     * @return
     */
    public static ValidateMessage getFailMeassage(){
        ValidateMessage message = new ValidateMessage();
        message.setCode(-1);
        message.setInfo("验证令牌失败");
        return message;
    }

    /**
     * 获取验证成功的验证信息
     * @return
     */
    public static ValidateMessage getSuccessMeassage(){
        ValidateMessage message = new ValidateMessage();
        message.setCode(1);
        message.setInfo("验证令牌成功");
        return message;
    }



}
