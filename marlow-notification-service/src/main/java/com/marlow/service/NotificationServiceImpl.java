package com.marlow.service;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marlow.model.AccountBalance;
import com.marlow.utility.Constants;
@Service
public class NotificationServiceImpl implements NotificationService{
    private static final Logger logger = LogManager.getLogger(NotificationServiceImpl.class);
 
    
	@Override
	public void logNotification(AccountBalance accountBalance) {

		if(!Objects.isNull(accountBalance)&&accountBalance.getAmount().compareTo(Constants.BALANCE_LIMIT)==-1) {
			logger.info("Account Balance reached low limit {}",accountBalance.getAmount());

		}else
			logger.info("Account Balance for firstname {} lastname {} is updated to {}",accountBalance.getFirstName(),accountBalance.getLastName(),accountBalance.getAmount());

		
	}

}
