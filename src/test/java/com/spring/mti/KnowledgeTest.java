package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Category;
import com.spring.mti.model.Question;
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
	public void testCreateQuestion(){
		ksrv.createQuestion("О слонах");
		Question q = ksrv.getQuestionByName("О слонах");
		assertTrue("О слонах".equals(q.getName()));
	 }
	
	@Test
	public void testAppendQuestionToCategory(){
		Question q = ksrv.getQuestionByName("О слонах");
		Category d = dsrv.getCategoryByName("Тестовая");
		q.setFk_catgory(d);
		ksrv.updateQuestionRelation(q);
		Question q1 = ksrv.getQuestionByName("О слонах");
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
	public void testBindingAnswetToQuestion(){
		Question q = ksrv.getQuestionByName("О слонах");
		Answer a = ksrv.getAnswerByContent("Ответ");
		a.setFk_Question(q);
		ksrv.updateAnswerRelation(a);
		Answer a1 = ksrv.getAnswerByContent("Ответ");
		Question q1 = a1.getFk_Question();
		assertTrue("О слонах".equals(q1.getName()));
	 }
	
	@Test
	public void testRmAnswer(){
		Answer a = ksrv.getAnswerByContent("Ответ");
		ksrv.deleteAnswer(a);
		assertTrue(null ==  ksrv.getAnswerByContent("Ответ"));
	 }
	
	@Test
	public void testRmQuestion(){
		Question q = ksrv.getQuestionByName("О слонах");
		ksrv.deleteQuestion(q);
		assertTrue(null ==  ksrv.getQuestionByName("О слонах"));
	 }

	@Test
	public void testRmCategoryAns(){
		Category d = dsrv.getCategoryByName("Тестовая");
		dsrv.deleteCategory(d);
		assertTrue(null ==  dsrv.getCategoryByName("Тестовая"));
	 }
}