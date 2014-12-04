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

@Entity
@XmlRootElement
@Table(name = "Exchange_Rate")
public class ExchangeRate implements Serializable {

	private static final long serialVersionUID = 8660116316150357624L;

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "bankId", insertable=false, updatable=false)
	private Bank bank;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id")
	private Currency from;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id")
	private Currency to;

	@NotNull
	private double rate;

	// public ExchangeRate(Currency from, Currency to, double rate) {
	// this.from = from;
	// this.to = to;
	// this.rate = rate;
	// }

	public Currency getFrom() {
		return from;
	}

	public void setFrom(Currency from) {
		this.from = from;
	}

	public Currency getTo() {
		return to;
	}

	public void setTo(Currency to) {
		this.to = to;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
