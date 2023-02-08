package com.marlow.service;

import java.util.Optional;

import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.entity.ATMEntity;

public interface AtmService {
	public Optional<ATMEntity> getCardDetails(TransactionRequest transactionRequest);	
}
