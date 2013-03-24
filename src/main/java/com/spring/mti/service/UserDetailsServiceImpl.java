package com.spring.mti.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.CategoryDao;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.model.Category;
import com.spring.mti.model.security.Users;
import com.spring.mti.model.security.UsersDetailImpl;

public class UserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
	@Autowired private PasswordEncoder passwordEncoder;
	@Autowired private SaltSource saltSource;
	@Autowired private AuthoritiesDao authDao;
	//@Autowired
	private CategoryDao categoryDao;
	
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
		Users user = getUserByLoginName(username);
		UserDetails userDetail = new UsersDetailImpl(user);
		return userDetail;
	}

	@Override
	public void createUser(Users userDetail) {
		dao.create(userDetail);
	}

	@Override
	@Deprecated
	public List<String> getAllUserNames() {
		return new ArrayList<String>();
	}

	@Override
	public void deleteUser(Users userDetail) {
		dao.delete(userDetail);
	}

	@Override
	public Users getUserByLoginName(String userName) {
		List param = new ArrayList<String>();
		param.add(userName);
		List<Users> res = dao.findByNamedQuery("select s from Users s where s.username=?1", param.toArray()); 
		if (res.size() > 0){
			return res.get(0);
		} else {
			return new Users();
		}
	}	
	
	@Override
	public void setSalt(Users user){
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(new UsersDetailImpl(user))));
	}
	
	@Override
	public boolean isUserRoleSet(String username){
		return authDao.isUserRoleSet(getUserByLoginName(username));
	}

	@Override
	public boolean isAdminRoleSet(String username) {
		return authDao.isAdminRoleSet(getUserByLoginName(username));
	}
	
	@Override
	public HashMap<String,List> getAllUsersPermissions(){
		return authDao.getAllUsersPermissions();
	}
	
	@Override
	@Transactional
	public void appendCategory(Category c){
		List<String> ls = new ArrayList<String>();
		ls.add(c.getCname());
		List<Category> cat = categoryDao.findByNamedQuery("select s from Category s where s.cname=?1",ls.toArray());
		System.out.println(cat.size());
		if ( cat != null && cat.size()<1) {
			categoryDao.create(c);
		}
	}
}
