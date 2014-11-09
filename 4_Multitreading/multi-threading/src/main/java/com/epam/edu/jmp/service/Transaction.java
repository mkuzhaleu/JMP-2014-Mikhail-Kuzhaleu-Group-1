package com.epam.edu.jmp.service;

import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Currency;

public class Transaction implements Runnable {
	private Account accountFrom;
	private Account accountTo;
	private Currency currency;
	private double amount;

	public Transaction(Account acc1, Account acc2, Currency curr, double amount) {
		this.accountFrom = acc1;
		this.accountTo = acc2;
		this.currency = curr;
		this.amount = amount;
	}
	
	@Override
	public void run() {
		MoneyTransferService.transferMoney(accountFrom, accountTo, amount);
	}

}
