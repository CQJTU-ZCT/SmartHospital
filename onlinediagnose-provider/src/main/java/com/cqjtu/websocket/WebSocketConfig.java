package com.cqjtu.websocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @description: ${description}
 * @author: codezhang
 * @date: 2018-05-18 22:42
 **/
@Component("webSocketConfig")
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandshakeInterceptor chatHandshakeInterceptor;

    @Autowired
    private ChatWebSocketHandler chatWebSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler, "/ws-sockjs")
                .addInterceptors(chatHandshakeInterceptor);
        registry.addHandler(chatWebSocketHandler, "/ws")
                .addInterceptors(chatHandshakeInterceptor);
    }

}
