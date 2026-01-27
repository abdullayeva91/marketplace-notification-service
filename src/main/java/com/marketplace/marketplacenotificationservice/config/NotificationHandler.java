package com.marketplace.marketplacenotificationservice.config;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
@EnableWebSocket
public class NotificationHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("WS connected: " + session.getId());
    }
}
