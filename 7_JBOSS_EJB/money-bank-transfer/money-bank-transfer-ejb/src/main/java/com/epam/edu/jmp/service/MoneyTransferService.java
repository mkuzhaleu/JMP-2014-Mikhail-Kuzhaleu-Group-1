package com.epam.edu.jmp.service;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransfer;
import com.epam.edu.jmp.model.Account;

public class MoneyTransferService {
	
	public void transferMoney(Account from, Account to, double amount) throws NotEnoughMoneyToPerformMoneyTransfer {
		if (checkEnoughMoney(from, amount) ) {
			doMoneyTransfer(from, to, amount);
			System.out.println("Transfered " + amount + " from " + from + " to " + to);
		}
	}

	private synchronized void doMoneyTransfer(Account from, Account to, double amount) {
		from.setMoneyValue(from.getMoneyValue() - amount);
		to.setMoneyValue(to.getMoneyValue() + amount);	
	}

	private boolean checkEnoughMoney(Account from, double amount) {
		if (from.getMoneyValue() >= amount ) {
			return true;
		} else {
			throw new NotEnoughMoneyToPerformMoneyTransfer("Can't perform money transfer of " + amount + from.getCurrency().getShortCode() + " from account " + from);
		}
	}

}
