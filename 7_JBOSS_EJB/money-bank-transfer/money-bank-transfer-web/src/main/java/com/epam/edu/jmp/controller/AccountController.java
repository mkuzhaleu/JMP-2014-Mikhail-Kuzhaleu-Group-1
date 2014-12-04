package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.AccountDao;
import com.epam.edu.jmp.model.Account;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class AccountController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private AccountDao accountDao;

   private Account newAccount;
   
   @Produces
   @Named
   public Account getNewAccount() {
      return newAccount;
   }

   public void register() throws Exception {
      accountDao.save(newAccount);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewAccount();
   }

   @PostConstruct
   public void initNewAccount() {
      newAccount = new Account();
   }
}
