package com.spring.mti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring.mti.security.Authorities;
import com.spring.mti.security.Users;

@Repository("authoritiesDAO")
public class AuthoritiesDaoImpl implements AuthoritiesDao {
	private Users user;
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
		
	
	@Override
	public String getAuthority() {
		return "";
	}

	@Override
	public void setPermissionUser(Authorities auser){
		//this.entityManager.persist(user);
		
	}
}
