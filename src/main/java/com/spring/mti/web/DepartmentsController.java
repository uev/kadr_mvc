package com.spring.mti.web;

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
import com.spring.mti.model.security.Users;
import com.spring.mti.service.DictionaryService;

@Controller 
public class DepartmentsController extends GeneralController implements BeanFactoryAware{
	private DictionaryService sdict;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sdict = (DictionaryService)context.getBean("serviceDictionary");
	}
	
	
	@RequestMapping(value = "/admin/dictionary/departments/index.html", method = RequestMethod.GET)
	public final ModelAndView indexAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("title", "Админзона / подразделения");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/departments/menu.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/departments/add.html", method = RequestMethod.GET)
	public final ModelAndView appendDepartmentAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/departments/scripts.jsp"));
			view.addObject("title", "Админзона / добавление подразделения");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/departments/add.jsp"));
		}
		return view;
	}
		
	@RequestMapping(value = "/admin/dictionary/departments/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendDepartmentJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Entering post...");
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String department = request.getParameter("department");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (department != null){
				try {
					log.info("Try appending new department");
					sdict.createDepartment(department);
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending department. Input value:");
						log.info(department);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/departments/rm.html", method = RequestMethod.GET)
	public final ModelAndView popDepartmentAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/departments/scripts.jsp"));
			view.addObject("title", "Админзона / удаление полразделения");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/departments/rm.jsp"));
			view.addObject("departments", sdict.getAddDepartments());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/departments/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popDepartmentJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Entering removing...");
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String department = request.getParameter("department");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (department != null){
				try {
					log.info("Try removing department");
					sdict.deleteDepartment(sdict.getDepartmentByName(department));
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error pop department. Input value:");
						log.info(department);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/departments/list.html", method = RequestMethod.POST)
	public @ResponseBody List<Department> listDepartmentJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			return sdict.getAddDepartments();
		}
		return null;	
	}	
}