package com.spring.mti.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.PersonService;


@Controller
public class IndexController extends GeneralController implements BeanFactoryAware {
	private PersonService personService;
	private CustomUserDetailsService authStorage;
	static Logger log = Logger.getLogger(LoginController.class.getName());
	/*
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
	*/
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView indexAction(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView("index");
		if (session.getAttribute("loginSuccess") != null) {
			log.debug("attr loginSuccess not null");
			if (authStorage.isUserRoleSet((String)session.getAttribute("login"))){
				//Личный кабинет аттестуемого
				log.debug("Set view candidate/index");
				view.setViewName("candidate/index");
			}
			if (authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
				//админка
				log.debug("Set view admin/index");
				view.setViewName("candidate/index");
				view.setViewName("admin/index");	
			}
		} else {
			log.debug("Set view login");
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