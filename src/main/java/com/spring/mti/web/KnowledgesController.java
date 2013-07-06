package com.spring.mti.web;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.spring.mti.model.Answer;
import com.spring.mti.model.Queshion;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;
import com.spring.mti.service.LayoutService;

@Controller
public class KnowledgesController extends GeneralController implements BeanFactoryAware{
	private KnowledgesService sknow;
	private DictionaryService sdict;
	private LayoutService slayout;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sknow = (KnowledgesService)context.getBean("serviceKnowledges");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		slayout = (LayoutService)context.getBean("serviceLayout");
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
			view.addObject("categories", sdict.getAllCategories());
		}
		return view;
	}
	
	
	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendQueshionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			System.out.println("Entering to queshion");
			String queshion = request.getParameter("queshion");
			String category = request.getParameter("category");
			String content = request.getParameter("content");
			String bs = "inAns";
			List<Object> lo = new ArrayList<Object>();
			lo.addAll(Arrays.asList(queshion, category, content));
			if (-1 == lo.indexOf(null)){
				try {
					System.out.println("Create queshion");
					sknow.createQueshion(queshion);
					Queshion q = sknow.getQueshionByName(queshion);
					if (q != null){
						q.setFk_catgory(sdict.getCategoryByName(category));
						q.setName(queshion);
						q.setContent(content);
						sknow.updateQueshionRelation(q);
					}
					Integer chk= new Integer(1);
					while (request.getParameter(bs.concat(chk.toString().concat("[answer]"))) != null){
						String preffix = bs.concat(chk.toString());
						sknow.createAnswer(request.getParameter(preffix.concat("[answer]")));
						Answer a = sknow.getAnswerByContent(request.getParameter(preffix.concat("[answer]")));
						a.setFk_queshion(q);
						a.setValid(Boolean.parseBoolean( request.getParameter(preffix.concat("[valid]"))   ));
						sknow.updateAnswerRelation(a);
						chk+=1;
					}
					answ.put("error", 0);
				} catch(Exception e){
					answ.put("error",1);
					e.printStackTrace();
				}
			}
			return answ;
		} 
		return null;
}

	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/rm.html", method = RequestMethod.GET)
	public final ModelAndView popQueshionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/queshions/scripts.jsp"));
			view.addObject("title", "Админзона / удаление вопроса");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/queshions/rm.jsp"));
			view.addObject("queshions", sknow.getAllQueshions());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popQueshionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			Queshion q = sknow.getQueshionById(Long.parseLong(request.getParameter("queshion")));
			try{
				sknow.deleteQueshion(q);
				answ.put("error", 0);
			} catch (Exception e) {
				e.printStackTrace();
				answ.put("error", 1);
			}
			return answ;
		} 
		return null;
	}

	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/getinfo.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllQueshionProfileJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			String queshion = request.getParameter("queshion");
			Queshion q = sknow.getQueshionByName(queshion);
			try{
				System.out.println(queshion);
				List<Answer> a = sknow.getAnswersByQueshion(q);
				return slayout.queshionProfileToMapJson(a);
			} catch (Exception e) {
				log.error("Error creating map from queshion");
				e.printStackTrace();
			}
		} 
		return null;
	}

	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/edit.html", method = RequestMethod.GET)
	public final ModelAndView editQueshionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/queshions/scripts.jsp"));
			view.addObject("title", "Админзона / редактирование вопроса");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/queshions/edit.jsp"));
			//view.addObject("queshions", sknow.getAllQueshions());
			Queshion q = sknow.getQueshionById(Long.parseLong(request.getParameter("id")));
			List<Answer> a = sknow.getAnswersByQueshion(q);
			view.addObject("queshion",q);
			view.addObject("answers",a);
			view.addObject("categories",sdict.getAllCategories());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/queshions/update.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateQueshionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			System.out.println("Entering to queshion");
			String queshion = request.getParameter("queshion");
			String category = request.getParameter("category");
			String content = request.getParameter("content");
			String queshion_id = request.getParameter("queshion_id");
			String bs = "inAns";
			List<Object> lo = new ArrayList<Object>();
			lo.addAll(Arrays.asList(queshion, category, content));
			if (-1 == lo.indexOf(null)){
				try {
					System.out.println("Update Queshion");
					//sknow.createQueshion(queshion);
					//Queshion q = sknow.getQueshionByName(queshion);
					Queshion q = sknow.getQueshionById(Long.parseLong(queshion_id));
					if (q != null){
						q.setFk_catgory(sdict.getCategoryByName(category));
						q.setName(queshion);
						q.setContent(content);
						sknow.updateQueshionRelation(q);
					}
					Integer chk= new Integer(1);
					sknow.deleteAnswersByQueshionId(Long.parseLong(queshion_id));
					for (int i=0; i < Integer.parseInt(request.getParameter("acount")); i++){
						//String hkey = request.getParameter(bs.concat(chk.toString().concat("[answer]")));
						while (request.getParameter(bs.concat(chk.toString().concat("[answer]"))) == null){
							chk+=1;
						}
						System.out.println(bs.concat(chk.toString().concat("[answer]")));
						String preffix = bs.concat(chk.toString());
						sknow.createAnswer(request.getParameter(preffix.concat("[answer]")));
						Answer a = sknow.getAnswerByContent(request.getParameter(preffix.concat("[answer]")));
						a.setFk_queshion(q);
						a.setValid(Boolean.parseBoolean( request.getParameter(preffix.concat("[valid]"))   ));
						sknow.updateAnswerRelation(a);
						chk+=1;	
					}
					/*
					while (request.getParameter(bs.concat(chk.toString().concat("[answer]"))) != null){
						System.out.println(bs.concat(chk.toString().concat("[answer]")));
						String preffix = bs.concat(chk.toString());
						sknow.createAnswer(request.getParameter(preffix.concat("[answer]")));
						Answer a = sknow.getAnswerByContent(request.getParameter(preffix.concat("[answer]")));
						a.setFk_queshion(q);
						a.setValid(Boolean.parseBoolean( request.getParameter(preffix.concat("[valid]"))   ));
						sknow.updateAnswerRelation(a);
						chk+=1;
					}
					*/
					System.out.println("Answer...");
					answ.put("error", 0);
				} catch(Exception e){
					answ.put("error",1);
					e.printStackTrace();
				}
			}
			return answ;
		} 
		return null;
}	
}
