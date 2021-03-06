package com.spring.mti.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.spring.mti.model.Question;
import com.spring.mti.model.address.City;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.LayoutService;

@Controller
public class PersonsController extends GeneralController implements BeanFactoryAware{
	private DictionaryService sdict;
	private AddressService saddr;
	private LayoutService slayout;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		saddr = (AddressService)context.getBean("serviceAddress");
		slayout = (LayoutService)context.getBean("serviceLayout");
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
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/add.html", method = RequestMethod.GET)
	public final ModelAndView addEmploye(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.addObject("title", "Админзона / Создание пользователя");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/persons/scripts.jsp"));
			view.setViewName("default/index");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/add.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Integer> addEmployeJson(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("error", 1);
		if (skey.equals(key)){
			String person = request.getParameter("person");
			String city = request.getParameter("city");
			if (!person.isEmpty() && !city.isEmpty()){
				sdict.createEmploye(person);
				List<Employe> e = sdict.getEmployeByName(person);
				Employe last = e.get(e.size()-1);
				City c = saddr.getCitiyById(Long.parseLong(request.getParameter("city")));
				Department d = sdict.getDepartmentByName(request.getParameter("department"));
				if (d != null){
					last.setFk_department(d);
				}
				last.setFk_city(c);
				sdict.updateEmployeRelation(last);
				map.put("error", 0);
			}
		}
		return map;	
	}

	@RequestMapping(value = "/admin/dictionary/persons/bind_dep.html", method = RequestMethod.GET)
	public final ModelAndView bindDepartmentAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/persons/scripts.jsp"));
			view.addObject("title", "Админзона / закрепление пользователей за департаментами");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/bind_dep.jsp"));
			view.addObject("departmentList", sdict.getAllDepartments());
			view.addObject("employeList", slayout.employeToMapJson(sdict.getEmployeAll()));
		}
		return view;
	}
	
	
	@RequestMapping(value = "/admin/dictionary/persons/bind_dep.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Integer> bindDepartmentJson(HttpServletRequest request, HttpServletResponse response){
		String key = request.getParameter("hash");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("error", 1);
		if (skey.equals(key)){
			String fio = request.getParameter("fio");
			List<Employe> e = sdict.getEmployeByName(fio);
			if (e != null){
				log.info("Employe isn't null");
				log.info(request.getParameter("department"));
				Department d = sdict.getDepartmentByName(request.getParameter("department"));
				if (d != null){
					log.info("Department isn't null");
					Employe em = e.get(0);
					em.setFk_department(d);
					try{
						sdict.updateEmployeRelation(em);
						map.put("error", 0);
					} catch (Exception err){
						log.error(em.getFio());
						err.printStackTrace();
					}
				}
			}
		}
		return map;	
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/rm.html", method = RequestMethod.GET)
	public final ModelAndView popPersonAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/persons/scripts.jsp"));
			view.addObject("title", "Админзона / удаление удаление пользователя");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/rm.jsp"));
			view.addObject("employers", slayout.employeToMapJson(sdict.getEmployeAll()));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/persons/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popQuestionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			String id = request.getParameter("id");
			try{
				Employe q = sdict.getEmployeById(Long.parseLong(id));
				sdict.deleteEmploye(q);
				answ.put("error", 0);
			} catch (Exception e) {
				e.printStackTrace();
				answ.put("error", 1);
			}
			return answ;
		} 
		return null;
	}
}