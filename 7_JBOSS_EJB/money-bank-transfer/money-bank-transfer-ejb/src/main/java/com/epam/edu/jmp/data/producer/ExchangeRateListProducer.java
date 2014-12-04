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

import com.epam.edu.jmp.model.ExchangeRate;

@RequestScoped
public class ExchangeRateListProducer {
   @Inject
   private EntityManager em;

   private List<ExchangeRate> exchangeRates;

   @Produces
   @Named
   public List<ExchangeRate> getExchangeRates() {
      return exchangeRates;
   }

   public void onExchangeRateListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final ExchangeRate exchangeRate) {
      retrieveAllExchangeRatesOrderedByName();
   }

   @PostConstruct
   public void retrieveAllExchangeRatesOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<ExchangeRate> criteria = cb.createQuery(ExchangeRate.class);
      Root<ExchangeRate> exchangeRate = criteria.from(ExchangeRate.class);
      criteria.select(exchangeRate).orderBy(cb.asc(exchangeRate.get("bank")), cb.asc(exchangeRate.get("from")));
      exchangeRates = em.createQuery(criteria).getResultList();
   }
}
