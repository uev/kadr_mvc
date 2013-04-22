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

import com.spring.mti.model.TestKnowledge;
import com.spring.mti.service.CertificationService;

@Controller
public class CertificationController extends GeneralController implements BeanFactoryAware{
	private CertificationService scert;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		scert = (CertificationService)context.getBean("serviceCertification");
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/manage.html", method = RequestMethod.GET)
	public final ModelAndView testManageAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/tests/scripts.jsp"));
			view.addObject("title", "Админзона / управление тестами");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/tests/manage.jsp"));
			view.addObject("tests", scert.getAllTests());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendTestJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Entering post...");
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String test = request.getParameter("test");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (test != null){
				try {
					log.info("Try appending new test");
					scert.createtTest(test);
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending test. Input value:");
						log.info(test);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/rm.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popTestJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String test = request.getParameter("test");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (test != null){
				try {
					log.info("Try removing new test");
					scert.deleteTest(scert.getTestById(Long.parseLong(test)));
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending test. Input value:");
						log.info(test);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/edit.html", method = RequestMethod.GET)
	public final ModelAndView testEditAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/tests/scripts.jsp"));
			//view.addObject("title", "Админзона / управление тестами");
			//view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/tests/edit.jsp"));
			//view.addObject("tests", scert.getAllTests());
		}
		return view;
	}
}