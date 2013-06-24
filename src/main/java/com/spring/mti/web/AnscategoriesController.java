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

import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;

/*
 * Class for categories snswers
 */
@Controller
public class AnscategoriesController extends GeneralController implements BeanFactoryAware{
	private DictionaryService sdict;
	private KnowledgesService sksrv;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		sksrv = (KnowledgesService)context.getBean("serviceKnowledges");
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/index.html", method = RequestMethod.GET)
	public final ModelAndView indexAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("title", "Админзона / категории вопросов");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/menu.jsp"));
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/add.html", method = RequestMethod.GET)
	public final ModelAndView appendCategoryAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/scripts.jsp"));
			view.addObject("title", "Админзона / создание категории");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/add.jsp"));
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendCategorytJson(HttpServletRequest request,
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

	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/rm.html", method = RequestMethod.GET)
	public final ModelAndView popCategoryAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/scripts.jsp"));
			view.addObject("title", "Админзона / удаление категории");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/rm.jsp"));
			view.addObject("categories", sdict.getAllCategories());
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popCategoryJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String category = request.getParameter("category");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (category != null){
				try {
					log.info("Try removing category");
					sdict.deleteCategory(sdict.getCategoryByName(category));
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error pop department. Input value:");
						log.info(category);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/anscategories/list.html", method = RequestMethod.GET)
	public final ModelAndView listCategoryAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/queshions/scripts.jsp"));
			view.addObject("title", "Админзона / просмотр содержимого категории");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/anscategories/list.jsp"));
			view.addObject("queshions", sksrv.getQueshionsFromCategory(Long.parseLong(request.getParameter("id"))));
			//view.addObject("body", viewPrefix.concat("/admin/dictionary/persons/index.jsp"));
		}
		return view;
	}
}