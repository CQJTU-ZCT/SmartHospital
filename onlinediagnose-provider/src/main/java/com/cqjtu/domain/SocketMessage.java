package com.cqjtu.domain;

/**
 * @description: 套接字消息
 * @author: codezhang
 * @date: 2018-05-18 21:24
 **/
public class SocketMessage {
    //消息发送者
    private String sender ;
    //消息类型
    private String type;
    //消息
    private String msg;
    //消息时间
    private String date;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
