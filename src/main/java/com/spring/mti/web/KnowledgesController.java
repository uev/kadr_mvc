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
import com.spring.mti.model.Question;
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
	
	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/add.html", method = RequestMethod.GET)
	public final ModelAndView appendQuestionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/Questions/scripts.jsp"));
			view.addObject("title", "Админзона / добавление вопроса в базу");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/Questions/add.jsp"));
			view.addObject("categories", sdict.getAllCategories());
		}
		return view;
	}
	
	
	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendQuestionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			System.out.println("Entering to Question");
			String Question = request.getParameter("Question");
			String category = request.getParameter("category");
			String content = request.getParameter("content");
			String bs = "inAns";
			List<Object> lo = new ArrayList<Object>();
			lo.addAll(Arrays.asList(Question, category, content));
			if (-1 == lo.indexOf(null)){
				try {
					System.out.println("Create Question");
					sknow.createQuestion(Question);
					Question q = sknow.getQuestionByName(Question);
					if (q != null){
						q.setFk_catgory(sdict.getCategoryByName(category));
						q.setName(Question);
						q.setContent(content);
						sknow.updateQuestionRelation(q);
					}
					Integer chk= new Integer(1);
					while (request.getParameter(bs.concat(chk.toString().concat("[answer]"))) != null){
						String preffix = bs.concat(chk.toString());
						sknow.createAnswer(request.getParameter(preffix.concat("[answer]")));
						Answer a = sknow.getAnswerByContent(request.getParameter(preffix.concat("[answer]")));
						a.setFk_Question(q);
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

	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/rm.html", method = RequestMethod.GET)
	public final ModelAndView popQuestionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/Questions/scripts.jsp"));
			view.addObject("title", "Админзона / удаление вопроса");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/Questions/rm.jsp"));
			view.addObject("Questions", sknow.getAllQuestions());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popQuestionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			Question q = sknow.getQuestionById(Long.parseLong(request.getParameter("Question")));
			try{
				sknow.deleteQuestion(q);
				answ.put("error", 0);
			} catch (Exception e) {
				e.printStackTrace();
				answ.put("error", 1);
			}
			return answ;
		} 
		return null;
	}

	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/getinfo.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllQuestionProfileJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			String Question = request.getParameter("Question");
			Question q = sknow.getQuestionByName(Question);
			try{
				System.out.println(Question);
				List<Answer> a = sknow.getAnswersByQuestion(q);
				return slayout.QuestionProfileToMapJson(a);
			} catch (Exception e) {
				log.error("Error creating map from Question");
				e.printStackTrace();
			}
		} 
		return null;
	}

	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/edit.html", method = RequestMethod.GET)
	public final ModelAndView editQuestionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			//view.setViewName("admin/dictionary/persons/index");
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/Questions/scripts.jsp"));
			view.addObject("title", "Админзона / редактирование вопроса");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/Questions/edit.jsp"));
			//view.addObject("Questions", sknow.getAllQuestions());
			Question q = sknow.getQuestionById(Long.parseLong(request.getParameter("id")));
			List<Answer> a = sknow.getAnswersByQuestion(q);
			view.addObject("Question",q);
			view.addObject("answers",a);
			view.addObject("categories",sdict.getAllCategories());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/Questions/update.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateQuestionJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if (skey.equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			System.out.println("Entering to Question");
			String Question = request.getParameter("Question");
			String category = request.getParameter("category");
			String content = request.getParameter("content");
			String Question_id = request.getParameter("Question_id");
			String bs = "inAns";
			List<Object> lo = new ArrayList<Object>();
			lo.addAll(Arrays.asList(Question, category, content));
			if (-1 == lo.indexOf(null)){
				try {
					System.out.println("Update Question");
					//sknow.createQuestion(Question);
					//Question q = sknow.getQuestionByName(Question);
					Question q = sknow.getQuestionById(Long.parseLong(Question_id));
					if (q != null){
						q.setFk_catgory(sdict.getCategoryByName(category));
						q.setName(Question);
						q.setContent(content);
						sknow.updateQuestionRelation(q);
					}
					Integer chk= new Integer(1);
					sknow.deleteAnswersByQuestionId(Long.parseLong(Question_id));
					for (int i=0; i < Integer.parseInt(request.getParameter("acount")); i++){
						//String hkey = request.getParameter(bs.concat(chk.toString().concat("[answer]")));
						while (request.getParameter(bs.concat(chk.toString().concat("[answer]"))) == null){
							chk+=1;
						}
						System.out.println(bs.concat(chk.toString().concat("[answer]")));
						String preffix = bs.concat(chk.toString());
						sknow.createAnswer(request.getParameter(preffix.concat("[answer]")));
						Answer a = sknow.getAnswerByContent(request.getParameter(preffix.concat("[answer]")));
						a.setFk_Question(q);
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
						a.setFk_Question(q);
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
