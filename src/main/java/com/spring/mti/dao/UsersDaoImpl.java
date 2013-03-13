package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.security.Users;

@Repository("usersDAO")
public class UsersDaoImpl implements UsersDao {
	//(unitName = "JPA_POSTGRES")
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void createUser(Users userDetail) {
		// TODO Auto-generated method stub
		this.entityManager.persist(userDetail);
	}

	@Override
	@Transactional
	public List<String> getAllUserNames() {
		// TODO Auto-generated method stub
		return this.entityManager.createQuery("select s.name from Users s").getResultList();
	}

	@Override
	@Transactional
	public void deleteUser(Users userDetail) {
		// TODO Auto-generated method stub
		this.entityManager.remove(userDetail);
	}
}
