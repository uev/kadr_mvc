package com.spring.mti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.mti.dao.UsersDao;
import com.spring.mti.model.security.Users;
import com.spring.mti.model.security.UsersDetailImpl;

public class UserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private SaltSource saltSource;
	
	
	private UsersDao dao;

	public UsersDao getDao() {
		return dao;
	}

	public void setDao(UsersDao dao) {
		this.dao = dao;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users user = dao.getUserByLoginName(username);	
		UserDetails userDetail = new UsersDetailImpl(user);
		return userDetail;
	}

	@Override
	public void createUser(Users userDetail) {
		dao.createUser(userDetail);
	}

	@Override
	public List<String> getAllUserNames() {
		return dao.getAllUserNames();
	}

	@Override
	public void deleteUser(Users userDetail) {
		dao.deleteUser(userDetail);	
	}

	@Override
	public Users getUserByLoginName(String userName) {
		return dao.getUserByLoginName(userName);
	}	
	
	@Override
	public void setSalt(Users user){
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(new UsersDetailImpl(user))));
	}
}
