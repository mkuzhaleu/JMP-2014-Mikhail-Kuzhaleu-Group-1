package com.epam.edu.jmp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
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

	private static final long serialVersionUID = 7160413125275449987L;

	@Id
	@GeneratedValue
	private long bankId;

	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExchangeRate> rates = Sets.newLinkedHashSet();

	@OneToMany(mappedBy = "bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Account> accounts = Sets.newLinkedHashSet();

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

	/**
	 * @return the bankId
	 */
	public long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bankId ^ (bankId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (bankId != other.bankId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
