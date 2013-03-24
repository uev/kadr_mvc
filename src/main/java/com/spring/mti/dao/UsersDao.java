package com.spring.mti.dao;

import com.spring.mti.model.security.Users;

public interface UsersDao extends GenericDao<Users, Long> {
	int getEnabled(Users userDetail);
}