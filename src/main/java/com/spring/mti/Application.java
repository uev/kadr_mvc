package com.spring.mti;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

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
		Users user = new Users();
		user.setUsermame("bob");
		user.setPassword("1235432");
		dao.createUser(user);
		Users user_temp = dao.getUserByLoginName("bob");
		dao.deleteUser(user_temp);

		//Users a = new Users();
        //a.setUsermame("dssd");
        //a.setPassword("dsdsds2");
        //dao.deleteUser(a);
        Authorities role = new Authorities();
	}
}