package com.epam.edu.jmp.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.epam.edu.jmp.exception.NotEnoughMoneyToPerformMoneyTransfer;
import com.epam.edu.jmp.service.MoneyTransferService;
import com.epam.edu.jmp.transfer.Transfer;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class TransferController {

   @Inject
   private FacesContext facesContext;
   
   @Inject
   private MoneyTransferService moneyTransferService;

   private Transfer newTransfer;
   
   @Produces
   @Named
   public Transfer getNewTransfer() {
      return newTransfer;
   }

   public void transfer() {
	  try {
	      moneyTransferService.transferMoney(newTransfer.getFrom(), newTransfer.getTo(), newTransfer.getAmount());
          facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Transfered!", "Transfer successful"));
	  } catch (NotEnoughMoneyToPerformMoneyTransfer e) {
		  facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), "Transfer failed"));
	  }
      initNewTransfer();
   }

   @PostConstruct
   public void initNewTransfer() {
	   newTransfer = new Transfer();
   }
}
