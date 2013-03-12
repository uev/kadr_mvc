package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.spring.mti.domain.Users;

public class UsersDaoImpl implements UsersDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	@Override
	public void createUser(Users userDetail) {
		// TODO Auto-generated method stub
		entityManager.persist(userDetail);
	}

	@Override
	public List<String> getAllUserNames() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select s.name from Users s").getResultList();
	}

	@Override
	public void deleteUser(Users userDetail) {
		// TODO Auto-generated method stub
		entityManager.remove(userDetail);
	}
}
