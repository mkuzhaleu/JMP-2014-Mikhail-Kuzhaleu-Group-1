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

import com.epam.edu.jmp.model.Currency;

@RequestScoped
public class CurrencyListProducer {
   @Inject
   private EntityManager em;

   private List<Currency> currencies;

   @Produces
   @Named
   public List<Currency> getCurrencies() {
      return currencies;
   }

   public void onCurrencyListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Currency currency) {
      retrieveAllCurrenciesOrderedByCode();
   }

   @PostConstruct
   public void retrieveAllCurrenciesOrderedByCode() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Currency> criteria = cb.createQuery(Currency.class);
      Root<Currency> currency = criteria.from(Currency.class);
      criteria.select(currency).orderBy(cb.asc(currency.get("code")));
      currencies = em.createQuery(criteria).getResultList();
   }
}
