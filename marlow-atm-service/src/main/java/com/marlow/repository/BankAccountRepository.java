package com.marlow.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.marlow.model.AccountStatus;
import com.marlow.model.entity.BankAccountEntity;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
	@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<BankAccountEntity> findByNumberAndStatus(String number,AccountStatus status);
}
