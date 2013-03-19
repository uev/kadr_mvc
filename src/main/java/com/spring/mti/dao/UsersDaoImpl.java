package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.security.Users;

@Repository("usersDAO")
public class UsersDaoImpl implements UsersDao {
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
		return this.entityManager.createQuery("select s.username from Users s").getResultList();
	}

	@Override
	@Transactional
	public void deleteUser(Users userDetail) {
		this.entityManager.createQuery("delete from Authorities s where s.user.id = :user_id").setParameter("user_id", userDetail.getId()).executeUpdate();
		this.entityManager.createQuery("delete from Users s where s = :user").setParameter("user", userDetail).executeUpdate();
		this.entityManager.flush();
	}

	@Override
	@Transactional
	public Users getUserByLoginName(String userName) {
		// TODO Auto-generated method stub
		//return (Users)this.entityManager.createQuery("select s from Users s where s.username =='".concat(userName).concat("'")).getSingleResult();
		try {
			return  (Users)this.entityManager.createQuery("select s from Users s where s.username = :username").setParameter("username", userName).getResultList().get(0);
		}
		catch (Exception indexOutOfBoundsException) {
			return new Users();
		}
	}
	
	@Override
	@Transactional
	public int getEnabled(Users userDetail){
		return userDetail.getEnabled();
	}
}
