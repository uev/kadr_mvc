package com.spring.mti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.RoleDao;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
@Repository
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	//@Resource
	private AuthoritiesDao authDao;
	@Autowired private RoleDao rolesDao;
	
	
	/*
	public void setAuthDao(AuthoritiesDaoImpl authDao) {
		this.authDao = authDao;
	}
	 */

	@Override
	public void setPermissionsUser(Users user) {
		authDao.setPermissionUser(user);
	}
	@Override
	public void setPermissionsAdmin(Users user) {
		authDao.setPermissionAdmin(user);
	}
	
	@Override
	public List<String> getAllPermissionsByUserId(long user_id) {
		// TODO Auto-generated method stub
		return authDao.getAllPermissionsBuUsername(user_id);
	}
	@Override
	public void createRole(Role role) {
		rolesDao.createRole(role);
	}
	
	@Override
	public void deleteRole(Role role) {
		rolesDao.deleteRole(role);	
	}
	@Override
	public List<Object[]> getAllRoles() {
		return rolesDao.getAllRoles();
	}
}
