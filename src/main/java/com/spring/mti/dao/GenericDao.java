package com.spring.mti.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
	void create(T t);
    void delete(T t);
	List<T> findByNamedQuery(String name, Object[] params);
}