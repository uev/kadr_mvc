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
		Users user = new Users();
		user.setUsermame("bob");
		user.setPassword("1235432");
		dao.createUser(user);
		Users user_temp = dao.getUserByLoginName("bob");
		dao.deleteUser(user_temp);
		//AuthoritiesDao daoauth = (AuthoritiesDao)context.getBean("authoritiesDao");
		//Users a = dao.getUserByLoginName("uev");
		//dao.deleteUser(a);
		//System.out.println(user.getPassword());
	}
}
