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
public class FundsServiceImpl implements FundsService {
	private static final Logger logger = LogManager.getLogger(FundsServiceImpl.class);

	@Autowired
	private AtmService atmService;
	@Autowired
	private BankAccountService bankAccountService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private NotificationService notificationService;

	@Retryable(value = { MySQLTransactionRollbackException.class, UnexpectedRollbackException.class,
			ObjectOptimisticLockingFailureException.class }, backoff = @Backoff(delay = 300, maxDelay = 300), maxAttempts = 8)
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public TransactionResponse fundsAction(TransactionRequest transactionRequest, TransactionType action) {

		logger.info("{} amount request started [{}]", action, transactionRequest.toString());
		Optional<ATMEntity> atmOptional = atmService.getCardDetails(transactionRequest);
		String message;
		if (atmOptional.isPresent()) {
			logger.info("Fetched ATM card details card number : [{}]", transactionRequest.getCardNo());

			Optional<BankAccountEntity> bankAccountEntityOp = bankAccountService
					.findByAccountNumber(atmOptional.get().getBankAccountEntity().getNumber());
			if (bankAccountEntityOp.isPresent()) {
				logger.info("Bank account details fetched {}", bankAccountEntityOp.get().getNumber());
				BankAccountEntity bankAccountEntity = bankAccountEntityOp.get();

				if (action.equals(TransactionType.WITHDRAW)) {
					if (validateBalance(bankAccountEntity, transactionRequest.getAmount())) {
						bankAccountEntity.setAvailableBalance(
								bankAccountEntity.getAvailableBalance().subtract(transactionRequest.getAmount()));

					}
					message = Constants.WITHDRAW_SUCCESS_MSG;
				} else {
					message = Constants.DEPOSIT_SUCCESS_MSG;
					bankAccountEntity.setAvailableBalance(
							bankAccountEntity.getAvailableBalance().add(transactionRequest.getAmount()));
				}
				bankAccountService.save(bankAccountEntity);
				String transactionId = transactionService.save(bankAccountEntity, transactionRequest.getAmount());

				notificationService.send(AccountBalance.builder().amount(bankAccountEntity.getAvailableBalance())
						.email(bankAccountEntity.getUser().getEmail())
						.firstName(bankAccountEntity.getUser().getFirstName())
						.lastName(bankAccountEntity.getUser().getLastName()).build());

				return TransactionResponse.builder().message(message).transactionId(transactionId).build();

			} else {
				logger.error("Inactive Account  for {}", transactionRequest.getCardNo());

				throw new InactiveAccountException("Inactive Account " + transactionRequest.getCardNo(),
						GlobalErrorCode.INACTIVE_ACCOUNT);
			}
		} else

		{
			logger.error("Card details not valid for {}", transactionRequest.getCardNo());

			throw new InvalidCardException("Given Card is not valid " + transactionRequest.getCardNo(),
					GlobalErrorCode.INVALID_CARD);
		}

	}

	private boolean validateBalance(BankAccountEntity bankAccount, BigDecimal amount) {
		if (bankAccount.getAvailableBalance().compareTo(amount) == -1) {
			logger.error("Insufficient funds in the account  {}", bankAccount.getNumber());

			throw new InsufficientFundsException("Insufficient funds in the account " + bankAccount.getNumber(),
					GlobalErrorCode.INSUFFICIENT_FUNDS);
		}
		return true;
	}

}