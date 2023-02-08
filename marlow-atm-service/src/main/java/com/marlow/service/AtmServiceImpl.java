package com.marlow.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.entity.ATMEntity;
import com.marlow.repository.ATMRepository;

@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	private ATMRepository atmRepository;

	public Optional<ATMEntity> getCardDetails(TransactionRequest transactionRequest) {

		return atmRepository.findByCardNumberAndPinAndExpiryGreaterThanEqual(transactionRequest.getCardNo(),
				transactionRequest.getPin(), LocalDate.now());

	}


}