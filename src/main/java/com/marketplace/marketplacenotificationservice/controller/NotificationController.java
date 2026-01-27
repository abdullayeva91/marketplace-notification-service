package com.marketplace.marketplacenotificationservice.controller;

import com.marketplace.marketplacenotificationservice.dto.NotificationRequest;
import com.marketplace.marketplacenotificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(NotificationService notificationService,
                                  SimpMessagingTemplate messagingTemplate) {
        this.notificationService = notificationService;
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@Valid @RequestBody NotificationRequest request) {
        notificationService.sendEmailToRecipient(request);

        messagingTemplate.convertAndSend("/topic/notifications", request.getMessage());

        return new ResponseEntity<>("Notification processed successfully", HttpStatus.OK);
    }
}