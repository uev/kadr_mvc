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

@Controller
public class DictionaryController extends GeneralController implements BeanFactoryAware {
	
	private AuthorityService sauth;
	private AddressService saddr;
	private DictionaryService sdict;
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sauth = (AuthorityService)context.getBean("serviceRole");
		saddr = (AddressService)context.getBean("serviceAddress");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
	}

	
	@RequestMapping(value = "/admin/dictionary/index.html", method = RequestMethod.GET)
	public final ModelAndView mainViewDictionary(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("admin/dictionary/index");
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/addemploye.html", method = RequestMethod.GET)
	public final ModelAndView addEmploye(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView view = verifyPermission(request.getSession());
		System.out.println(request.getCharacterEncoding());
		if (view.getViewName() == null){
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
			view.setViewName("admin/dictionary/addemploye");
		}
		return view;
	}
		
	//@RequestMapping(value = "/json/getcountry.html", method = RequestMethod.POST)
	@RequestMapping(value = "/admin/dictionary/json/getcountry.html", method = RequestMethod.POST)
	public  @ResponseBody List<Country> getCountries(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			return saddr.getCountries();
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/json/getregion.html", method = RequestMethod.POST)
	public  @ResponseBody List<Region> getRegions(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String id = request.getParameter("id");
			if (id != null){
				return saddr.getRegions(Integer.parseInt(id));
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/json/getcity.html", method = RequestMethod.POST)
	public  @ResponseBody List<City> getCities(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
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
	
	@RequestMapping(value = "/admin/dictionary/json/recbykey.html", method = RequestMethod.POST)
	public  @ResponseBody List<Object> getRecordsByKeyPerson(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String name = request.getParameter("key");
			if (name != null){
				List<Object> answer = new ArrayList<Object>();
				List<Employe> e = sdict.getEmployeByName(name);
				for (Employe item : e){
					City c = item.getFk_city(); 
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("city", c.getName());
					map.put("region", c.getFk_region().getName());
					map.put("country", c.getFk_country().getName());
					Department d = item.getFk_department();
					if (d != null) {
						map.put("department", d.getDep_name());
					} else {
						map.put("department", "Не указан"); 
					}	
					map.put("fio", item.getFio());
					map.put("id", item.getId());
					answer.add(map);
				}
					return answer;
			}
		}
		return null;
	}
}
