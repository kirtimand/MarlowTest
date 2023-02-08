package com.marlow.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marlow.model.entity.ATMEntity;

@Repository
public interface ATMRepository extends JpaRepository<ATMEntity, Long> {
    Optional<ATMEntity> findByCardNumberAndPinAndExpiryGreaterThanEqual(String cardNumber,int pin,LocalDate expiry);
}
