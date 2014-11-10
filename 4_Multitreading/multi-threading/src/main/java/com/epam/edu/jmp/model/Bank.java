package com.epam.edu.jmp.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public class Bank {

    private static AtomicInteger accountNumber = new AtomicInteger(0);

    private int code;
    private String name;
    private List<ExchangeRate> rates = Lists.newArrayList();
    private List<Account> accounts = Lists.newArrayList();

    public Bank(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates = rates;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setCustomers(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<ExchangeRate> addExchangeRate(ExchangeRate exchangeRate) {
        getRates().add(exchangeRate);
        return rates;
    }

    public Account openNewAccount(Currency currency, double amount) {
        Account account = new Account(code, getAccountNumber(currency),
                currency, amount);
        accounts.add(account);
        return account;
    }

    private String getAccountNumber(Currency currency) {
        return currency.getCode()
                + StringUtils.leftPad(
                        String.valueOf(accountNumber.getAndIncrement()), 10,
                        '0');
    }

    @Override
    public String toString() {
        return "Bank [code=" + code + ", name=" + name + "]";
    }

    public void addAllExchangeRate(List<ExchangeRate> rates) {
        rates.addAll(rates);
    }

}
