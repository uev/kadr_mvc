package com.spring.mti.service;

import java.util.HashMap;
import java.util.List;

import com.spring.mti.model.Category;
import com.spring.mti.model.security.Users;

public interface CustomUserDetailsService{
	public void createUser(Users userDetail);
	public List<String> getAllUserNames();
	public void deleteUser(Users userDetail);
	public Users getUserByLoginName(String userName);
	public void setSalt(Users userDetail);
	public boolean isUserRoleSet(String userName);
	public boolean isAdminRoleSet(String userName);
	HashMap<String, List> getAllUsersPermissions();
	void appendCategory(Category c);
}
