package com.spring.mti.web;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.mti.service.CustomUserDetailsService;

public class LoginController implements Controller, BeanFactoryAware{
	private CustomUserDetailsService authStorage;
	private AuthenticationManager am;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
		am = (AuthenticationManager) context.getBean("authenticationManager");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (request.getParameter("logout") != null) {
			session.removeAttribute("loginSuccess");
			session.removeAttribute("login");
			return new ModelAndView("redirect:/");
		}
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Authentication arequest = new UsernamePasswordAuthenticationToken(login, password);
		try {
			am.authenticate(arequest);
			session.setAttribute("loginSuccess", 1);
			session.setAttribute("login", login);
        } catch(Exception e){
        	if (e instanceof BadCredentialsException){
        		log.error("Error auth!!!");
        	} 
        	return new ModelAndView("redirect:/");
        }
		return null;
	}
}
