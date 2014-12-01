package com.epam.edu.jmp.data.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.CustomerDao;
import com.epam.edu.jmp.model.Customer;

@Stateless
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {

  @SuppressWarnings("unused")
  private final EntityManager em;

  public  CustomerDaoImpl() {
    super(null);
    this.em = null;
  }

  @Inject
  public CustomerDaoImpl(EntityManager em) {
    super(em);
    this.em = em;
  }

}
