package com.spring.mti.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;
	protected String query;

	public GenericDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	@Transactional
	public void create(T t) {
			this.entityManager.persist(t);
	}

	@Override
	@Transactional
	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}
	
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> findByNamedQuery(final String name, Object... params) {
    	Query query = this.entityManager.createQuery(name);
    	for (int i = 0; i < params.length; i++) {
        	query.setParameter(i + 1, params[i]);
         }
         final List<T> result = (List<T>) query.getResultList();
         return result;
    }
}