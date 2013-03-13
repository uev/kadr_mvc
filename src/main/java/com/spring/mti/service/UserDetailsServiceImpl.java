package com.spring.mti.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.mti.dao.UsersDao;
import com.spring.mti.security.Users;
import com.spring.mti.security.UsersDetailImpl;


public class UserDetailsServiceImpl implements UserDetailsService {
	UsersDao dao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users user = dao.getUserByLoginName(username);
		System.out.println(user.getPassword());
		
		
		UserDetails userDetail = new UsersDetailImpl(user);
		return null;
	}
}
