package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.spring.mti.domain.Users;

public interface UsersDao{
	void createUser(Users userDetail);
	List<String> getAllUserNames();
	void deleteUser(Users userDetail);
	public void setEntityManager(EntityManager entityManager);
}