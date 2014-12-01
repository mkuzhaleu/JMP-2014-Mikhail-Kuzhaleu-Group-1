package com.epam.edu.jmp.exception;

public class NotEnoughMoneyToPerformMoneyTransfer extends RuntimeException {

	private static final long serialVersionUID = 5564764963730433881L;
	
	public NotEnoughMoneyToPerformMoneyTransfer(String message, Throwable cause) {
		super(message, cause);
	}

	public NotEnoughMoneyToPerformMoneyTransfer(String message) {
		super(message);
	}

}
