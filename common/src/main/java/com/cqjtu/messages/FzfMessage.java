package com.cqjtu.messages;

/**
 * @author zjhfyq
 * @Desc
 * @date 2017/12/8.
 */
public class FzfMessage  extends  Message{

    public  static FzfMessage get404Message(){
        FzfMessage message = new FzfMessage();
        message.setCode(404);
        return  message;
    }

}
