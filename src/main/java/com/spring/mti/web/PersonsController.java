package com.spring.mti.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.DictionaryService;

@Controller
public class PersonsController extends GeneralController implements BeanFactoryAware{
	private DictionaryService sdict;
	private AddressService saddr;

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		saddr = (AddressService)context.getBean("serviceAddress");
	}

	@RequestMapping(value = "/admin/dictionary/persons/index.html", method = RequestMethod.GET)
	public final ModelAndView indexAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("title", "Админзона / пользователи");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/menu.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/add.html", method = RequestMethod.GET)
	public final ModelAndView addEmploye(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView view = verifyPermission(request.getSession());
		System.out.println(request.getCharacterEncoding());
		if (view.getViewName() == null){
			view.addObject("title", "Админзона / Создание пользователя");
			request.setCharacterEncoding("utf-8");
			String person = request.getParameter("person");
			if (person != null){
				sdict.createEmploye(person);
				List<Employe> e = sdict.getEmployeByName(person);
				Employe last = e.get(e.size()-1);
				System.out.println(request.getParameter("city"));
				City c = saddr.getCitiyById(Long.parseLong(request.getParameter("city")));
				last.setFk_city(c);
				sdict.updateEmployeRelation(last);
			}
			view.setViewName("admin/dictionary/persons/add");
		}
		return view;
	}
}
