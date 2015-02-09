package com.epam.edu.jmp.exception;

public class CurrenciesExchangeIsNotSupportedExceprion extends Exception {

    private static final long serialVersionUID = -2336169019318872725L;

    public CurrenciesExchangeIsNotSupportedExceprion(String message, Throwable cause) {
            super(message, cause);
    }

    public CurrenciesExchangeIsNotSupportedExceprion(String message) {
            super(message);
    }
}
