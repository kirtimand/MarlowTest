package com.marlow;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import com.marlow.model.dto.AccountBalance;

@SpringBootApplication
@EnableFeignClients
@EnableRetry 
public class MarlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarlowApplication.class, args);
	}
	
	@Bean
	public Supplier<AccountBalance> fizzBuzzProducer() {
        return () -> new AccountBalance();
	}

}
