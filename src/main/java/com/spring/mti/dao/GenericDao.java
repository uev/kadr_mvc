package com.spring.mti.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
	void create(T t);
    void delete(T t);
	List<T> findByNamedQuery(String name, Object[] params);
	List<T> findAll(T t);
	List<Object[]> findAll_toArray(T t);
	Object[] toArray(T t);
	List<Object[]> listToArray(List<T> t);
	void update(T t);
	T getByid(T t, long id);
//	void bedoreInitBean();
	List<T> getPage(int page, int size, String string);
}