package com.marlow.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marlow.model.TransactionType;
import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.dto.response.TransactionResponse;
import com.marlow.service.FundsService;
import com.marlow.service.TransactionServiceImpl;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionResource {
	
	@Autowired
	FundsService fundService;
	

	
	@PostMapping(path ="/deposit")
	public ResponseEntity<TransactionResponse> deposit(@Valid @RequestBody TransactionRequest request) {
		
        return ResponseEntity.ok( fundService.fundsAction(request,TransactionType.DEPOSIT));

	}

	@PostMapping(path ="/withdraw")
	public ResponseEntity<TransactionResponse> withdraw(@Valid @RequestBody TransactionRequest request) {
		
		return ResponseEntity.ok( fundService.fundsAction(request,TransactionType.WITHDRAW));
	}

}
