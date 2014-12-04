package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.CurrencyDao;
import com.epam.edu.jmp.model.Currency;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class CurrencyController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private CurrencyDao currencyDao;

   private Currency newCurrency;

   @Produces
   @Named
   public Currency getNewCurrency() {
      return newCurrency;
   }

   public void register() throws Exception {
      currencyDao.save(newCurrency);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewCurrency();
   }

   @PostConstruct
   public void initNewCurrency() {
	   newCurrency = new Currency();
   }
}
