package com.epam.edu.jmp.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.epam.edu.jmp.model.Account;

@RequestScoped
public class AccountListProducer {
   @Inject
   private EntityManager em;

   private List<Account> accounts;

   @Produces
   @Named
   public List<Account> getAccounts() {
      return accounts;
   }

   public void onAccountListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Account account) {
      retrieveAllAccountsOrderedByName();
   }

   @PostConstruct
   public void retrieveAllAccountsOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Account> criteria = cb.createQuery(Account.class);
      Root<Account> account = criteria.from(Account.class);
      criteria.select(account).orderBy(cb.asc(account.get("bank")), cb.asc(account.get("number")));
      accounts = em.createQuery(criteria).getResultList();
   }
}
