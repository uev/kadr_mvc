package com.spring.mti;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.security.Users;

public class UsersTest {
	private static ApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
	}

	@Test
	/* Тестирование работы DAO */
	public void testUsersDAOCreate() {
		
		UsersDao dao = (UsersDao)context.getBean("userDao");
		/*
		Users user = new Users();
		user.setUsermame("uev");
		user.setPassword("123");
		dao.createUser(user);
		*/
		//AuthoritiesDao daoauth = (AuthoritiesDao)context.getBean("authoritiesDao");
		Users a = dao.getUserByLoginName("uev");
		System.out.println(a.getPassword());
		
		

		//System.out.println(user.getPassword());
	}
}
