package com.epam.edu.jmp.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.service.MoneyExchangeService;

public class MoneyExchangeTransaction implements Runnable {

    private final static Logger LOG = LoggerFactory.getLogger(MoneyExchangeTransaction.class);

    private Bank bank;
    private Account accountFrom;
    private Account accountTo;
    private double amount;

    public MoneyExchangeTransaction(Bank bank, Account acc1, Account acc2, double amount) {
        this.bank = bank;
        this.accountFrom = acc1;
        this.accountTo = acc2;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            MoneyExchangeService.exchangeMoney(bank, accountFrom, accountTo, amount);
        } catch (NotEnoughMoneyToPerformMoneyTransferException
                | CurrenciesExchangeIsNotSupportedExceprion e) {
            LOG.warn(e.getMessage());
        }
    }

}
