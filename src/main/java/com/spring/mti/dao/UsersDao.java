package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.spring.mti.security.Users;

public interface UsersDao{
	void createUser(Users userDetail);
	List<String> getAllUserNames();
	void deleteUser(Users userDetail);
}