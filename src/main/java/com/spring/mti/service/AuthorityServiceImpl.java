package com.spring.mti.service;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.AuthoritiesDaoImpl;
import com.spring.mti.security.Authorities;
import com.spring.mti.security.Users;
@Service("serviceAuth")
@Repository
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	//@Resource
	AuthoritiesDao authDao;
	
	/*
	public void setAuthDao(AuthoritiesDaoImpl authDao) {
		this.authDao = authDao;
	}
	 */

	@Override
	public void setPermissionsUser(Users user) {
		// TODO Auto-generated method stub
		System.out.println("Enter to service");
		authDao.setPermissionUser(user);
	}

	@Override
	public List<String> getAllPermissionsByUserId(long user_id) {
		// TODO Auto-generated method stub
		return authDao.getAllPermissionsBuUsername(user_id);
	}
	
	@Override
	public boolean isUserRoleSet(Users user){
		return authDao.isUserRoleSet(user);
	}
	
	
	
	
}
