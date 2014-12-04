package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.ExchangeRateDao;
import com.epam.edu.jmp.model.ExchangeRate;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ExchangeRateController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private ExchangeRateDao exchangeRateDao;

   private ExchangeRate newExchangeRate;
   
   @Produces
   @Named
   public ExchangeRate getNewExchangeRate() {
      return newExchangeRate;
   }

   public void register() throws Exception {
      exchangeRateDao.save(newExchangeRate);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewExchangeRate();
   }

   @PostConstruct
   public void initNewExchangeRate() {
      newExchangeRate = new ExchangeRate();
   }
}
