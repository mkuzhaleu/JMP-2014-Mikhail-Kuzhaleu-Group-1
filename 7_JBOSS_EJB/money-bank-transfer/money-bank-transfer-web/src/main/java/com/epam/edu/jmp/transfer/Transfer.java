package com.epam.edu.jmp.transfer;

import java.io.Serializable;

import com.epam.edu.jmp.model.Account;

public class Transfer implements Serializable {

	private static final long serialVersionUID = 1623141032675087410L;
	private Account from;
	private Account to;
	private double amount;
	/**
	 * @return the from
	 */
	public Account getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Account from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public Account getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(Account to) {
		this.to = to;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
