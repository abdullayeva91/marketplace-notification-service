package com.marketplace.marketplacenotificationservice.service;

import com.marketplace.marketplacenotificationservice.dto.NotificationRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmailToRecipient(NotificationRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("abdullayevala@gmail.com");

        mailMessage.setTo(request.getTo());

        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getMessage());

        mailSender.send(mailMessage);

        System.out.println("LOG: Mail uğurla göndərildi: " + request.getTo());
    }
}