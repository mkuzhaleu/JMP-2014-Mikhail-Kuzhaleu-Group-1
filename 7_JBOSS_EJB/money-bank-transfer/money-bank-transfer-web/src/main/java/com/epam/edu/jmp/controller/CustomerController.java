package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.data.CustomerDao;
import com.epam.edu.jmp.model.Customer;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class CustomerController {

   @Inject
   private FacesContext facesContext;

   @Inject
   private CustomerDao customerDao;

   private Customer newCustomer;

   @Produces
   @Named
   public Customer getNewCustomer() {
      return newCustomer;
   }

   public void register() throws Exception {
      customerDao.save(newCustomer);
      facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
      initNewCustomer();
   }

   @PostConstruct
   public void initNewCustomer() {
      newCustomer = new Customer();
   }
}
