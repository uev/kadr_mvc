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
			/*
			 * Маршрутизация в админзоне
			 */
			if (request.getParameter("accounting") != null) {
				if (request.getParameter("listacc") != null) {
					//return JsonView.Render(authStorage.getAllUserNames(), response);
					Map<String, List> testMap = new HashMap<String, List>();
					//testMap.put("users", authStorage.getAllUsersPermissions());
					JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(authStorage.getAllUsersPermissions());  
					//view.addObject("json", jsonObject);
					//return new ModelAndView("jsonpattern").addObject("json", jsonObject);
					view.addObject("json", authStorage.getAllUsersPermissions());
				}
				/*
				 * Создание пользователя
				 */
				if (request.getParameter("createuser") != null) {
					view.addObject("createuser", "1");
				}
				view.setViewName("ui_admin_accounting");
			}
			return view;
		}
		return new ModelAndView("redirect:/");
	}
}
