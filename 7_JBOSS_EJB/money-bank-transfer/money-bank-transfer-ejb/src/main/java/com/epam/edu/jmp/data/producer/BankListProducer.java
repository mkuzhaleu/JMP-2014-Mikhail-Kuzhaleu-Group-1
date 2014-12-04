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

import com.epam.edu.jmp.model.Bank;

@RequestScoped
public class BankListProducer {
   @Inject
   private EntityManager em;

   private List<Bank> banks;

   @Produces
   @Named
   public List<Bank> getBanks() {
      return banks;
   }

   public void onBankListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Bank bank) {
      retrieveAllBanksOrderedByCode();
   }

   @PostConstruct
   public void retrieveAllBanksOrderedByCode() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Bank> criteria = cb.createQuery(Bank.class);
      Root<Bank> bank = criteria.from(Bank.class);
      criteria.select(bank).orderBy(cb.asc(bank.get("name")));
      banks = em.createQuery(criteria).getResultList();
   }
}
