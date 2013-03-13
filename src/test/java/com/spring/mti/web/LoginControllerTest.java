package com.spring.mti.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginControllerTest {
	private static BeanFactory context;
	@Before
	public void setUp() throws Exception {
		
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
	}

	@Test
	public void testAuthManager() {
		Authentication arequest = new UsernamePasswordAuthenticationToken("uev", "123");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        am.authenticate(arequest);
	}

}
