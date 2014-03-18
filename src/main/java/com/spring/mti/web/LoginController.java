package com.spring.mti.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends GeneralController {
	//private CustomUserDetailsService authStorage;
	//private AuthenticationManager am;
	static Logger log = Logger.getLogger(LoginController.class.getName());
	private static int limiAuth = 3;
	private static int blockTime = 1;

	/*
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
		am = (AuthenticationManager) context.getBean("authenticationManager");
	}*/

	/*
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
        }
		return new ModelAndView("redirect:/");
	}
	*/
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public ModelAndView loginAction(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Authentication arequest = new UsernamePasswordAuthenticationToken(login, password);
		Object prev = session.getAttribute("blockOnTime");
		ModelAndView view = new ModelAndView("redirect:/");
		try {
			if (prev instanceof DateTime) {
				DateTime currentdate = new DateTime().now();
				Minutes s = Minutes.minutesBetween((DateTime)prev, currentdate);
				System.out.println(s.getMinutes());
				if (s.getMinutes() > blockTime) {
					session.setAttribute("loginFail", null);
					session.setAttribute("blockOnTime", null);
					session.setAttribute("loginIncorrect", null);
				}  else {
					session.setAttribute("loginIncorrect", "Не правильный логин или пароль. Доступ блокирован на 2 минуты");
					throw  new Exception();
				}
			}
			log.debug("Try auth login ".concat(login));
			am.authenticate(arequest);
			log.debug("Set session attribute");
			session.setAttribute("loginSuccess", 1);
			session.setAttribute("login", login);
        } catch(Exception e){
        	e.printStackTrace();
        	if (e instanceof BadCredentialsException){
        		log.error("Error auth!!!");
        		Integer lfail = (Integer)session.getAttribute("loginFail");
        		session.setAttribute("loginIncorrect", "Не правильный логин или пароль");
        		if (lfail != null){
        			lfail+=1;
        			session.setAttribute("loginFail", lfail);
        		} else {
        			session.setAttribute("loginFail", 1);
        		}
        		System.out.println(lfail);
        		if (lfail instanceof Integer && lfail > limiAuth) {
        			session.setAttribute("blockOnTime", new DateTime().now());
        			System.out.println("Set time");
        		}
        	} 
        }
		log.debug("Redirect...");
		return view;
	}

	@RequestMapping(value = "/logout.html", method = RequestMethod.GET)
	public ModelAndView logoutAction(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		if (session.getAttribute("loginSuccess") != null) { 
			try {
				session.removeAttribute("loginSuccess");
				session.removeAttribute("login");
				log.info("Set session attribute");
			} catch (Exception e) {
			}
		}
		return new ModelAndView("redirect:/");
	}
}