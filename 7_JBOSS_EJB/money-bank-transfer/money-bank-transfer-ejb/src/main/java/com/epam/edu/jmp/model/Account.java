package com.epam.edu.jmp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@XmlRootElement
@Table(name = "Account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 458681994546754038L;

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "code", insertable=false, updatable=false)
	private Bank bank;
	
	@NotNull
	@NotEmpty
	private String number;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "code")
	private Currency currency;
	
	@NotNull
	@NotEmpty
	private double moneyValue;

	@ManyToOne(optional=false)
	@JoinColumn(name = "id", insertable=false, updatable=false)
	private Customer customer;
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return id;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int id) {
		this.id = id;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the moneyValue
	 */
	public double getMoneyValue() {
		return moneyValue;
	}

	/**
	 * @param moneyValue the moneyValue to set
	 */
	public void setMoneyValue(double moneyValue) {
		this.moneyValue = moneyValue;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
//	public Account(int bankCode, String number, Currency currency, double moneyValue) {
//		super();
//		this.bankCode = bankCode;
//		this.number = number;
//		this.currency = currency;
//		this.moneyValue = moneyValue;
//	}


}
