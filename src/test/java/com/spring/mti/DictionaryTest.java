package com.spring.mti;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.spring.mti.model.Category;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.DictionaryService;

public class DictionaryTest {
	private static ApplicationContext context;
	private static AddressService asrv;
	private static DictionaryService dsrv;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		asrv = (AddressService)context.getBean("serviceAddress");
		dsrv = (DictionaryService)context.getBean("serviceDictionary");
	}
	
	@Test
	public void testCreateRmEmploye(){
	    }
}