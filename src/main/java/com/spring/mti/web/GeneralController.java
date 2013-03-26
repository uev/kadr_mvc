package com.spring.mti.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.servlet.ModelAndView;
import com.spring.mti.service.CustomUserDetailsService;

public class GeneralController implements BeanFactoryAware {
	protected CustomUserDetailsService authStorage;

	public ModelAndView verifyPermission(HttpSession session) {
		ModelAndView view = new ModelAndView();
		if (!authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
			view.setViewName("redirect:/");
		}
		return view;
	}

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
	}
}