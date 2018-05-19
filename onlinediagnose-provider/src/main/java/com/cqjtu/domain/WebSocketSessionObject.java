package com.cqjtu.domain;

import org.springframework.web.socket.WebSocketSession;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-19 09:27
 **/

public class WebSocketSessionObject {
    private String id;
    private WebSocketSession webSocketSession;
    private Boolean isBusy = false;
    private String oid ="";
    private int  userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
