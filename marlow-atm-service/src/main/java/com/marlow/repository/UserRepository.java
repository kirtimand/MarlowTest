package com.marlow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marlow.model.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdentificationNumber(String identificationNumber);
}
