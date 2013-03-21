package com.spring.mti.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.mti.service.CustomUserDetailsService;

public class AdminController implements Controller, BeanFactoryAware {
	private CustomUserDetailsService authStorage;
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView("ui_admin");
		if (authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
			return view;
		}
		return new ModelAndView("redirect:/");
	}
}
