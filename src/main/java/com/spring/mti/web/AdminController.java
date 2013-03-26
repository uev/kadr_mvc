package com.spring.mti.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.spring.mti.model.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;

public class AdminController implements Controller, BeanFactoryAware {
	private CustomUserDetailsService authStorage;
	private AuthorityService sauth;
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
		sauth = (AuthorityService)context.getBean("serviceRole");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView("admin/index");
		if (authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
			/*
			 * Маршрутизация в админзоне
			 */
			if (request.getParameter("accounting") != null) {
				view.setViewName("admin/accounting");
				if (request.getParameter("listacc") != null) {
					//return JsonView.Render(authStorage.getAllUserNames(), response);
					Map<String, List> testMap = new HashMap<String, List>();
					//testMap.put("users", authStorage.getAllUsersPermissions());
					//JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(authStorage.getAllUsersPermissions());  
					//view.addObject("json", jsonObject);
					//return new ModelAndView("jsonpattern").addObject("json", jsonObject);
					view.addObject("json", authStorage.getAllUsersPermissions());
				}
	
				/*
				 * Удаление аккаунта
				 */
				if (request.getParameter("unbindlogin") != null) {
					view.addObject("rmusers", 1);
					String login = request.getParameter("login");
					if (login != null) {
						try {
							authStorage.deleteUser(authStorage.getUserByLoginName(login));
							view.addObject("error", 0);
							} catch (Exception e){
								view.addObject("error", 1);
							}
						}
					}	
				/*
				 * Создание аккаунта
				 */
				if (request.getParameter("bindlogin") != null) {
					String login = request.getParameter("login");
					String role = request.getParameter("role");
					if (login != null) {
						if ( authStorage.getUserByLoginName(login).getUsername() == null){
							try {
								Users user = new Users();
								user.setUsermame(request.getParameter("login"));
								user.setPassword(request.getParameter("password"));
								user.setEnabled(1);
								authStorage.setSalt(user);
								authStorage.createUser(user);
								sauth.setPermissions(user, sauth.getRoleByName(role));
								view.addObject("error", 0);
							}
							catch(Exception e) {
								view.addObject("error", 1);
								System.out.println("Exeption");
							}
						} else {
							view.addObject("error", 1);
						}
					}
					view.addObject("roles", sauth.getAllRoles());
				}
				
			}
			return view;
		}
		return new ModelAndView("redirect:/");
	}
}
