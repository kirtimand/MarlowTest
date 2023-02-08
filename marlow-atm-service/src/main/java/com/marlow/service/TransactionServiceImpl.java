package com.marlow.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.marlow.exception.GlobalErrorCode;
import com.marlow.exception.InactiveAccountException;
import com.marlow.exception.InsufficientFundsException;
import com.marlow.exception.InvalidCardException;
import com.marlow.model.AccountStatus;
import com.marlow.model.TransactionType;
import com.marlow.model.dto.AccountBalance;
import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.dto.response.TransactionResponse;
import com.marlow.model.entity.ATMEntity;
import com.marlow.model.entity.BankAccountEntity;
import com.marlow.model.entity.TransactionEntity;
import com.marlow.repository.BankAccountRepository;
import com.marlow.repository.TransactionRepository;
import com.marlow.utility.Constants;
import com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class TransactionServiceImpl implements TransactionService {
	private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public String save(BankAccountEntity bankAccountEntity, BigDecimal amount) {
		String transactionId = UUID.randomUUID().toString();

		transactionRepository.saveAndFlush(TransactionEntity.builder().account(bankAccountEntity).amount(amount)
				.date(LocalDate.now()).referenceNumber(UUID.randomUUID().toString()).transactionId(transactionId)
				.transactionType(TransactionType.WITHDRAW).build());
		return transactionId;
	}

}