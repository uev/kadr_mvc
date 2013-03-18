package com.spring.mti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.security.Users;
import com.spring.mti.service.AuthorityService;

public class AuthUsersTest {
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
		user.setUsermame("testic");
		user.setPassword("1235432");
		user.setEnabled(1);
		dao.createUser(user);
		assertEquals(dao.getUserByLoginName("testic").getUsername(), "testic");
	}
	
	@Test
	public void testSetPermissionsUser(){
		Users user = dao.getUserByLoginName("testic");
		AuthorityService aservice = (AuthorityService) context.getBean("serviceAuth");
		if (!aservice.isUserRoleSet(user)){
			aservice.setPermissionsUser(user);
			System.out.println("Add user permission");
		}
		assertTrue(aservice.isUserRoleSet(user));
	}
	
	@Test
	public void testAuthManager(){
		Authentication arequest = new UsernamePasswordAuthenticationToken("testic", "1235432");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        am.authenticate(arequest);
	}
	
	@Test
	public void testUsersDAORemoveUser() {
		Users user = dao.getUserByLoginName("testic");
		try {
			user.getUsername().equals(null);
			dao.deleteUser(user);
		} catch(NullPointerException exception) {
			System.out.println("User is epsent");
		}
	}
}