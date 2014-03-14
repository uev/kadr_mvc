package com.spring.mti.cli;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.model.Certification;
import com.spring.mti.model.Employe;
import com.spring.mti.model.RelCertificationEmploye;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CertificationService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.DictionaryService;

public class DemoCertification {
	private static DictionaryService dsrv;
	private static CustomUserDetailsService dao;
	private static AuthorityService sauth;
	private static CertificationService certsrv;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		dsrv = (DictionaryService)context.getBean("serviceDictionary");
		dao = (CustomUserDetailsService)context.getBean("userDetailsService");
		sauth = (AuthorityService)context.getBean("serviceRole");
		certsrv = (CertificationService)context.getBean("serviceCertification");
		Scanner  io = new Scanner(System.in);
		//Создание логина
		Users u = new Users(); 
		System.out.println("IoC inited");
		System.out.println("Input login");
		u.setUsermame(io.next());
		System.out.println("Input password");
		u.setUsermame(io.next());
		u.setEnabled(1);
		u.setFk_employe((Employe)dsrv.getEmployeByName("Тест Тестович Тестов").get(0));
		dao.setSalt(u);
		dao.createUser(u);
		Role role = sauth.getRoleByName("ROLE_USER");
		sauth.setPermissions(u,role);
		Certification c = certsrv.getCertificationByName("Итоговая 1");
		certsrv.popEmployeFromCertification(certsrv.getEmployeInCertification(u.getFk_employe(), c));
		//назначаем тестирование
		certsrv.pushEmployeToCertification(u.getFk_employe(), c);
	}
}
