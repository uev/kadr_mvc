package com.spring.mti.web;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Queshion;
import com.spring.mti.model.TestKnowledge;
import com.spring.mti.service.CertificationService;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;
import com.spring.mti.service.LayoutService;

@Controller
public class CertificationController extends GeneralController implements BeanFactoryAware{
	private CertificationService scert;
	private KnowledgesService sknow;
	private DictionaryService sdict;
	private LayoutService slayout;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		scert = (CertificationService)context.getBean("serviceCertification");
		sknow = (KnowledgesService)context.getBean("serviceKnowledges");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		slayout = (LayoutService)context.getBean("serviceLayout");
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
			view.addObject("categories", sdict.getAllCategories());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/append_queshion.html", method = RequestMethod.GET)
	public final ModelAndView appendToTestQueshionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/tests/scripts.jsp"));
			view.addObject("title", "Админзона / добавить вопросы");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/tests/appendquesh.jsp"));
			String testid = request.getParameter("id");
			Long id = Long.parseLong(testid);
			view.addObject("testname", scert.getTestById(id).getName());
			//diff
			List<Queshion> queshions = sknow.getAllQueshions();
			queshions.removeAll(scert.getListQueshionsFromTest(id));
			view.addObject("queshions", queshions);
		}
		return view;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(headers = {"Accept=application/json"}, value = "/admin/dictionary/knowledges/tests/append_queshion.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> sync(@RequestBody String json)  throws Exception {
		JSONObject json1 = (JSONObject) JSONSerializer.toJSON(URLDecoder.decode(json.substring(0, json.length()-1)));
		Map<String, Object> answ = new HashMap<String, Object>();
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(json1.getString("hash"))){
			String testname = json1.getString("testname");
			TestKnowledge test = scert.getTestByName(testname);
			JSONArray jarray = json1.getJSONArray("queshions");
			if (test != null){
				try {
					log.info("Try appending queshion test");
					for (int i=0; i < jarray.size(); i++) {
						scert.pushQueshionToTest(test, sknow.getQueshionById(Long.parseLong((String)jarray.get(i))));
					}
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending to test.");
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(headers = {"Accept=application/json"}, value = "/admin/dictionary/knowledges/tests/pop_queshion.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popQueshionsFromTest(@RequestBody String json)  throws Exception {
		JSONObject json1 = (JSONObject) JSONSerializer.toJSON(URLDecoder.decode(json.substring(0, json.length()-1)));
		Map<String, Object> answ = new HashMap<String, Object>();
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(json1.getString("hash"))){
			String testname = json1.getString("testname");
			TestKnowledge test = scert.getTestByName(testname);
			JSONArray jarray = json1.getJSONArray("queshions");
			if (test != null){
				try {
					for (int i=0; i < jarray.size(); i++) {
						scert.popQueshionFromTest(scert.getQueshionFromTest(test, sknow.getQueshionById(Long.parseLong((String)jarray.get(i)))));
					}
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error removing from test.");
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}

	@RequestMapping(value = "/admin/dictionary/knowledges/tests/getinfo.html", method = RequestMethod.POST)
	public @ResponseBody List<Queshion> getAllQueshionsFromTestJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			try{
				String testname = request.getParameter("test");
				TestKnowledge t = scert.getTestByName(testname);
				List<Queshion> lstq = scert.getListQueshionsFromTest(t.getId());	
				return lstq;
			} catch (Exception e) {
				log.error("Error create list queshions");
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	@RequestMapping(value = "/admin/dictionary/knowledges/tests/pop_queshion.html", method = RequestMethod.GET)
	public final ModelAndView popFromTestQueshionAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/dictionary/knowledges/tests/scripts.jsp"));
			view.addObject("title", "Админзона / Исключить вопросы");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/dictionary/knowledges/tests/rmansw.jsp"));
			String testid = request.getParameter("id");
			Long id = Long.parseLong(testid);
			view.addObject("testname", scert.getTestById(id).getName());
			//diff
			List<Queshion> queshions = scert.getListQueshionsFromTest(id);
			view.addObject("queshions", queshions);
		}
		return view;
	}
}