package com.spring.mti.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;

@Controller
public class KnowledgesController extends GeneralController implements BeanFactoryAware{
	private KnowledgesService sknow;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sknow = (KnowledgesService)context.getBean("serviceKnowledges");
	}

	
	@RequestMapping(value = "/admin/dictionary/knowledges/index.html", method = RequestMethod.GET)
	public final ModelAndView indexAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("title", "Админзона / база знаний");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/menu.jsp"));
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
}
