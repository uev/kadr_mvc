package com.spring.mti.dao;

import java.io.Serializable;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	protected static Query qIterable;
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
    
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<T> findAll(T t) {
    	Query query = this.entityManager.createQuery("select s from " + t.getClass().getSimpleName() + " s");
        	return query.getResultList();
    }
    

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Object[]> findAll_toArray(T t) {
    	List<Object[]> l = new ArrayList<Object[]>();
    	List<T> r = this.findAll(t);
    	for (T item : r){
    		l.add(this.toArray(item));
    	}
    	return l;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Object[]> listToArray(List<T> t) {
    	List<Object[]> l = new ArrayList<Object[]>();
    	for (T item : t){
    		l.add(this.toArray(item));
    	}
    	return l;
    }
    
    @Override
	public Object[] toArray(T t) {
		Class cl = t.getClass();
		Method[] met = cl.getDeclaredMethods();
	    ArrayList<Object> l = new ArrayList<Object>();
		for (Method item : met){
	    	if (item.getName().matches("^get.*") == true) {
	    		System.out.println(item.getName());
	    		try{
	    			Method method = cl.getMethod(item.getName());
	    			System.out.println(t.getClass().getSimpleName());
	    			//Object[] obj = (Object[])method.invoke(t);
	    			l.add(method.invoke(t));
	    		} catch(Exception e){
	    			System.out.println(e);
	    		}		
	    	}
	    }		
		return l.toArray();
	}   
    
    @Override
    @Transactional
    public void update(T t) {
    	this.entityManager.merge(t);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    @Transactional
    public T getByid(T t,long id) {
    	return (T)this.entityManager.find(t.getClass(), id);
    }
    
    /*
    @SuppressWarnings("unchecked")
   	@Override
    @PostConstruct
    public void bedoreInitBean() {
       	String n = this.getClass().getSimpleName();
    	String modelName = n.substring(0, n.indexOf("DaoImpl"));
       	this.query = "select s from "+modelName+" s";
    }
    */
    
    @Override
    @Transactional
    public List <T> getPage(int page, int size, String query) {
    	this.qIterable = this.entityManager.createQuery(query);
    	this.qIterable.setMaxResults(size);
    	this.qIterable.setFirstResult(page*size-size);
    	return this.qIterable.getResultList();
    }
}