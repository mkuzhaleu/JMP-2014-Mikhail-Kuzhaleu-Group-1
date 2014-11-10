package com.epam.edu.jmp.service;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.util.BankUtils;

public class MoneyTransferService {

    public static void transferMoney(Account from, Account to, double amount)
            throws NotEnoughMoneyToPerformMoneyTransferException {
        if (BankUtils.checkEnoughMoney(from, amount)) {
            doMoneyTransfer(from, to, amount);
            System.out.println("Transfered " + amount + " from " + from
                    + " to " + to);
        }
    }

    private static synchronized void doMoneyTransfer(Account from, Account to,
            double amount) {
        from.setMoneyValue(from.getMoneyValue() - amount);
        to.setMoneyValue(to.getMoneyValue() + amount);
    }

}
