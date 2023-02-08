package com.marlow.exception;

public class InactiveAccountException extends SimpleBankingGlobalException {
    public InactiveAccountException(String message, String code) {
        super(message, code);
    }
}
