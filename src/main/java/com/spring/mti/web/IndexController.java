package com.spring.mti.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.PersonService;


public class IndexController implements Controller, BeanFactoryAware {
	private PersonService personService;
	private CustomUserDetailsService authStorage;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Calling IndexController.handleRequest()");
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView("index");
		if (session.getAttribute("loginSuccess") != null) {
			if (authStorage.isUserRoleSet((String)session.getAttribute("login"))){
				//Личный кабинет аттестуемого
				view.setViewName("candidate/index");
			}
			if (authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
				//админка
				view.setViewName("admin/index");	
			}
		} else {
			view.setViewName("login");
		}
		return view;
}

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		// TODO Auto-generated method stub
		personService = (PersonService)context.getBean("servicePerson");
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
	}
}