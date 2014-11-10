package com.epam.edu.jmp.service;

import com.epam.edu.jmp.exception.CurrenciesExchangeIsNotSupportedExceprion;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.model.Bank;
import com.epam.edu.jmp.model.Currency;
import com.epam.edu.jmp.model.ExchangeRate;

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

    public static double capitalization(Bank bank) throws CurrenciesExchangeIsNotSupportedExceprion {
        double sum = 0;
        for (Account account : bank.getAccounts()) {
            double moneyValue = account.getMoneyValue();
            double convertedToUSD = convertMoneyFrom(bank,
                    account.getCurrency(), Currency.USD, moneyValue);

            sum += account.getMoneyValue();
        }
        return sum;
    }

    private static double convertMoneyFrom(Bank bank, Currency currencyFrom,
            Currency currencyTo, double moneyValue)
            throws CurrenciesExchangeIsNotSupportedExceprion {
        ExchangeRate rate = getExchangRate(bank, currencyFrom, currencyTo);
        return Math.round(moneyValue * rate.getRate() * 100) / 100.d;
    }

    public static ExchangeRate getExchangRate(Bank bank, Currency currencyFrom,
            Currency currencyTo)
            throws CurrenciesExchangeIsNotSupportedExceprion {
        // TODO Auto-generated method stub
        for (ExchangeRate rate : bank.getRates()) {
            if (rate.getFrom() == currencyFrom && rate.getTo() == currencyTo) {
                return rate;
            }
        }

        throw new CurrenciesExchangeIsNotSupportedExceprion(bank
                + " is not supports exchange from " + currencyFrom + " to "
                + currencyTo);
    }
}
