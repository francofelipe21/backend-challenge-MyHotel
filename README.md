## Explanation of solutions

### Problem 1
To get the server running, the email.sender.timeout.seconds parameter in application.properties was modified and circular references between EmailTemplateService and EmailSenderService were removed. EmailTemplateService was modified to return information from the repository.

### Problem 2
To be able to send emails to guests, the syntax of the SQL query "findEmailTemplateDataBySurveyResultId" in the "SurveyResultRepository" interface was corrected. Additionally, to provide a custom message of the type "Thanks <guest name> for answering our survey. Kind regards, <hotel name>!", the "buildMailMessage" method of "EmailTemplateService" was modified to return the previous message with the corresponding names.

