package com.spring.mti.web;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.AbstractTest;

public class LoginControllerTest extends AbstractTest {

	@Test
	@Ignore
	public void testLogout() {
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("logout")).thenReturn("1");
		LoginController controller = new LoginController();
        controller.setBeanFactory(context);
		ModelAndView modelAndView =new ModelAndView();
		try {
			//modelAndView = controller.handleRequest(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertEquals("redirect:/", modelAndView.getViewName());
	}
}
