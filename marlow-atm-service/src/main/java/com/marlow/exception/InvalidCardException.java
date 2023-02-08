package com.marlow.exception;

public class InvalidCardException extends SimpleBankingGlobalException {
    public InvalidCardException(String message, String code) {
        super(message, code);
    }
}
