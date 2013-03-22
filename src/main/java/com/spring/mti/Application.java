package com.spring.mti;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.AuthoritiesDaoImpl;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.model.security.Authorities;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;

public class Application {

	/**
	 * @param args
	 */
	private static CustomUserDetailsService dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		dao = (CustomUserDetailsService)context.getBean("userDetailsService");
		dao.getAllUsersPermissions();
		/*
		Authentication arequest = new UsernamePasswordAuthenticationToken("gogol", "bordello");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        am.authenticate(arequest);
		
		Users user = dao.getUserByLoginName("bazooka");
		AuthorityService aservice = (AuthorityService) context.getBean("serviceAuth");
		System.out.println(user.getPassword());
		*/
		}
}