package com.marlow.model.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class TransactionRequest {
	@NotNull(message = "Card Number must be 12 characters")
	@Size(min = 12, max = 12,message = "Card Number must be 12 characters")
	private String cardNo;
	@NotNull(message = "ATM Pin is required")
	@Range(min = 1l, message = "Please enter valid PIN")
	private int pin;
	@NotNull(message = "Please enter valid amount")
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 6, fraction = 2,message="Amount should be greater then 0 and less then 100000")
	private BigDecimal amount;
}
