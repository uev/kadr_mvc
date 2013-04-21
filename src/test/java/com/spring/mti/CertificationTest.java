package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.spring.mti.model.Queshion;
import com.spring.mti.model.RelTestQueshion;
import com.spring.mti.model.TestKnowledge;
import com.spring.mti.service.CertificationService;
import com.spring.mti.service.KnowledgesService;

public class CertificationTest extends AbstractTest {
	private CertificationService certsrv;
	private KnowledgesService ksrv;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		certsrv = (CertificationService)context.getBean("serviceCertification");
		ksrv = (KnowledgesService)context.getBean("serviceKnowledges");
	}
	
	@Test
	public void testCreateTest(){
		certsrv.createtTest("Тест номер один");
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		assertTrue("Тест номер один".equals(t.getName()));
	 }
	
	@Test
	public void testPushQueshionToTest(){
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		 ksrv.createQueshion("Контрольный вопрос");
		Queshion q = ksrv.getQueshionByName("Контрольный вопрос");
		certsrv.pushQueshionToTest(t, q);
		RelTestQueshion r = certsrv.getQueshionFromTest(t, q);
		assertTrue(null != r);
	 }
	
	@Test
	public void testPopQueshionToTest(){
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		Queshion q = ksrv.getQueshionByName("Контрольный вопрос");
		RelTestQueshion r = certsrv.getQueshionFromTest(t, q);
		certsrv.popQueshionFromTest(r);
		assertTrue(null == certsrv.getQueshionFromTest(t, q));
		ksrv.deleteQueshion(q);
		assertTrue(null ==  ksrv.getQueshionByName("Контрольный вопрос"));
	 }

	@Test
	public void testRmTest(){
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		certsrv.deleteTest(t);
		assertTrue( null ==  certsrv.getTestByName("Тест номер один"));
	 }
}