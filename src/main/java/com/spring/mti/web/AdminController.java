package com.spring.mti.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mti.model.Employe;
import com.spring.mti.model.security.Authorities;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.LayoutService;

@Controller
public class AdminController extends GeneralController implements BeanFactoryAware {
	//private CustomUserDetailsService authStorage;
	private AuthorityService sauth;
	private LayoutService slayout;
	private DictionaryService sdict;
	static Logger log = Logger.getLogger(LoginController.class.getName());

	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
	//	authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
		super.setBeanFactory(context);
		sauth = (AuthorityService)context.getBean("serviceRole");
		slayout = (LayoutService)context.getBean("serviceLayout");
		sdict = (DictionaryService)context.getBean("serviceDictionary");
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView("admin/index");
		if (authStorage.isAdminRoleSet((String)session.getAttribute("login"))){
			/*
			 * Маршрутизация в админзоне
			 */
			if (request.getParameter("accounting") != null) {
				view.setViewName("admin/accounting");
				if (request.getParameter("listacc") != null) {
					//return JsonView.Render(authStorage.getAllUserNames(), response);
					Map<String, List> testMap = new HashMap<String, List>();
					//testMap.put("users", authStorage.getAllUsersPermissions());
					//JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(authStorage.getAllUsersPermissions());  
					//view.addObject("json", jsonObject);
					//return new ModelAndView("jsonpattern").addObject("json", jsonObject);
					view.addObject("json", authStorage.getAllUsersPermissions());
				}
	
				/*
				 * Удаление аккаунта
				 */
				if (request.getParameter("unbindlogin") != null) {
					view.addObject("rmusers", 1);
					String login = request.getParameter("login");
					if (login != null) {
						try {
							authStorage.deleteUser(authStorage.getUserByLoginName(login));
							view.addObject("error", 0);
							} catch (Exception e){
								view.addObject("error", 1);
							}
						}
					}	
				/*
				 * Создание аккаунта
				 */
				if (request.getParameter("bindlogin") != null) {
					String login = request.getParameter("login");
					String role = request.getParameter("role");
					if (login != null) {
						if ( authStorage.getUserByLoginName(login).getUsername() == null){
							try {
								Users user = new Users();
								user.setUsermame(request.getParameter("login"));
								user.setPassword(request.getParameter("password"));
								user.setEnabled(1);
								authStorage.setSalt(user);
								authStorage.createUser(user);
								sauth.setPermissions(user, sauth.getRoleByName(role));
								view.addObject("error", 0);
							}
							catch(Exception e) {
								view.addObject("error", 1);
								System.out.println("Exeption");
							}
						} else {
							view.addObject("error", 1);
						}
					}
					view.addObject("roles", sauth.getAllRoles());
				}
				
			}
			return view;
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/admin/index.html", method = RequestMethod.GET)
	public ModelAndView indexAction(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("admin/index");
		}
		return view;	
	}
	
	/*
	 * Просмотр аккаунтов 
	 */
	@RequestMapping(value = "/admin/accounting.html", method = RequestMethod.GET)
	public ModelAndView accountingAction(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		HttpSession s = request.getSession();
		ModelAndView view = verifyPermission(s);
		if (view.getViewName() == null){
			System.out.println("Set view");
			view.setViewName("admin/accounting");
			//view.addObject("json", authStorage.getAllUsersPermissions());
			List<Authorities> la = sauth.getAllPermissions();
			view.addObject("json", slayout.authorityToMapJson(la));
			view.addObject("title", "Админзона / управление логинами");
		}
		return view;	
	}
	
	
	/*
	 * Удаление  логина. Разрушение связи Пользователь <=> Логин 
	 */
	@RequestMapping(value = "/admin/unbindlogin.html", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView unbindloginAction(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		ModelAndView view = verifyPermission(request.getSession());
		log.info("Enter to unbindlogin");
		if (view.getViewName() == null){
			log.debug("Set view admin/accounting");
			view.setViewName("admin/accounting");
			view.addObject("rmusers", 1);
			view.addObject("title", "Админзона / удаление логина");
			view.addObject("form_unbind", request.getRequestURL());
			String username = request.getParameter("login");
			if  (username != null) { 
				try {
					log.debug(username);
					authStorage.deleteUser(authStorage.getUserByLoginName(username));
					view.addObject("error", 0);
				} catch (Exception e){
					log.error("Error removing login");
					view.addObject("error", 1);
					e.printStackTrace();
				}
			}
		}
		return view;	
	}
	
	/*
	 *  Привязка пользователя к логину (представление) 
	 */
	@RequestMapping(value = "/admin/bindlogin.html", method = RequestMethod.GET)
	public ModelAndView bindloginAction(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		ModelAndView view = verifyPermission(request.getSession());
		log.info("Enter to bindlogin");
		if (view.getViewName() == null){	
			view.setViewName("admin/accounting");
			String login = request.getParameter("login");
			String role = request.getParameter("role");
			view.addObject("title", "Админзона / добавление логина");
			view.addObject("form_bind", request.getRequestURL());
			view.addObject("form_bind", request.getRequestURL());
			List<Object> sss= slayout.employeToMapJson(sdict.getEmployeAll());
			view.addObject("allemp", sss);
			
			if (login != null) {
				/*
				if ( authStorage.getUserByLoginName(login).getUsername() == null){
					try {
						Users user = new Users();
						user.setUsermame(request.getParameter("login"));
						user.setPassword(request.getParameter("password"));
						user.setEnabled(1);
						authStorage.setSalt(user);
						authStorage.createUser(user);
						sauth.setPermissions(user, sauth.getRoleByName(role));
						view.addObject("error", 0);
					}
					catch(Exception e) {
						view.addObject("error", 1);
						System.out.println("Exeption");
					}
				} else {
					view.addObject("error", 1);
				}
				*/
			}
			view.addObject("roles", sauth.getAllRoles());
		}
		return view;	
	}
	
	
	/*
	 *  Привязка пользователя к логину (Вебсервис) 
	 */
	@RequestMapping(value = "/admin/json/bindlogin.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> bindloginJson(HttpServletRequest request,
			HttpServletResponse response)  throws Exception {
		String key = request.getParameter("hash");
		if ("dcd95bcb84b09897b2b66d4684c040da".equals(key)){
			String login = request.getParameter("login");
			String passwd = request.getParameter("password");
			String role = request.getParameter("role");
			String idemp = request.getParameter("idemp");
			Map<String, Object> answ = new HashMap<String, Object>();
			if (login != null){
				if ( authStorage.getUserByLoginName(login).getUsername() == null){
					log.info("User not founded. Tty reg. Id: ");
					log.info(idemp);
					try {
						Users user = new Users();
						user.setUsermame(login);
						user.setPassword(passwd);
						user.setEnabled(1);
						user.setFk_employe(sdict.getEmployeById(Long.parseLong(idemp)));
						log.info("Bind login");
						authStorage.setSalt(user);
						authStorage.createUser(user);
						sauth.setPermissions(user, sauth.getRoleByName(role));
						answ.put("error", 0);
					}
					catch(Exception e) {
						answ.put("error", 1);
						log.error("Error login binding");
						e.printStackTrace();
					}
				} else {
					answ.put("error", 1);
				}
			return answ;	
			}
		}
		return null;
	}
}