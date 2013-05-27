package com.spring.mti.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Certification;
import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.Queshion;
import com.spring.mti.model.RelCertificationEmploye;
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
	private PasswordEncoder pencoder;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		scert = (CertificationService)context.getBean("serviceCertification");
		sknow = (KnowledgesService)context.getBean("serviceKnowledges");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
		slayout = (LayoutService)context.getBean("serviceLayout");
		pencoder = (Md5PasswordEncoder)context.getBean("passwordEncoder");
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

	@RequestMapping(value = "/admin/certification/index.html", method = RequestMethod.GET)
	public final ModelAndView indexAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("admin/certification/index");
			view.addObject("title", "Админзона / аттестация");
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/certification/manage.html", method = RequestMethod.GET)
	public final ModelAndView manageCertificationAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			System.out.println("123");
			view.addObject("hscript", viewPrefix.concat("/admin/certification/scripts.jsp"));
			view.addObject("title", "Админзона / управление аттестациями");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/certification/manage.jsp"));
			view.addObject("cert", scert.getAllCertifications());
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/certification/add.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendCertificationJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String cert = request.getParameter("cert");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (cert != null){
				try {
					scert.createCertification(cert);
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error appending test. Input value:");
						log.info(cert);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/certification/edit.html", method = RequestMethod.GET)
	public final ModelAndView editCertificationAction(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("default/index");
			view.addObject("hscript", viewPrefix.concat("/admin/certification/scripts.jsp"));
			view.addObject("title", "Админзона / параметры аттестации");
			view.addObject("menu", viewPrefix.concat("/admin/menu.jsp"));
			view.addObject("body", viewPrefix.concat("/admin/certification/edit.jsp"));
			view.addObject("tests", scert.getAllTests());
			view.addObject("departments", sdict.getAllDepartments());
			String cert_id = request.getParameter("id");
			if (null != cert_id) {
				Certification c = scert.getCertificationById(Long.parseLong(cert_id));
				List<Employe> r = scert.getListEmployeByCertification(c);
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", String.valueOf(c.getId()));
				map.put("name", c.getName());
				TestKnowledge t = c.getFk_test(); 
				if (null != t){
					view.addObject("current_test", t.getName());
				} else {
					view.addObject("current_test", "");
				}
				view.addObject("cert_title", map);
				view.addObject("cert_employers", slayout.employeToMapJson(r));
			}
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/certification/getemployers.html", method = RequestMethod.POST)
	public @ResponseBody List<Employe> getEmployersByDepartment(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			Map<String, Object> answ = new HashMap<String, Object>();
			String department = request.getParameter("department");
			Department d = sdict.getDepartmentByName(department);
			try {
				List<Employe> em = sdict.getEmployersByDepartment(d);
				return em;
			} catch (Exception e) {
				log.error("Error creating list employers");
				e.printStackTrace();
			}
		} 
		return null;
	}
	
	@RequestMapping(value = "/admin/certification/set_test.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> setTestCertificationJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String certification = request.getParameter("certification");
			String testname = request.getParameter("testname");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (testname != null){
				try {
					TestKnowledge t = scert.getTestByName(testname);
					Certification c = scert.getCertificationByName(certification);
					System.out.println(c.getName());
					scert.setTestCertification(c, t);
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error setting test certification");
						log.info(testname);
						log.info(certification);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/certification/append_person.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> appendPersonTpCertification(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		System.out.println("Entering post...");
		String key = request.getParameter("hash");
		System.out.println(key);
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String employe = request.getParameter("employe");
			String certification = request.getParameter("certification");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (employe != null){
				try {
					log.info("Try appending person to  certification");
					Employe e = sdict.getEmployeById(Long.parseLong(employe));
					if (e != null) {
						Certification c = scert.getCertificationByName(certification);
						RelCertificationEmploye r = scert.getEmployeInCertification(e, c);
						if (r == null) {
							scert.pushEmployeToCertification(e, c);
							answ.put("error", 0);
						}
					}
				} catch(Exception e){
					answ.put("error", 1);
					log.error("Error appending test. Input value:");
					log.info(employe);
					e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	@RequestMapping(value = "/admin/certification/pop_person.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> popEmployeFromCertJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String employe = request.getParameter("employe");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (employe != null){
				try {
					log.info("Try removing employe from certification");
					Employe e = sdict.getEmployeById(Long.parseLong(employe));
					Certification c = scert.getCertificationByName(request.getParameter("certification"));
					scert.popEmployeFromCertification(scert.getEmployeInCertification(e, c));
					answ.put("error", 0);
					} catch(Exception e){
						answ.put("error", 1);
						log.error("Error removing certification from employe. Input value:");
						log.info(employe);
						e.printStackTrace();
				}		
				return answ;
			}
		}	
		return null;
	}
	
	
	@RequestMapping(value = "/candidate/certification/proxy.html", method = RequestMethod.GET)
	public final ModelAndView certificationProxy(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("id");
		if (null != cid){
			HttpSession session = request.getSession();
			String login = (String)session.getAttribute("login");
			try {
				Certification cert = scert.getCertificationById(Long.parseLong(cid));
				Employe em = authStorage.getUserByLoginName(login).getFk_employe();
				List<Employe> r = scert.getListEmployeByCertification(cert);
				if (r.contains(em) == true){
					String secret = "User " + em.getFio() + " begining certification: " + cert.getName();  
					log.info(secret);
					session.setAttribute("userHash", pencoder.encodePassword(secret,null));
					session.setAttribute("employe", em);
					session.setAttribute("certification", cert);
					return new ModelAndView("redirect:/candidate/certification/process.html");
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * Тестирование
	 */
	@RequestMapping(value = "/candidate/certification/process.html", method = RequestMethod.GET)
	public final ModelAndView procedureCertification(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView();
		try{
			Certification c = (Certification)session.getAttribute("certification");
			Employe em = (Employe)session.getAttribute("employe");
			String etalon_hash = (String)session.getAttribute("userHash");
			String secret = "User " + em.getFio() + " begining certification: " + c.getName();
			if (!etalon_hash.equals(pencoder.encodePassword(secret,null))){
				log.error("Secrets not equals! Escape...");
				throw new Exception();
			}
			view.setViewName("candidate/process");
			/*
			 * Передача вопросника
			 */
			List<Queshion> lq = scert.getListQueshionsFromTest(c.getFk_test().getId());
			Map<String, List<Answer>> lo = new HashMap<String, List<Answer>>();
			for (Queshion q : lq) {
				lo.put(q.getContent(), sknow.getAnswersByQueshion(q));
			}
			view.addObject("answers", lo);
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			view.setViewName("redirect:/");
		}		
		return null;
	}
}