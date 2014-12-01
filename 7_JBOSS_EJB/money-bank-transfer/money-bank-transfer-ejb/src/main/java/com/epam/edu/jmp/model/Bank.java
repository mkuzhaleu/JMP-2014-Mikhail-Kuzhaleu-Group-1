package com.epam.edu.jmp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.Sets;

@Entity
@XmlRootElement
@Table(name = "Bank")
public class Bank implements Serializable {

	// private static AtomicInteger accountNumber = new AtomicInteger(0);

	private static final long serialVersionUID = 7160413125275449987L;
	
	@Id
	@GeneratedValue
	private int code;

	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
	private Set<ExchangeRate> rates = Sets.newLinkedHashSet();

	@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
	private Set<Account> accounts = Sets.newLinkedHashSet();

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rates
	 */
	public Set<ExchangeRate> getRates() {
		return rates;
	}

	/**
	 * @param rates the rates to set
	 */
	public void setRates(Set<ExchangeRate> rates) {
		this.rates = rates;
	}

	/**
	 * @return the accounts
	 */
	public Set<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	// public Bank(int code, String name) {
	// this.code = code;
	// this.name = name;
	// }

	// public List<ExchangeRate> addExchangeRate(ExchangeRate exchangeRate) {
	// getRates().add(exchangeRate);
	// return rates;
	// }
	//
	// public Account openNewAccount(Currency currency, double amount) {
	// Account account = new Account(code, getAccountNumber(currency), currency,
	// amount);
	// accounts.add(account);
	// return account;
	// }
	//
	// private String getAccountNumber(Currency currency) {
	// return currency.getCode() +
	// StringUtils.leftPad(String.valueOf(accountNumber.getAndIncrement()), 10,
	// '0');
	// }
	//
	// @Override
	// public String toString() {
	// return "Bank [code=" + code + ", name=" + name + ", rates=" + rates +
	// ", accounts=" + accounts + "]";
	// }

}
