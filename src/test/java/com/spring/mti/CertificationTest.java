package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.spring.mti.model.Certification;
import com.spring.mti.model.Employe;
import com.spring.mti.model.Queshion;
import com.spring.mti.model.RelCertificationEmploye;
import com.spring.mti.model.RelTestQueshion;
import com.spring.mti.model.TestKnowledge;
import com.spring.mti.service.CertificationService;
import com.spring.mti.service.DictionaryService;
import com.spring.mti.service.KnowledgesService;

public class CertificationTest extends AbstractTest {
	private CertificationService certsrv;
	private KnowledgesService ksrv;
	private DictionaryService dsrv;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		certsrv = (CertificationService)context.getBean("serviceCertification");
		ksrv = (KnowledgesService)context.getBean("serviceKnowledges");
		dsrv = (DictionaryService)context.getBean("serviceDictionary");
	}
	
	/*
	@Test
	public void testCreateTest(){
		certsrv.createtTest("Тест номер один");
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		assertTrue("Тест номер один".equals(t.getName()));
	 }
	 */
	
	@Test
	public void testPushQueshionToTest(){
		certsrv.createtTest("Тест номер один");
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		assertTrue("Тест номер один".equals(t.getName()));
		//TestKnowledge t = certsrv.getTestByName("Тест номер один");
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
		certsrv.deleteTest(t);
		assertTrue( null ==  certsrv.getTestByName("Тест номер один"));
	 }

	/*
	@Test
	public void testRmTest(){
		TestKnowledge t = certsrv.getTestByName("Тест номер один");
		certsrv.deleteTest(t);
		assertTrue( null ==  certsrv.getTestByName("Тест номер один"));
	 }
	 */
	
	@Test
	public void testCreateCertification(){
		certsrv.createCertification("Тестовая аттестация");
		Certification c = certsrv.getCertificationByName("Тестовая аттестация");
		assertTrue("Тестовая аттестация".equals(c.getName()));
	 }
	
	@Test
	public void testCreateCertificationProcess(){
		Certification c = certsrv.getCertificationByName("Тестовая аттестация");
		assertTrue("Тестовая аттестация".equals(c.getName()));
		certsrv.createtTest("Тест");
		TestKnowledge t = certsrv.getTestByName("Тест");
		assertTrue("Тест".equals(t.getName()));
		certsrv.setTestCertification(c, t);
		try {
			assertTrue(c.getFk_test().getId() > 0);
		 } catch(Exception e){
			 e.printStackTrace();
		 }
		dsrv.createEmploye("Тестовый работник");
		Employe em = dsrv.getEmployeByName("Тестовый работник").get(0);
		assertTrue("Тестовый работник".equals(em.getFio()));
		certsrv.pushEmployeToCertification(em, c);
		RelCertificationEmploye rel = certsrv.getEmployeInCertification(em, c);
		assertTrue(rel.getId() > 0);
		certsrv.popEmployeFromCertification(rel);
	 }
	
	@Test
	public void testRmCertification(){
		Certification c = certsrv.getCertificationByName("Тестовая аттестация");
		certsrv.deleteCertification(c);
		assertTrue( null ==  certsrv.getCertificationByName("Тестовая аттестация"));
	 }
}