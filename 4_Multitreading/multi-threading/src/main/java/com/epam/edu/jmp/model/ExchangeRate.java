package com.epam.edu.jmp.model;

public class ExchangeRate {
	private Currency from;
	private Currency to;
	private double rate;
	
	public ExchangeRate(Currency from, Currency to, double rate) {
		this.from = from;
		this.to = to;
		this.rate = rate;
	}

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

}
