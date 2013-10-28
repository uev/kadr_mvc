package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Certification;
import com.spring.mti.model.CertificationState;
import com.spring.mti.model.Employe;
import com.spring.mti.model.Question;
import com.spring.mti.model.RelCertificationEmploye;
import com.spring.mti.model.RelTestQuestion;
import com.spring.mti.model.TestKnowledge;

public interface CertificationService {
	void createtTest(String name);
	TestKnowledge getTestByName(String name);
	void deleteTest(TestKnowledge t);
	RelTestQuestion getQuestionFromTest(TestKnowledge t, Question q);
	void pushQuestionToTest(TestKnowledge t, Question q);
	void popQuestionFromTest(RelTestQuestion t);
	List<TestKnowledge> getAllTests();
	TestKnowledge getTestById(Long id);
	List<Question> getListQuestionsFromTest(Long id);
	Certification getCertificationByName(String name);
	Certification getCertificationById(Long id);
	void deleteCertification(Certification t);
	void createCertification(String name);
	void pushEmployeToCertification(Employe e, Certification c);
	RelCertificationEmploye getEmployeInCertification(Employe e, Certification c);
	void setTestCertification(Certification c, TestKnowledge t);
	void popEmployeFromCertification(RelCertificationEmploye r);
	List<Certification> getAllCertifications();
	List<Employe> getListEmployeByCertification(Certification c);
	List<RelCertificationEmploye> getListCertificationByEmploye(Employe e);
	void commitAnswer(CertificationState c);
	void commitCertification(Employe em, Certification c);
	List<RelCertificationEmploye> findCompletedCertifications();
	RelCertificationEmploye getRelationshCertificationEmploye(long id);
	List<CertificationState> getCertificationCompletedSession(
			RelCertificationEmploye r);
}