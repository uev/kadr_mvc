package com.spring.mti.dao;

import java.util.List;

import com.spring.mti.security.Authorities;
import com.spring.mti.security.Users;


public interface AuthoritiesDao {
	public String getAuthority();
	public List<String> getAllPermissionsBuUsername(long user_id);
	//public void setPermissionUser(Authorities auser);
	public void setPermissionUser(Users user);
	public boolean isUserRoleSet(Users user);
}
