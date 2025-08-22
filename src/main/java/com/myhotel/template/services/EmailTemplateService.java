package com.myhotel.template.services;

import com.myhotel.template.projections.EmailTemplateDataProjection;
import com.myhotel.template.projections.EmailsOverviewTemplateDataProjection;
import com.myhotel.template.repositories.SurveyResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailTemplateService {

    private SurveyResultRepository surveyResultRepository;

    public EmailTemplateService(SurveyResultRepository surveyResultRepository) {
        this.surveyResultRepository = surveyResultRepository;
    }

    public EmailTemplateDataProjection getTemplateData(Long surveyResponseId) {
        EmailTemplateDataProjection emailTemplateDataProjection = this.surveyResultRepository.findEmailTemplateDataBySurveyResultId(surveyResponseId);
        return emailTemplateDataProjection;
    }

    public String getRecipe(Long surveyResponseId) {
        EmailTemplateDataProjection templateDataProjection =  this.getTemplateData(surveyResponseId);
        return templateDataProjection.getGuestEmail();
    }


    public String buildMailMessage(Long surveyResponseId) {
        EmailTemplateDataProjection templateDataProjection =  this.getTemplateData(surveyResponseId);
        String guest_name = templateDataProjection.getGuestName();
        String hotel_name = templateDataProjection.getHotelName();
        return "Thanks "+guest_name+" for answering our survey. Kind regards, "+hotel_name+"!";
    }

    public String getSenderName(Long surveyResponseId) {
        EmailTemplateDataProjection templateData =  this.getTemplateData(surveyResponseId);
        return templateData.getHotelName();
    }

    public List<EmailsOverviewTemplateDataProjection> getEmailsOverview() {
        List<EmailsOverviewTemplateDataProjection> emailTemplateDataProjection = this.surveyResultRepository.getEmailsOverview();
        return emailTemplateDataProjection;
    }
}
