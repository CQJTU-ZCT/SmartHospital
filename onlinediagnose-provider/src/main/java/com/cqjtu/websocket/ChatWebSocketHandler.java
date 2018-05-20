package com.cqjtu.websocket;


import com.cqjtu.domain.SocketMessage;
import com.cqjtu.domain.WebSocketSessionObject;
import com.cqjtu.model.Users;
import com.cqjtu.tools.JsonUtil;
import com.cqjtu.tools.Token;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-18 22:49
 **/
@Component("chatWebSocketHandler")
public class ChatWebSocketHandler implements WebSocketHandler {

    //存放用户对话资源
    private static List<WebSocketSessionObject> WEB_SOCKET_SESSION_LIST_USER;
    private static List<WebSocketSessionObject> WEB_SOCKET_SESSION_LIST_DOCTOR;


    static {
        WEB_SOCKET_SESSION_LIST_USER = new ArrayList<>();
        WEB_SOCKET_SESSION_LIST_DOCTOR = new ArrayList<>();
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
         Object userObject  = webSocketSession.getAttributes().get("user");
        if (userObject == null){
            SocketMessage message = new SocketMessage();
            message.setDate(new Date().toString());
            message.setSender("Server");
            message.setType("2");
            message.setMsg("未登录");
            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),webSocketSession);
            //webSocketSession.close(CloseStatus.NOT_ACCEPTABLE);
            //return;
        }else {
            Users user = (Users) userObject;
            //创建一个id
            String id = Token.getToken32WithoutLine();
            WebSocketSessionObject object = new WebSocketSessionObject();
            object.setBusy(false);
            object.setId(id);
            object.setWebSocketSession(webSocketSession);
            if (user.getRoleId() == 1){
                //用户
                object.setUserType(1);
                WEB_SOCKET_SESSION_LIST_USER.add(object);
                //分配用户和医生
                allot();
            }else if (user.getRoleId() == 2){
                //医生
                object.setUserType(2);
                WEB_SOCKET_SESSION_LIST_DOCTOR.add(object);
                //分配用户和医生
                allot();
            }else {
                SocketMessage message = new SocketMessage();
                message.setDate(new Date().toString());
                message.setSender("Server");
                message.setType("3");
                message.setMsg("当前用户不允许登录在线医生，请联系管理员");
                sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),webSocketSession);
                webSocketSession.close(CloseStatus.NOT_ACCEPTABLE);
                return;
            }
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        SocketMessage message = new SocketMessage();
        message.setDate(new Date().toString());
        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_USER){
            if (user.getWebSocketSession().equals(webSocketSession)){
                if (user.getBusy()){
                    String oid = user.getOid();
                    message.setType("6");
                    message.setSender(user.getId());
                    message.setMsg(webSocketMessage.getPayload().toString());
                    for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
                        if (doctor.getId().equals(oid)){
                            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),doctor.getWebSocketSession());
                            break;
                        }
                    }
                }else {
                    message.setSender("Server");
                    message.setMsg("当前没有分配到医生，请耐心等待");
                    message.setType("7");
                    sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),user.getWebSocketSession());
                }
                break;
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        SocketMessage message = new SocketMessage();
        message.setDate(new Date().toString());
        message.setSender("Server");
        message.setType("5");

        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_USER){
            if (user.getWebSocketSession().equals(webSocketSession)){
                if (user.getBusy()){
                    String oid = user.getOid();
                    for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
                        if (doctor.getId().equals(oid)){
                            message.setMsg("当前对话用户异常，已经断开连接，即将重新分配用户");
                            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),doctor.getWebSocketSession());
                            //设置医生状态
                            doctor.setBusy(false);
                            doctor.setOid("");
                            break;
                        }
                    }
                    message.setMsg("您的对话发生异常，系统已经为您断开连接，即将为您重新分配医生");
                    user.setBusy(false);
                    user.setOid("");
                }else {
                    message.setMsg("您的对话发生异常，系统已经为您断开连接，即将为您重新排队");
                }
                sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),user.getWebSocketSession());
                break;
            }
        }

        for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
            if (doctor.getWebSocketSession().equals(webSocketSession)){
                if (doctor.getBusy()){
                    String oid = doctor.getOid();
                    for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_DOCTOR){
                        if (user.getId().equals(oid)){
                            message.setMsg("当前对话医生异常，已经断开连接，即将重新分配医生");
                            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),user.getWebSocketSession());
                            //设置用户状态
                            user.setBusy(false);
                            user.setOid("");
                            break;
                        }
                    }
                    message.setMsg("您的对话发生异常，系统已经为您断开连接，即将为您重新分配用户");
                    doctor.setBusy(false);
                    doctor.setOid("");
                }else {
                    message.setMsg("您的对话发生异常，重新等待用户接入");
                }
                sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),doctor.getWebSocketSession());
                break;
            }
        }
        //重新分配
        allot();

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        SocketMessage message = new SocketMessage();
        message.setDate(new Date().toString());
        message.setSender("Server");
        message.setType("6");
        WebSocketSessionObject object = null;
        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_USER){
            if (user.getWebSocketSession().equals(webSocketSession)){
                if (user.getBusy()){
                    String oid = user.getOid();
                    for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
                        if (doctor.getId().equals(oid)){
                            message.setMsg("当前对话用户已经断开连接，即将重新分配用户");
                            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),doctor.getWebSocketSession());
                            //设置医生状态
                            doctor.setBusy(false);
                            doctor.setOid("");
                            break;
                        }
                    }
                }
                object = user;
                break;
            }
        }
        if(object != null){
            WEB_SOCKET_SESSION_LIST_USER.remove(object);
        }else {
            for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
                if (doctor.getWebSocketSession().equals(webSocketSession)){
                    if (doctor.getBusy()){
                        String oid = doctor.getOid();
                        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_DOCTOR){
                            if (user.getId().equals(oid)){
                                message.setMsg("当前对话医生已经断开连接，即将重新分配医生");
                                sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),user.getWebSocketSession());
                                //设置用户状态
                                user.setBusy(false);
                                user.setOid("");
                                break;
                            }
                        }
                    }
                    object = doctor;
                    break;
                }
            }
            if(object != null){
                WEB_SOCKET_SESSION_LIST_USER.remove(object);
            }
        }
        //重新分配
        allot();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void sendMessage(TextMessage message , WebSocketSession socketSession){
        try {
            if (socketSession.isOpen()){
                socketSession.sendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //分配
    public void allot(){
        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_USER){
            if (user.getBusy() == false){
                for (WebSocketSessionObject doctor : WEB_SOCKET_SESSION_LIST_DOCTOR){
                   if (doctor.getBusy() == false){
                       //说明有空闲医生

                       Users userDomain = (Users) user.getWebSocketSession().getAttributes().get("user");
                       Users doctorDomain = (Users) doctor.getWebSocketSession().getAttributes().get("user");


                       //给医生发送 已经分配的消息
                       SocketMessage message = new SocketMessage();
                       message.setSender("Server");
                       message.setType("8");
                       message.setMsg(userDomain.getRealname());
                       message.setDate(new Date().toString());
                       sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),doctor.getWebSocketSession());
                       //给用户发送分配消息
                       message.setMsg(doctorDomain.getRealname());
                       sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),user.getWebSocketSession());

                       //变更医生和用户状态
                       doctor.setBusy(true);
                       user.setBusy(true);
                       //相互设置对话对象ID
                       doctor.setOid(user.getId());
                       user.setOid(doctor.getId());
                       break;
                   }
                }
            }
        }
        SocketMessage message = new SocketMessage();
        message.setType("4");
        message.setDate(new Date().toString());
        message.setSender("Server");
        List<WebSocketSession> idelUser = new ArrayList<>();
        for (WebSocketSessionObject user : WEB_SOCKET_SESSION_LIST_USER){
            if (user.getBusy() == false){
                idelUser.add(user.getWebSocketSession());
            }
        }
        int numIdle = idelUser.size();
        for (int i =0 ;i< numIdle ;i++){
            message.setMsg("排在您前面的还有："+(numIdle-i-1)+"个人");
            sendMessage(new TextMessage(JsonUtil.praseBeanToJson(message)),idelUser.get(i));
        }
    }




}
