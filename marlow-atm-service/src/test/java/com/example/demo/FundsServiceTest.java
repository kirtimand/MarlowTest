package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.marlow.exception.InactiveAccountException;
import com.marlow.exception.InsufficientFundsException;
import com.marlow.exception.InvalidCardException;
import com.marlow.model.AccountStatus;
import com.marlow.model.AccountType;
import com.marlow.model.TransactionType;
import com.marlow.model.dto.request.TransactionRequest;
import com.marlow.model.entity.ATMEntity;
import com.marlow.model.entity.BankAccountEntity;
import com.marlow.model.entity.TransactionEntity;
import com.marlow.model.entity.UserEntity;
import com.marlow.repository.TransactionRepository;
import com.marlow.service.AtmService;
import com.marlow.service.BankAccountService;
import com.marlow.service.FundsServiceImpl;
import com.marlow.service.NotificationService;
import com.marlow.service.TransactionService;
import com.marlow.service.TransactionServiceImpl;

@ExtendWith(MockitoExtension.class)
class FundsServiceTest {
	@Mock
	AtmService atmService;
	@Mock
	private NotificationService notificationService;

	@Mock
	TransactionService transactionService;
	@Mock
	TransactionRepository transactionRepo;

	@Mock
	BankAccountService bankAccountService;
	static Optional<ATMEntity> atmOptional;

	@InjectMocks
	FundsServiceImpl fundsService;

	TransactionRequest request;

	BankAccountEntity bankAccount;

	@MockitoSettings(strictness = Strictness.WARN)

	@BeforeAll
	static void initContext() {

	}

	@Test
	public void testDepositSuccess() {
		UserEntity userEntity = UserEntity.builder().email("k@gmail.com").firstName("kirti").lastName("m")
				.identificationNumber("abc123").build();
		bankAccount = BankAccountEntity.builder().actualBalance(new BigDecimal(1000))
				.availableBalance(new BigDecimal(1000)).number("123123123123").status(AccountStatus.ACTIVE)
				.type(AccountType.SAVINGS_ACCOUNT).user(userEntity).build();
		
		ATMEntity atm = new ATMEntity(1l, "123456789111", "debit", LocalDate.now(), 123, 1234, bankAccount);

		atmOptional = Optional.of(atm);
		Optional<BankAccountEntity> bankAccountOptional = Optional.of(bankAccount);

		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		request.setCardNo("123456789111");
		request.setPin(1234);
		
		String transactionId = UUID.randomUUID().toString();

		TransactionEntity transaction = TransactionEntity.builder().account(bankAccount).amount(new BigDecimal(1))
				.date(LocalDate.now()).referenceNumber(UUID.randomUUID().toString()).transactionId(transactionId)
				.transactionType(TransactionType.WITHDRAW).build();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);
		when(bankAccountService.findByAccountNumber("123123123123")).thenReturn(bankAccountOptional);

