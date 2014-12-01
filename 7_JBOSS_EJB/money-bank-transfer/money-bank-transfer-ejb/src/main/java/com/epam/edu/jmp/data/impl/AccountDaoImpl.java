package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.AccountDao;
import com.epam.edu.jmp.model.Account;


@Stateless
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao {

  public AccountDaoImpl(){}

  @Inject
  public AccountDaoImpl(EntityManager em) {
    super(em);
  }

}
