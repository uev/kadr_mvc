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
import com.spring.mti.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;

public class AuthUsersTest {
	private static ApplicationContext context;
	private static CustomUserDetailsService dao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		dao = (CustomUserDetailsService)context.getBean("userDetailsService");
	}

	@Test
	public void testUsersDAOCreateUser() {
		Users user = new Users();
		user.setUsermame("testic");
		user.setPassword("testic");
		user.setEnabled(1);
		dao.setSalt(user);
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
		Authentication arequest = new UsernamePasswordAuthenticationToken("testic", "testic");
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