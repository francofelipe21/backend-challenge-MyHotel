package com.myhotel.template.services;
import com.myhotel.template.models.MessageResult;
import org.springframework.stereotype.Service;

@Service
public class SurveyNotificationService {

    private final EmailSenderService emailSenderService;
    private final EmailTemplateService emailTemplateService;

    public SurveyNotificationService(EmailSenderService emailSenderService, EmailTemplateService emailTemplateService) {
        this.emailSenderService = emailSenderService;
        this.emailTemplateService = emailTemplateService;
    }

    public MessageResult notifyGuest(Long surveyResponseId) {
        String from = emailTemplateService.getSenderName(surveyResponseId);
        String to = emailTemplateService.getRecipe(surveyResponseId);
        String subject = "Thank you for your feedback!";
        String body = emailTemplateService.buildMailMessage(surveyResponseId);
        return emailSenderService.sendEmail(from, to, subject, body);
    }

    public Object getEmailsOverview() {
        return emailTemplateService.getEmailsOverview();
    }
}
