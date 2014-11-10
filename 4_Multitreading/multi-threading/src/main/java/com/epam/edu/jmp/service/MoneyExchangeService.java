package com.epam.edu.jmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.ExchangeRate;
import com.epam.edu.jmp.util.BankUtils;

public class MoneyExchangeService {

    private final static Logger LOG = LoggerFactory.getLogger(MoneyExchangeService.class);

    public static void exchangeMoney(Bank bank, Account from, Account to, double amount)
            throws NotEnoughMoneyToPerformMoneyTransferException,
                   CurrenciesExchangeIsNotSupportedExceprion
            {
        ExchangeRate rate = BankUtils.getCurrencyExchangeRate(bank, from.getCurrency(), to.getCurrency());
        boolean oneBankAccounts = BankUtils.checkBankAccounts(bank, from, to);
        synchronized (from) {
            if (oneBankAccounts && BankUtils.checkEnoughMoney(from, amount)) {
                double convertedValue = doMoneyExchange(from, to, amount, rate);
                LOG.info("Exchanged " + amount + from.getCurrency().getShortCode() + " from " + from
                        + " to " + to + ". Loaded value " + convertedValue + to.getCurrency().getShortCode() +
                        ". Exchange rate " + rate.getRate());
            }
        }
    }

    private static double doMoneyExchange(Account from, Account to,
            double amount, ExchangeRate rate) {
        from.setMoneyValue(from.getMoneyValue() - amount);
        double convertedValue = Math.round(amount * rate.getRate() * 100.0) / 100.0d;
        to.setMoneyValue(to.getMoneyValue() + convertedValue);
        return convertedValue;
    }

}
