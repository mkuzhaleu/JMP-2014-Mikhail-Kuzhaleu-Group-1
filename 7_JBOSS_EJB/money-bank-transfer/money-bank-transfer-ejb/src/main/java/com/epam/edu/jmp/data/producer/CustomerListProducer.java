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

import com.epam.edu.jmp.model.Customer;

@RequestScoped
public class CustomerListProducer {
   @Inject
   private EntityManager em;

   private List<Customer> customers;

   @Produces
   @Named
   public List<Customer> getCustomers() {
      return customers;
   }

   public void onCustomerListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Customer customer) {
      retrieveAllCustomersOrderedByName();
   }

   @PostConstruct
   public void retrieveAllCustomersOrderedByName() {
      CriteriaBuilder cb = em.getCriteriaBuilder();
      CriteriaQuery<Customer> criteria = cb.createQuery(Customer.class);
      Root<Customer> customer = criteria.from(Customer.class);
      criteria.select(customer).orderBy(cb.asc(customer.get("firstName")), cb.asc(customer.get("lastName")));
      customers = em.createQuery(criteria).getResultList();
   }
}
