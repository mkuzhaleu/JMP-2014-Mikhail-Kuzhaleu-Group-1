package com.epam.edu.jmp.service;

import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;

public class BankService {
    public static Account getAccountByNumber(Bank bank, String accountNumber) {
        Account account = null;

        for (Account accnt : bank.getAccounts()) {
            if (accnt.getNumber().equals(accountNumber)) {
                account = accnt;
                break;
            }
        }

        return account;
    }

    public static double capitalization(Bank bank) {
        double sum = 0;
        for (Account account : bank.getAccounts()) {
            sum += account.getMoneyValue();
        }
        return sum;
    }
}
