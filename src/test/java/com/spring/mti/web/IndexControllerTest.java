package com.spring.mti.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest extends AbstractTestController {

	@Test
	public void testLoginView() throws Exception {
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loginSuccess")).thenReturn(null);
		IndexController controller = new IndexController();
        controller.setBeanFactory(context);
        ModelAndView modelAndView = controller.handleRequest(request, response);
        assertEquals("login", modelAndView.getViewName());
	}
	
	@Test
	public void testIndexView() throws Exception {
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loginSuccess")).thenReturn("1");
		IndexController controller = new IndexController();
        controller.setBeanFactory(context);
        ModelAndView modelAndView = controller.handleRequest(request, response);
        assertEquals("index", modelAndView.getViewName());
	}
}
