package com.lvb.challenge.picpay.PicpayBackendChallenge.configuration.twilio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

    private String accountSid;
    private String authToken;
    private String trialNumber;
}
