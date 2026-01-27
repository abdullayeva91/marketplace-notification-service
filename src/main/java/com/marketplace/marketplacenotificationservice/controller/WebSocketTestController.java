package com.marketplace.marketplacenotificationservice.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketTestController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketTestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/send-msg")
    public String sendTestMessage(@RequestParam String text) {
        messagingTemplate.convertAndSend("/topic/notifications", "Vizual Test: " + text);
        return "Mesaj gonderildi: " + text;
    }
}