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
	private List<Customer> customers = Lists.newArrayList();
	
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<ExchangeRate> addExchangeRate(ExchangeRate exchangeRate) {
		getRates().add(exchangeRate);
		return rates;
	}
	
	public Account openNewAccount(Currency currency, double amount) {
		Account account = new Account(code, getAccountNumber(currency), currency, amount);
		return account;
	}

	private String getAccountNumber(Currency currency) {
		return currency.getCode() + StringUtils.leftPad(String.valueOf(accountNumber.getAndIncrement()), 10, '0');
	}
	
}
