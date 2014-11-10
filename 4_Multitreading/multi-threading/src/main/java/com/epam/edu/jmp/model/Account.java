package com.epam.edu.jmp.model;

public class Account {
    private int bankCode;
    private String number;
    private Currency currency;
    private double moneyValue;

    public Account(int bankCode, String number, Currency currency,
            double moneyValue) {
        super();
        this.bankCode = bankCode;
        this.number = number;
        this.currency = currency;
        this.moneyValue = moneyValue;
    }

    public int getBankCode() {
        return bankCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getMoneyValue() {
        return moneyValue;
    }

    public void setMoneyValue(double moneyValue) {
        this.moneyValue = moneyValue;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account [bankCode=" + bankCode + ", number=" + number
                + ", currency=" + currency + ", moneyValue=" + moneyValue + "]";
    }

}
