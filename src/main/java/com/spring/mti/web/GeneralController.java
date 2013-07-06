package com.spring.mti.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.servlet.ModelAndView;
import com.spring.mti.service.CustomUserDetailsService;

public class GeneralController implements BeanFactoryAware {
	protected CustomUserDetailsService authStorage;
	protected AuthenticationManager am;
	protected String viewPrefix = "/WEB-INF/views/";
	protected int sizePage = 30;
	protected int pageStep = 3;

	public ModelAndView verifyPermission(HttpSession session) {
		ModelAndView view = new ModelAndView();
		try{
			if (!authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
				view.setViewName("redirect:/");
			}
		} catch (Exception e) {
			view.setViewName("redirect:/");
			e.printStackTrace();
		}		
		return view;	
	}

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
		am = (AuthenticationManager) context.getBean("authenticationManager");
	}
}