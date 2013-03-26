package com.spring.mti.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.spring.mti.model.address.Region;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.AuthorityService;

@Controller
public class DictionaryController extends GeneralController implements BeanFactoryAware {
	
	private AuthorityService sauth;
	private AddressService saddr;
	
	@Override
	public void setBeanFactory(BeanFactory context) throws BeansException {
		super.setBeanFactory(context);
		sauth = (AuthorityService)context.getBean("serviceRole");
		saddr = (AddressService)context.getBean("serviceAddress");
	}

	
	@RequestMapping(value = "/admin/dictionary/index.html", method = RequestMethod.GET)
	public final ModelAndView mainViewDictionary(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("admin/dictionary/index");
		}
		return view;
	}
	
	@RequestMapping(value = "/admin/dictionary/addemploye.html", method = RequestMethod.GET)
	public final ModelAndView addEmploye(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = verifyPermission(request.getSession());
		if (view.getViewName() == null){
			view.setViewName("admin/dictionary/addemploye");
		}
		return view;
	}
	
	@RequestMapping(value = "/json/getcountry.html", method = RequestMethod.POST)
	public  @ResponseBody List<Region> getRegion(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Catch POST");
		Users u = new Users();
		return saddr.getRegions(63);
	}
}
