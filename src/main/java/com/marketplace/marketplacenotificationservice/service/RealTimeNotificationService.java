package com.marketplace.marketplacenotificationservice.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RealTimeNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public RealTimeNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendToUser(String topic, Object message) {
        messagingTemplate.convertAndSend("/topic/" + topic, message);
    }
}