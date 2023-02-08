package com.marlow.kafka;

import java.util.function.Supplier;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marlow.model.dto.AccountBalance;
@Configuration
public class KafkaConfiguration {
	

	@Bean
	public Supplier<AccountBalance> producer() {
        return () ->null ;
	}
}
