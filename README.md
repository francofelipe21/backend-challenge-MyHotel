## Explanation of solutions

### Problem 1
To get the server running, the email.sender.timeout.seconds parameter in application.properties was modified and circular references between "EmailTemplateService" and "EmailSenderService" were removed. "EmailTemplateService" was modified to return information from the repository.

### Problem 2
To be able to send emails to guests, the syntax of the SQL query "findEmailTemplateDataBySurveyResultId" in the "SurveyResultRepository" interface was corrected. Additionally, to provide a custom message of the type "Thanks guestname for answering our survey. Kind regards, hotelname!", the "buildMailMessage" method of "EmailTemplateService" was modified to return the previous message with the corresponding names.

### Problem 3
In the repository, the SQL query "findEmailTemplateDataBySurveyResultId" was modified so that instead of returning scoreCant, it returns scoreCount.

### Problem 4
To provide a list of the most popular domains from largest to smallest, a series of steps were followed:
* The new route "/emails_overview" was created in "EmailNotificationController".
* The "getEmailsOverview" method of the "SurveyNotificationService" class was called.
* "SurveyNotificationService" calls the "getEmailsOverview" method of "EmailTemplateService".
* "EmailTemplateService" queries the repository for a list of "EmailsOverviewTemplateDataProjection".
* The "EmailsOverviewTemplateDataProjection" interface was created to serve as a container for the data in the new query.
* The "getEmailsOverview" method was created in the repository to query for the most popular domains.
