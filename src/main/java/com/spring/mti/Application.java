package com.spring.mti;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.AuthoritiesDaoImpl;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.security.Authorities;
import com.spring.mti.security.Users;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		/*
		Authentication arequest = new UsernamePasswordAuthenticationToken("uev", "123");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        am.authenticate(arequest);
        */
		UsersDao dao = (UsersDao)context.getBean("userDao");
		/*
		Users user = new Users();
		user.setUsermame("bazooka");
		user.setPassword("122");
		user.setEnabled(1);
		dao.createUser(user);
		*/
		//Users user_temp = dao.getUserByLoginName("bob");
		//dao.deleteUser(user_temp);

		//Users a = new Users();
        //a.setUsermame("dssd");
        //a.setPassword("dsdsds2");
        //dao.deleteUser(a);
		Users user = dao.getUserByLoginName("bazooka");
		Authorities a = new Authorities();
		a.setAuthorityUser(user);
		AuthoritiesDao role = (AuthoritiesDao)context.getBean("authoritiesDao");
		role.setPermissionUser(a);
		
		
		
		//AuthoritiesDao role = (AuthoritiesDao)context.getBean("authoritiesDao");
		//AuthoritiesDaoImpl role = new AuthoritiesDaoImpl();
		//role.setPermissionUser(a);
        
		
		
		/*
		Authentication arequest = new UsernamePasswordAuthenticationToken("bazooka", "122");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        try{
        	am.authenticate(arequest);
        }	catch(BadCredentialsException e){
        	System.out.println("Error auth!");
        }
        */
        
	}
}