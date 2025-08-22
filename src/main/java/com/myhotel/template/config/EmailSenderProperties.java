package com.myhotel.template.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailSenderProperties {

    @Value("${email.sender.timeout.seconds}")
    private int timeoutSeconds;

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }
}
