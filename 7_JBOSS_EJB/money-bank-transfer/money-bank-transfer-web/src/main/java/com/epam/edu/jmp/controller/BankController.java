package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.BankDao;
import com.epam.edu.jmp.model.Bank;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class BankController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private BankDao bankDao;

   private Bank newBank;

   @Produces
   @Named
   public Bank getnewBank() {
      return newBank;
   }

   public void register() throws Exception {
      bankDao.save(newBank);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewBank();
   }

   @PostConstruct
   public void initNewBank() {
	   newBank = new Bank();
   }
}
