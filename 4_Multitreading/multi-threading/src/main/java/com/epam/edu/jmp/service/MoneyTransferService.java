package com.epam.edu.jmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.util.BankUtils;

public class MoneyTransferService {

    private final static Logger LOG = LoggerFactory.getLogger(MoneyTransferService.class);

    public static void transferMoney(Account from, Account to, double amount)
            throws NotEnoughMoneyToPerformMoneyTransferException {
        synchronized (from) {
            if (BankUtils.checkEnoughMoney(from, amount)) {
                doMoneyTransfer(from, to, amount);
                LOG.info("Transfered " + amount + " from " + from
                        + " to " + to);
            }
        }
    }

    private static void doMoneyTransfer(Account from, Account to,
            double amount) {
        from.setMoneyValue(from.getMoneyValue() - amount);
        to.setMoneyValue(to.getMoneyValue() + amount);
    }

}
