package com.marlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlow.model.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {}
