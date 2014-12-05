package com.epam.edu.jmp.util;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransferException;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.Currency;
import com.epam.edu.jmp.model.ExchangeRate;

public class BankUtils {

    private BankUtils() {
    }

    public static boolean checkEnoughMoney(Account from, double amount) {
        if (from.getMoneyValue() >= amount) {
            return true;
        } else {
            throw new NotEnoughMoneyToPerformMoneyTransferException(
                    "Can't perform money transfer of " + amount
                            + from.getCurrency().getShortCode()
                            + " from account " + from);
        }
    }

    public static ExchangeRate getCurrencyExchangeRate(Bank bank,
            Currency from, Currency to)
            throws CurrenciesExchangeIsNotSupportedExceprion {

        for (ExchangeRate rate : bank.getRates()) {
            if (rate.getFrom().getCurrencyId() == from.getCurrencyId()
                    && rate.getTo().getCurrencyId() == to.getCurrencyId()) {
                return rate;
            }
        }

        throw new CurrenciesExchangeIsNotSupportedExceprion("Exchange from ["
                + from + "] to [" + to + "] is not supported in bank " + bank);
    }

    public static boolean checkBankAccounts(Bank bank, Account from, Account to)
            throws CurrenciesExchangeIsNotSupportedExceprion {

        if (from.getBank().getBankId() == bank.getBankId()) {
            return true;
        }

        throw new CurrenciesExchangeIsNotSupportedExceprion(
                "Exchange between accounts is supported ONLY within the same bank");
    }

}
