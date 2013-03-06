package com.spring.mti.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest {
	private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private static BeanFactory context;

	@Before
	public void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
	    response = mock(HttpServletResponse.class);
	    session = mock(HttpSession.class);
	    context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml"); 
	}

	@Test
	public void testLoginView() throws Exception {
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loginSuccess")).thenReturn(null);
		IndexController controller = new IndexController();
        controller.setBeanFactory(context);
        ModelAndView modelAndView = controller.handleRequest(request, response);
        assertEquals("login", modelAndView.getViewName());
	}	
}
