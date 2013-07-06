package com.spring.mti.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;
import com.spring.mti.model.address.Country;
import com.spring.mti.model.address.Region;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.LayoutService;

@Controller
public class DictionaryController extends GeneralController implements BeanFactoryAware {
	
	private AuthorityService sauth;
	private AddressService saddr;
	private DictionaryService sdict;
	private LayoutService slayout;
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sauth = (AuthorityService)context.getBean("serviceRole");
		saddr = (AddressService)context.getBean("serviceAddress");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		slayout = (LayoutService)context.getBean("serviceLayout");
	}

	@RequestMapping(value = "/admin/dictionary/index.html", method = RequestMethod.GET)
	public final ModelAndView mainViewDictionary(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("title", "Админзона / cправочники");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/menu.jsp"));
		}
		return view;
	}
	
	
		
	//@RequestMapping(value = "/json/getcountry.html", method = RequestMethod.POST)
	@RequestMapping(value = "/admin/dictionary/persons/json/getcountry.html", method = RequestMethod.POST)
	public  @ResponseBody List<Country> getCountries(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			return saddr.getCountries();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/json/getregion.html", method = RequestMethod.POST)
	public  @ResponseBody List<Region> getRegions(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			String id = request.getParameter("id");
			if (id != null){
				return saddr.getRegions(Integer.parseInt(id));
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/json/getcity.html", method = RequestMethod.POST)
	public  @ResponseBody List<City> getCities(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			String id = request.getParameter("id");
			System.out.println(id);
			if (id != null){
				List<City> l = saddr.getCities(Integer.parseInt(id));
				System.out.println(l.get(1).getName());
				return l;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/json/recbykey.html", method = RequestMethod.POST)
	public  @ResponseBody List<Object> getRecordsByKeyPerson(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			String name = request.getParameter("key");
			if (name != null){
				List<Employe> e = sdict.getEmployeByName(name);
				List<Object> answer = slayout.employeToMapJson(e);
				return answer;
			}
		}
		return null;
	}	
}