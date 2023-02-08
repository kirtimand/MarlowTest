package com.marlow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.marlow.model.dto.AccountBalance;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	public StreamBridge streamBridge;

	@Value("${kafka.binding.name}")
	String bindingName;

	public void send(AccountBalance accountBalance) {
		streamBridge.send(bindingName, accountBalance);
	}
}