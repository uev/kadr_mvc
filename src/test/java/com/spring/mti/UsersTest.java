package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.dao.UsersDao;
import com.spring.mti.domain.Users;

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
		user.setName("alicedssd");
		user.setPassword("lol");
		dao.createUser(user);
	}
}
