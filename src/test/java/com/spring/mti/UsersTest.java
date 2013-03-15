package com.spring.mti;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.security.Users;

public class UsersTest {
	private static ApplicationContext context;
	private static UsersDao dao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		dao = (UsersDao)context.getBean("userDao");
	}

	@Test
	public void testUsersDAOCreateUser() {
		Users user = new Users();
		user.setUsermame("bob");
		user.setPassword("1235432");
		user.setEnabled(1);
		dao.createUser(user);
		/*
		 * Права
		 */
		
		
		//AuthoritiesDao daoauth = (AuthoritiesDao)context.getBean("authoritiesDao");
		//Users a = dao.getUserByLoginName("uev");
		//dao.deleteUser(a);
		//System.out.println(user.getPassword());
	}
	
	@Test
	@Ignore
	public void testUsersDAORemoveUser() {
		Users user = dao.getUserByLoginName("bob");
		try {
			user.getUsername().equals(null);
			dao.deleteUser(user);
		} catch(NullPointerException exception) {
			System.out.println("User is epsent");
		}
	}
}
