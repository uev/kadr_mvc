package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.mti.domain.Person;
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
		person.setName("Testic");
		service.savePerson(person);
	}
	
	@Test
	public void testCustomUserQuery() {
		PersonService service = (PersonService)context.getBean("servicePerson"); 
		Person person= service.findPerson("Testic");
		System.out.println(person.getName());
	}
	
	
	
}
