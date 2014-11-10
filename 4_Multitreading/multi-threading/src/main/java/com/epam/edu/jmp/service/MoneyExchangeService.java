package com.epam.edu.jmp.service;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.ExchangeRate;
import com.epam.edu.jmp.util.BankUtils;

public class MoneyExchangeService {

    public static void exchangeMoney(Bank bank, Account from, Account to, double amount)
            throws NotEnoughMoneyToPerformMoneyTransferException,
                   CurrenciesExchangeIsNotSupportedExceprion
            {
        ExchangeRate rate = BankUtils.getCurrencyExchangeRate(bank, from.getCurrency(), to.getCurrency());
        boolean oneBankAccounts = BankUtils.checkBankAccounts(bank, from, to);
        if (oneBankAccounts && BankUtils.checkEnoughMoney(from, amount)) {
            double convertedValue = doMoneyExchange(from, to, amount, rate);
            System.out.println("Exchanged " + amount + from.getCurrency().getShortCode() + " from " + from
                    + " to " + to + ". Loaded value " + convertedValue + to.getCurrency().getShortCode());
        }
    }

    private static synchronized double doMoneyExchange(Account from, Account to,
            double amount, ExchangeRate rate) {
        from.setMoneyValue(from.getMoneyValue() - amount);
        double convertedValue = Math.round(amount * rate.getRate() * 100.0) / 100.0d;
        to.setMoneyValue(to.getMoneyValue() + convertedValue);
        return convertedValue;
    }

}
