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
@Table(name = "Customer")
public class Customer implements Serializable  {

	private static final long serialVersionUID = -2368502106660667651L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotNull
	@NotEmpty
	private String firstName;
	
	@NotNull
	@NotEmpty
	private String lastName;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
	private Set<Account> accounts = Sets.newIdentityHashSet();
	
//	public Customer(String firstName, String lastName) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}

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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Set<Account> addAccount(Account account) {
		accounts.add(account);
		return accounts;
	}

//	@Override
//	public String toString() {
//		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", accounts=" + accounts + "]";
//	}

}
