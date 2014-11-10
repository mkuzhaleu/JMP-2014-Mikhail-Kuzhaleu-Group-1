package com.epam.edu.jmp.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.service.MoneyTransferService;

public class MoneyTransferTransaction implements Runnable {

    private final static Logger LOG = LoggerFactory.getLogger(MoneyTransferTransaction.class);

    private Account accountFrom;
    private Account accountTo;
    private double amount;

    public MoneyTransferTransaction(Account acc1, Account acc2, double amount) {
        this.accountFrom = acc1;
        this.accountTo = acc2;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            MoneyTransferService.transferMoney(accountFrom, accountTo, amount);
        } catch (NotEnoughMoneyToPerformMoneyTransferException e) {
            LOG.warn(e.getMessage());
        }
    }

}
