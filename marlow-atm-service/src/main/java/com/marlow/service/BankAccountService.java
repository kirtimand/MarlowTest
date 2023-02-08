package com.marlow.service;

import java.util.Optional;

import com.marlow.model.AccountStatus;
import com.marlow.model.entity.BankAccountEntity;

public interface BankAccountService {
    Optional<BankAccountEntity> findByAccountNumber(String number);
    
    void save(BankAccountEntity accountEntity);
}
