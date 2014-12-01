package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.BankDao;
import com.epam.edu.jmp.model.Bank;


@Stateless
public class BankDaoImpl extends GenericDaoImpl<Bank> implements BankDao {

  public BankDaoImpl() {}

  @Inject
  public BankDaoImpl(EntityManager em) {
    super(em);
  }

}
