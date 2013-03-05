package com.spring.mti.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.spring.mti.service.PersonService;


public class IndexController implements Controller, BeanFactoryAware {
	private PersonService personService;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Calling IndexController.handleRequest()");
		ModelAndView view = new ModelAndView("index");
		long count = personService.countAllPersons();
		view.addObject("countM", count);
/*
		HttpSession session = request.getSession();
		if (session.getAttribute("chkErrorPin") != null) {
			Integer chkErrorPin = (Integer)session.getAttribute("chkErrorPin");
			if (chkErrorPin > 0 && chkErrorPin > 3) {
				view.addObject("error", "Карта заблокирована. Обратитесь в техподдержку");
			} else {
				view.addObject("error", "Не верный пинкод" + session.getAttribute("chkErrorPin").toString());
			}
		}
*/
		return view;
	}

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		// TODO Auto-generated method stub
		personService = (PersonService)context.getBean("servicePerson");
	}
}