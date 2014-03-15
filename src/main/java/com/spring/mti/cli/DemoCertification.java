package com.spring.mti.cli;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sun.util.BuddhistCalendar;

import com.spring.mti.model.Certification;
import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.RelCertificationEmploye;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AddressService;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CertificationService;
import com.spring.mti.service.CustomUserDetailsService;
import com.spring.mti.service.DictionaryService;

public class DemoCertification {
	private static DictionaryService dsrv;
	private static CustomUserDetailsService dao;
	private static AuthorityService sauth;
	private static CertificationService certsrv;
	private static AddressService saddr;
	private static Employe empl;
	private static int ch = 100500;
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
		saddr = (AddressService)context.getBean("serviceAddress");
		Scanner  io = new Scanner(System.in);
		System.out.println("Input employe name");
		String fio = io.nextLine();
		try{
			empl = dsrv.getEmployeByName(fio).get(0);
			System.out.println("Unexpected name employe");
			System.exit(0);
		} catch (IndexOutOfBoundsException e){
			dsrv.createEmploye(fio);
			empl = dsrv.getEmployeByName(fio).get(0);
			empl.setFk_city(saddr.getCitiyByName("Москва"));
		}
		System.out.println("Select department...");
		List<Department> lstd =    dsrv.getAllDepartments();
		HashMap<Integer, Department> map = new HashMap<Integer, Department>();
		for(int i=0; i < lstd.size(); i++){
			Department d = lstd.get(i); 
			map.put(i+1, d);
			System.out.printf("%d %s\n", i+1, d.getName());
		}
		while (!map.containsKey(ch)) {
			try {
				ch = Integer.parseInt(io.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("Unexpected number");
			}
			if (map.containsKey(ch)){
				empl.setFk_department(map.get(ch));
				dsrv.updateEmployeRelation(empl);
			}
		}
		//Создание логина
		Users u = new Users(); 
		System.out.println("IoC inited");
		System.out.println("Input login");
		u.setUsermame(io.next());
		System.out.println("Input password");
		u.setPassword(io.next());
		u.setEnabled(1);
		u.setFk_employe(empl);
		dao.setSalt(u);
		dao.createUser(u);
		Role role = sauth.getRoleByName("ROLE_USER");
		sauth.setPermissions(u,role);
		Certification c = certsrv.getCertificationByName("Итоговая 1");
		certsrv.pushEmployeToCertification(u.getFk_employe(), c);
	}
		//certsrv.popEmployeFromCertification(certsrv.getEmployeInCertification(u.getFk_employe(), c));
		//назначаем тестирование
		//certsrv.pushEmployeToCertification(u.getFk_employe(), c);
}
