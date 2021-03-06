package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.ExchangeRateDao;
import com.epam.edu.jmp.model.ExchangeRate;

@Stateless
public class ExchangeRateDaoImpl extends GenericDaoImpl<ExchangeRate> implements
		ExchangeRateDao {

	public ExchangeRateDaoImpl() {
	}

	@Inject
	public ExchangeRateDaoImpl(EntityManager em) {
		super(em);
	}

	@Inject
	private Event<ExchangeRate> exchangeRateEventSrc;

	@Override
	public void save(ExchangeRate exchangeRate) {
		super.save(exchangeRate);
		exchangeRateEventSrc.fire(exchangeRate);
	}

}
