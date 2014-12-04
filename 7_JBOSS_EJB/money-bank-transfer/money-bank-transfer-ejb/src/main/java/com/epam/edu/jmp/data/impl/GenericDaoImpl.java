package com.epam.edu.jmp.data.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.epam.edu.jmp.data.GenericDao;

@Stateless
public class GenericDaoImpl<T> implements GenericDao<T> {

  private final Class<T> persistentClass;
  private final EntityManager em;
  
  public GenericDaoImpl() {
    this(null);
  }

  @SuppressWarnings("unchecked")
  public GenericDaoImpl(EntityManager em) {
    this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass()).getActualTypeArguments()[0];
    this.em = em;
  }

  @Override
  public void save(T object) {
    em.persist(object);
  }

  @Override
  public T find(final Long id) {
    return this.em.find(persistentClass, id);
  }

  @Override
  public T update(final T t) {
      return this.em.merge(t);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getAll() {
    return em.createQuery("FROM " + persistentClass.getName()).getResultList();
  }

}
