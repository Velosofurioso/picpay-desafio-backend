package com.lvb.challenge.picpay.PicpayBackendChallenge.service.sms;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.sms.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
    // or maybe void sendSms(String phoneNumber, String message);
}
