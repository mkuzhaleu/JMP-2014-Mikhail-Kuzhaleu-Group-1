package com.epam.edu.jmp.exception;

public class NotEnoughMoneyToPerformMoneyTransferException extends
        RuntimeException {

    private static final long serialVersionUID = 5564764963730433881L;

    public NotEnoughMoneyToPerformMoneyTransferException(String message,
            Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyToPerformMoneyTransferException(String message) {
        super(message);
    }

}
