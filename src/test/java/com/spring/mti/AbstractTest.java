package com.spring.mti;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractTest {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected static BeanFactory context;
	
	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
	    response = mock(HttpServletResponse.class);
	    session = mock(HttpSession.class);
	    context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml"); 
	}
}
