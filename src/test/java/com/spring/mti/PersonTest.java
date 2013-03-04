package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
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
	public void testAddRecord() {
		PersonService service = (PersonService)context.getBean("servicePerson"); 
		Person person = new Person();
		person.setName("Testic");
		service.savePerson(person);
	}
}
