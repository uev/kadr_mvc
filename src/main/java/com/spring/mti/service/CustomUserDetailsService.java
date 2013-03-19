package com.spring.mti.service;

import java.util.List;

import com.spring.mti.security.Users;

public interface CustomUserDetailsService{
	public void createUser(Users userDetail);
	public List<String> getAllUserNames();
	public void deleteUser(Users userDetail);
	public Users getUserByLoginName(String userName);
	public void setSalt(Users userDetail);
}
