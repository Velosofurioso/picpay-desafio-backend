package com.lvb.challenge.picpay.PicpayBackendChallengeNotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PicpayBackendChallengeNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayBackendChallengeNotificationApplication.class, args);
	}

}
