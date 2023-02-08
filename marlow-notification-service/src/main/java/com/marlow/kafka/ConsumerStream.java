package com.marlow.kafka;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marlow.model.AccountBalance;
import com.marlow.service.NotificationService;

@Configuration
public class ConsumerStream {

	@Autowired
	NotificationService notificationService;

	@Bean
	public Consumer<AccountBalance> consumer() {
		return value -> notificationService.logNotification(value);
	}

}