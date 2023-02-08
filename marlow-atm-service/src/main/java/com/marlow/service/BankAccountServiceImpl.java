package com.marlow.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlow.model.AccountStatus;
import com.marlow.model.entity.BankAccountEntity;
import com.marlow.repository.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;


	@Override
	public Optional<BankAccountEntity> findByAccountNumber(String number) {
		return bankAccountRepository
				.findByNumberAndStatus(number, AccountStatus.ACTIVE);
	}


	@Override
	public void save(BankAccountEntity accountEntity) {
		bankAccountRepository.saveAndFlush(accountEntity);		
	}


}