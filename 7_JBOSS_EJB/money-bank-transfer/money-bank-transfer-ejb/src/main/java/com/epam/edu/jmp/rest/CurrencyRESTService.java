package com.epam.edu.jmp.rest;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.epam.edu.jmp.data.AccountDao;
import com.epam.edu.jmp.model.Account;
import com.epam.edu.jmp.service.MoneyTransferService;

@Path("/accounts")
@RequestScoped
public class CurrencyRESTService {

  @Inject
  private AccountDao accountDao;
  
  @Inject
  private MoneyTransferService moneyTransferService;

  private final Logger log = Logger.getLogger("CurrencyRESTService");

  @GET
  @Path("from/{fromId}/to/{toId}/amount/{amount}")
  @Produces("text/json")
  public String convertCurrencies(@PathParam("fromId") long fromId,
		  						  @PathParam("toId") long toId, 
		  						  @PathParam("amount") double amount) {

    log.info("Transfering for from " + fromId + " to " + toId + " amount " + amount);

    Account fromAccount = accountDao.find(fromId);
    Account toAccount = accountDao.find(toId);
    
    moneyTransferService.transferMoney(fromAccount, toAccount, amount);
    
    return "Money transfered";
  }
}
