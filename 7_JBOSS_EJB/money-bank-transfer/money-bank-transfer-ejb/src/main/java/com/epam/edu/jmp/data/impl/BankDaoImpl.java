package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.BankDao;
import com.epam.edu.jmp.model.Bank;

@Stateless
public class BankDaoImpl extends GenericDaoImpl<Bank> implements BankDao {

	public BankDaoImpl() {
	}

	@Inject
	public BankDaoImpl(EntityManager em) {
		super(em);
	}

	@Inject
	private Event<Bank> bankEventSrc;

	@Override
	public void save(Bank bank) {
		// TODO Auto-generated method stub
		super.save(bank);
		bankEventSrc.fire(bank);
	}

}
