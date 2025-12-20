package com.marketplace.marketplacenotificationservice.controller;

import com.marketplace.marketplacenotificationservice.dto.NotificationRequest;
import com.marketplace.marketplacenotificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(
            @Valid @RequestBody NotificationRequest request
    ) {
        notificationService.sendEmail(request);
        return new ResponseEntity<>("Email sent successfully", HttpStatus.OK);
    }
}
