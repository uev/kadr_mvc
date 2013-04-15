package com.spring.mti.web;

import java.util.HashMap;
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
	
	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/add.html", method = RequestMethod.GET)
	public final ModelAndView appendQueshionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/queshions/scripts.jsp"));
			view.addObject("title", "Админзона / добавление вопроса в базу");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/queshions/add.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendQueshionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String category = request.getParameter("category");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (category != null){
				try {
					log.info("Try appending new category");
					sdict.createCategory(category);
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending department. Input value:");
						log.info(category);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	
	
	
	
	
	
	
	
}