		assertEquals("Deposit successful", fundsService.fundsAction(request, TransactionType.DEPOSIT).getMessage());

	}

	@Test
	public void testInvalidCard() {
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		request.setCardNo("12345678911");
		request.setPin(1234);

		atmOptional = Optional.empty();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);

		assertThrows(InvalidCardException.class, () -> {
			fundsService.fundsAction(request, TransactionType.DEPOSIT);
		});

	}

	@Test
	public void testInvalidAccount() {
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		UserEntity userEntity = UserEntity.builder().email("k@gmail.com").firstName("kirti").lastName("m")
				.identificationNumber("adv").build();

		bankAccount = BankAccountEntity.builder().actualBalance(new BigDecimal(1000))
				.availableBalance(new BigDecimal(1000)).number("123123123123").status(AccountStatus.ACTIVE)
				.type(AccountType.SAVINGS_ACCOUNT).user(userEntity).build();

		ATMEntity atm = new ATMEntity(1l, "123456789111", "debit", LocalDate.now(), 123, 1234, bankAccount);

		atmOptional = Optional.of(atm);
		Optional<BankAccountEntity> bankAccountOptional = Optional.empty();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);
		when(bankAccountService.findByAccountNumber("123123123123")).thenReturn(bankAccountOptional);

		assertThrows(InactiveAccountException.class, () -> {
			fundsService.fundsAction(request, TransactionType.DEPOSIT);
		});

	}

	@Test
	public void testWithdrawSuccess() {
		UserEntity userEntity = UserEntity.builder().email("k@gmail.com").firstName("k").lastName("m")
				.identificationNumber("adv").build();
		bankAccount = BankAccountEntity.builder().actualBalance(new BigDecimal(1000))
				.availableBalance(new BigDecimal(1000)).number("123123123123").status(AccountStatus.ACTIVE)
				.type(AccountType.SAVINGS_ACCOUNT).user(userEntity).build();
		ATMEntity atm = new ATMEntity(1l, "123456789111", "debit", LocalDate.now(), 123, 1234, bankAccount);

		atmOptional = Optional.of(atm);
		Optional<BankAccountEntity> bankAccountOptional = Optional.of(bankAccount);
		;
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		request.setCardNo("123456789111");
		request.setPin(1234);
		String transactionId = UUID.randomUUID().toString();

		TransactionEntity transaction = TransactionEntity.builder().account(bankAccount).amount(new BigDecimal(1))
				.date(LocalDate.now()).referenceNumber(UUID.randomUUID().toString()).transactionId(transactionId)
				.transactionType(TransactionType.WITHDRAW).build();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);
		when(bankAccountService.findByAccountNumber("123123123123")).thenReturn(bankAccountOptional);

		assertEquals("Withdraw successful", fundsService.fundsAction(request, TransactionType.WITHDRAW).getMessage());

	}

	@Test
	public void testAccountBalanceTest() {
		UserEntity userEntity = UserEntity.builder().email("k@gmail.com").firstName("k").lastName("m")
				.identificationNumber("adv").build();
		bankAccount = BankAccountEntity.builder().actualBalance(new BigDecimal(10)).availableBalance(new BigDecimal(10))
				.number("123123123123").status(AccountStatus.ACTIVE).type(AccountType.SAVINGS_ACCOUNT).user(userEntity)
				.build();
		ATMEntity atm = new ATMEntity(1l, "123456789111", "debit", LocalDate.now(), 123, 1234, bankAccount);

		atmOptional = Optional.of(atm);
		Optional<BankAccountEntity> bankAccountOptional = Optional.of(bankAccount);
		;
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1000));
		request.setCardNo("123456789111");
		request.setPin(1234);
		String transactionId = UUID.randomUUID().toString();

		TransactionEntity transaction = TransactionEntity.builder().account(bankAccount).amount(new BigDecimal(1))
				.date(LocalDate.now()).referenceNumber(UUID.randomUUID().toString()).transactionId(transactionId)
				.transactionType(TransactionType.WITHDRAW).build();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);
		when(bankAccountService.findByAccountNumber("123123123123")).thenReturn(bankAccountOptional);

		assertThrows(InsufficientFundsException.class, () -> {
			fundsService.fundsAction(request, TransactionType.WITHDRAW);
		});
	}

	@Test
	public void testWithdrawInvalidCard() {
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		request.setCardNo("12345678911");
		request.setPin(1234);

		atmOptional = Optional.empty();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);

		assertThrows(InvalidCardException.class, () -> {
			fundsService.fundsAction(request, TransactionType.WITHDRAW);
		});

	}

	@Test
	public void testWithdrawInvalidAccount() {
		request = new TransactionRequest();
		request.setAmount(new BigDecimal(1));
		UserEntity userEntity = UserEntity.builder().email("k@gmail.com").firstName("k").lastName("m")
				.identificationNumber("adv").build();

		bankAccount = BankAccountEntity.builder().actualBalance(new BigDecimal(1000))
				.availableBalance(new BigDecimal(1000)).number("123123123123").status(AccountStatus.ACTIVE)
				.type(AccountType.SAVINGS_ACCOUNT).user(userEntity).build();

		ATMEntity atm = new ATMEntity(1l, "123456789111", "debit", LocalDate.now(), 123, 1234, bankAccount);

		atmOptional = Optional.of(atm);
		Optional<BankAccountEntity> bankAccountOptional = Optional.empty();

		when(atmService.getCardDetails(request)).thenReturn(atmOptional);
		when(bankAccountService.findByAccountNumber("123123123123")).thenReturn(bankAccountOptional);

		assertThrows(InactiveAccountException.class, () -> {
			fundsService.fundsAction(request, TransactionType.WITHDRAW);
		});

	}

}
