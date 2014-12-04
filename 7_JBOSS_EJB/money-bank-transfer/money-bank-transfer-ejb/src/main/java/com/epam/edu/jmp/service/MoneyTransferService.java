package com.epam.edu.jmp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransfer;
import com.epam.edu.jmp.model.Account;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MoneyTransferService {
	
	@Inject
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void transferMoney(Account from, Account to, double amount) throws NotEnoughMoneyToPerformMoneyTransfer {
		if (checkEnoughMoney(from, amount) ) {
			doMoneyTransfer(from, to, amount);
			System.out.println("Transfered " + amount + " from " + from + " to " + to);
		}
	}

	private void doMoneyTransfer(Account from, Account to, double amount) {
		from.setMoneyValue(from.getMoneyValue() - amount);
		em.merge(from);
		to.setMoneyValue(to.getMoneyValue() + amount);
		em.merge(to);
	}

	private boolean checkEnoughMoney(Account from, double amount) {
		if (from.getMoneyValue() >= amount ) {
			return true;
		} else {
			throw new NotEnoughMoneyToPerformMoneyTransfer("Can't perform money transfer of " + amount + from.getCurrency().getShortCode() + " from account " + from);
		}
	}

}
