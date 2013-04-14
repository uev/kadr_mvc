package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Category;
import com.spring.mti.model.Queshion;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;

public class KnowledgeTest extends AbstractTest{
	private DictionaryService dsrv;
	private KnowledgesService ksrv;
	

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dsrv = (DictionaryService)context.getBean("serviceDictionary");
		ksrv = (KnowledgesService)context.getBean("serviceKnowledges");
	}
	
	@Test
	public void testCreateCategoryAns(){
		dsrv.createCategory("Тестовая");
		Category d = dsrv.getCategoryByName("Тестовая");
		assertTrue("Тестовая".equals(d.getCname()));
	 }
	
	@Test
	public void testCreateQueshion(){
		ksrv.createQueshion("О слонах");
		Queshion q = ksrv.getQueshionByName("О слонах");
		assertTrue("О слонах".equals(q.getName()));
	 }
	
	@Test
	public void testAppendQueshionToCategory(){
		Queshion q = ksrv.getQueshionByName("О слонах");
		Category d = dsrv.getCategoryByName("Тестовая");
		q.setFk_catgory(d);
		ksrv.updateQueshionRelation(q);
		Queshion q1 = ksrv.getQueshionByName("О слонах");
		Category d1 = q1.getFk_catgory();
		assertTrue("Тестовая".equals(d1.getCname()));
	 }
	
	@Test
	public void testCreateAnswer(){
		ksrv.createAnswer("Ответ");
		Answer a = ksrv.getAnswerByContent("Ответ");
		assertTrue("Ответ".equals(a.getContent()));
	 }
	
	@Test
	public void testBindingAnswetToQueshion(){
		Queshion q = ksrv.getQueshionByName("О слонах");
		Answer a = ksrv.getAnswerByContent("Ответ");
		a.setFk_queshion(q);
		ksrv.updateAnswerRelation(a);
		Answer a1 = ksrv.getAnswerByContent("Ответ");
		Queshion q1 = a1.getFk_queshion();
		assertTrue("О слонах".equals(q1.getName()));
	 }
	
	@Test
	public void testRmAnswer(){
		Answer a = ksrv.getAnswerByContent("Ответ");
		ksrv.deleteAnswer(a);
		assertTrue(null ==  ksrv.getAnswerByContent("Ответ"));
	 }
	
	@Test
	public void testRmQueshion(){
		Queshion q = ksrv.getQueshionByName("О слонах");
		ksrv.deleteQueshion(q);
		assertTrue(null ==  ksrv.getQueshionByName("О слонах"));
	 }

	@Test
	public void testRmCategoryAns(){
		Category d = dsrv.getCategoryByName("Тестовая");
		dsrv.deleteCategory(d);
		assertTrue(null ==  dsrv.getCategoryByName("Тестовая"));
	 }
}