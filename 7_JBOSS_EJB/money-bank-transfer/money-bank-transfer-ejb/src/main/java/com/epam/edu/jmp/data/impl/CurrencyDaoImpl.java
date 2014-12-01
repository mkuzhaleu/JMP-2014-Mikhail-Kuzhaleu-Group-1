package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.CurrencyDao;
import com.epam.edu.jmp.model.Currency;


@Stateless
public class CurrencyDaoImpl extends GenericDaoImpl<Currency> implements CurrencyDao {

  public CurrencyDaoImpl(){}

  @Inject
  public CurrencyDaoImpl(EntityManager em) {
    super(em);
  }

}
