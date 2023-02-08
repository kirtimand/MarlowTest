package com.marlow.service;

import com.marlow.model.TransactionType;
import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.dto.response.TransactionResponse;

public interface FundsService {
	public TransactionResponse fundsAction(TransactionRequest transactionRequest, TransactionType action) ;	
}
