package com.epam.edu.jmp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

//public enum Currency {
@Entity
@XmlRootElement
@Table(name = "Currency")
public class Currency implements Serializable {

	private static final long serialVersionUID = 4162740133802742320L;
	// USD("Dollar USA", "$", 841),
	// EUR("EURO", "€", 978),
	// RUR("Russian Ruble", "р", 643);

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String shortCode;

	@NotNull
	@NotEmpty
	private int code;

	// private Currency(String name, String shortCode, int code) {
	// this.name = name;
	// this.shortCode = shortCode;
	// this.code = code;
	// }

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	public String getShortCode() {
		return shortCode;
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param shortCode
	 *            the shortCode to set
	 */
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	// public Currency getCurrencyByCode(int code) {
	// for(Currency cur : Currency.values()) {
	// if (cur.getCode() == code) {
	// return cur;
	// }
	// }
	// return null;
	// }

}
