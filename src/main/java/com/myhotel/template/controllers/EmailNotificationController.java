package com.myhotel.template.controllers;

import com.myhotel.template.services.SurveyNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class EmailNotificationController {
    private final SurveyNotificationService notificationService;

    public EmailNotificationController(SurveyNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/send/{surveyResponseId}")
    public ResponseEntity<?> sendNotification(@PathVariable Long surveyResponseId) {
        return ResponseEntity.ok(notificationService.notifyGuest(surveyResponseId));
    }

    @GetMapping("/emails_overview")
    public ResponseEntity<?> getEmailSummary() {
        return ResponseEntity.ok(notificationService.getEmailsOverview());
    }
}
