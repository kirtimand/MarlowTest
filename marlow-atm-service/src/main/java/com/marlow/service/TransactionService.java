package com.marlow.service;

import java.math.BigDecimal;

import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.dto.response.TransactionResponse;
import com.marlow.model.entity.BankAccountEntity;

public interface TransactionService {

	public String save(BankAccountEntity bankAccountEntity, BigDecimal amount) ;
	
}
