package com.spring.mti;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.dao.UsersDao;
import com.spring.mti.dao.UsersDaoImpl;
import com.spring.mti.model.Person;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.PersonService;

public class PersonTest {
	private static ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
	}

	@Test
	@Ignore
	public void testAddRecord() {
		PersonService service = (PersonService)context.getBean("servicePerson"); 
		Person person = new Person();
		person.setName("fffdsfsdTesticDrwerweref2");
		//person.setAge(12);
		service.savePerson(person);	
	}
	
	@Test
	public void testCustomUserQuery() {
		PersonService service = (PersonService)context.getBean("servicePerson"); 
		Person person= service.findPerson("Testic");
		System.out.println(person.getName());
	}
}