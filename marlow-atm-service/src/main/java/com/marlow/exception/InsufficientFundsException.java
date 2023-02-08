package com.marlow.exception;

public class InsufficientFundsException extends SimpleBankingGlobalException {
    public InsufficientFundsException(String message, String code) {
        super(message, code);
    }
}
