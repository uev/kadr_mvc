package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.spring.mti.model.Category;
import com.spring.mti.service.DictionaryService;

public class AnswerTest extends AbstractTest{
	private DictionaryService dsrv;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dsrv = (DictionaryService)context.getBean("serviceDictionary");
	}
	
	@Test
	public void testCreateCategoryAns(){
		dsrv.createCategory("Тестовая");
		Category d = dsrv.getCategoryByName("Тестовая");
		assertTrue("Тестовая".equals(d.getCname()));
	 }
	
	@Test
	public void testRmCategoryAns(){
		Category d = dsrv.getCategoryByName("Тестовая");
		dsrv.deleteCategory(d);
		assertTrue(null ==  dsrv.getCategoryByName("Тестовая"));
	 }
}