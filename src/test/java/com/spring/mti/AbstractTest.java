package com.spring.mti;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.web.IndexController;

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
	
	@Test
	public void testLock() throws Exception {
	}
}
