package com.marlow.service;

import com.marlow.model.dto.AccountBalance;

public interface NotificationService {

	public void send(AccountBalance accountBalance);
}
