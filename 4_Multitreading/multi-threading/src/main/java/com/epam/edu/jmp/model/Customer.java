package com.epam.edu.jmp.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Customer {
	private String firstName;
	private String lastName;
	private List<Account> accounts = Lists.newArrayList();
	
	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public List<Account> addAccount(Account account) {
		accounts.add(account);
		return accounts;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", accounts=" + accounts + "]";
	}

}
