package com.spring.mti.web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Ignore;
import org.junit.Test;

import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.AbstractTest;

public class IndexControllerTest extends AbstractTest {

	@Test
	public void testLoginView() throws Exception {
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loginSuccess")).thenReturn(null);
		IndexController controller = new IndexController();
        controller.setBeanFactory(context);
        ModelAndView modelAndView = controller.indexAction(request, response);
        assertEquals("login", modelAndView.getViewName());
	}
	
	@Test
	public void testIndexView() throws Exception {
		when(request.getSession()).thenReturn(session);
        when(session.getAttribute("loginSuccess")).thenReturn("1");
		IndexController controller = new IndexController();
        controller.setBeanFactory(context);
        ModelAndView modelAndView = controller.indexAction(request, response);
        assertEquals("index", modelAndView.getViewName());
	}
}
