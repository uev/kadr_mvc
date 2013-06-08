package com.spring.mti.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.mti.dao.CertificationDao;
import com.spring.mti.dao.CertificationStateDao;
import com.spring.mti.dao.EmployeDao;
import com.spring.mti.dao.QueshionDao;
import com.spring.mti.dao.RelCertificationEmployeDao;
import com.spring.mti.dao.RelTestQueshionDao;
import com.spring.mti.dao.TestKnowledgeDao;
import com.spring.mti.model.Certification;
import com.spring.mti.model.CertificationState;
import com.spring.mti.model.Employe;
import com.spring.mti.model.Queshion;
import com.spring.mti.model.RelCertificationEmploye;
import com.spring.mti.model.RelTestQueshion;
import com.spring.mti.model.TestKnowledge;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@Repository
public class CertificationServiceImpl implements CertificationService {
	@Autowired private TestKnowledgeDao testDao;
	@Autowired private RelTestQueshionDao test_queshionDao;
	@Autowired private QueshionDao queshionDao;
	@Autowired private CertificationDao certificationDao;
	@Autowired private CertificationStateDao certificationStateDao;
	@Autowired private RelCertificationEmployeDao certification_employe;
	@Autowired private EmployeDao employeDao;
	
	@Override
	public void createtTest(String name) {
		List<TestKnowledge>res = testDao.findByNamedQuery("select s from TestKnowledge s where s.name=?1",Arrays.asList(name).toArray());
		if ( res != null && res.size()<1) {
			TestKnowledge t = new TestKnowledge();
			t.setName(name);
			testDao.create(t);
		}
	}
	
	@Override
	public TestKnowledge getTestByName(String name) {
		try{
			return testDao.findByNamedQuery("select s from TestKnowledge s where s.name=?1",Arrays.asList(name).toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public TestKnowledge getTestById(Long id) {
		return testDao.getByid(new TestKnowledge(), id);
	}
	
	@Override
	public void deleteTest(TestKnowledge t) {
		testDao.delete(t);
	}
	
	@Override
	public void pushQueshionToTest(TestKnowledge t, Queshion q) {
		if (null == this.getQueshionFromTest(t, q)){
			RelTestQueshion r = new RelTestQueshion();
			r.setFk_queshion(q);
			r.setFk_test(t);
			test_queshionDao.create(r);
		}
	}
	
	@Override
	public RelTestQueshion getQueshionFromTest(TestKnowledge t, Queshion q) {
		List<Long> param = Arrays.asList(t.getId(),q.getId());
		try {
			return test_queshionDao.findByNamedQuery("select s from RelTestQueshion s where s.fk_test.id=?1 and s.fk_queshion.id=?2",param.toArray()).get(0);
		} catch(IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public void popQueshionFromTest(RelTestQueshion t) {
		test_queshionDao.delete(t);
	}
	
	@Override
	public List<TestKnowledge> getAllTests() {
		try{
			List<TestKnowledge> t =testDao.findAll(new TestKnowledge());
			t.size();
			return t;
		} catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public List<Queshion> getListQueshionsFromTest(Long id) {
		try{
			List<Queshion> q = queshionDao.findByNamedQuery("select q from RelTestQueshion s, Queshion q where s.fk_test.id=?1 and s.fk_queshion.id=q.id",Arrays.asList(id).toArray());
			q.size();
			return q;
		} catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public void createCertification(String name) {
		List<Certification> res = certificationDao.findByNamedQuery("select s from Certification s where s.name=?1",Arrays.asList(name).toArray());
		if ( res != null && res.size()<1) {
			Certification t = new Certification();
			t.setName(name);
			certificationDao.create(t);
		}
	}
	
	@Override
	public Certification getCertificationByName(String name) {
		try{
			return certificationDao.findByNamedQuery("select s from Certification s where s.name=?1",Arrays.asList(name).toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public Certification getCertificationById(Long id) {
		return certificationDao.getByid(new Certification(), id);
	}
	
	@Override
	public List<Certification> getAllCertifications() {
		try{
			List<Certification> c = certificationDao.findAll(new Certification());
			c.size();
			return c;
		} catch(IndexOutOfBoundsException e) {
			System.out.println(e);
			return null;
		}
	}
	
	@Override
	public void deleteCertification(Certification t) {
		certificationDao.delete(t);
	}
	
	
	@Override
	public RelCertificationEmploye getEmployeInCertification(Employe e, Certification c) {
		List<Long> param = Arrays.asList(e.getId(),c.getId());
		try {
			return certification_employe.findByNamedQuery("select s from RelCertificationEmploye s where s.fk_certification.id=?2 and s.fk_employe.id=?1",param.toArray()).get(0);
		} catch(IndexOutOfBoundsException err) {
			return null;
		}
	}
	
	@Override
	public void commitCertification(Employe em, Certification c){
		RelCertificationEmploye r = this.getEmployeInCertification(em, c);
		r.setComplete(true);
		Date d = new Date();
		r.setDatecomplete(d);
		certification_employe.update(r);
	}

	@Override
	public List<Employe> getListEmployeByCertification(Certification c) {
		List<Long> param = Arrays.asList(c.getId());
		try {
			return employeDao.findByNamedQuery("select s.fk_employe from RelCertificationEmploye s where s.fk_certification.id=?1",param.toArray());
		} catch(Exception err) {
			err.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void pushEmployeToCertification(Employe e, Certification c) {
		if (null == this.getEmployeInCertification(e, c)){
			RelCertificationEmploye r = new RelCertificationEmploye();
			r.setFk_certification(c);
			r.setFk_employe(e);
			certification_employe.create(r);
		}
	}
	
	@Override
	public void popEmployeFromCertification(RelCertificationEmploye r) {
		certification_employe.delete(r);
	}
	
	@Override
	public void setTestCertification(Certification c, TestKnowledge t) {
		try{
			t.getId();
			c.setFk_test(t);
			certificationDao.update(c);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<RelCertificationEmploye> getListCertificationByEmploye(Employe e) {
		List<Long> param = Arrays.asList(e.getId());
		try {
			return certification_employe.findByNamedQuery("select s from RelCertificationEmploye s where s.fk_employe.id=?1",param.toArray());
		} catch(Exception err) {
			err.printStackTrace();
			return null;
		}
	}
	
	@Override
	public RelCertificationEmploye getRelationshCertificationEmploye(long id) {
		List<Long> param = Arrays.asList(id);
		try {
			return certification_employe.findByNamedQuery("select s from RelCertificationEmploye s where id=?1",param.toArray()).get(0);
		} catch (Exception err){
			err.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<CertificationState> getCertificationCompletedSession(RelCertificationEmploye r) {
		List<Long> param = Arrays.asList(r.getFk_employe().getId(), r.getFk_certification().getId());
		try {
			return certificationStateDao.findByNamedQuery(
					"select s from CertificationState s where s.fk_employe.id=?1 and s.fk_certification.id=?2",
					param.toArray());
		} catch (Exception err){
			err.printStackTrace();
			return null;
		}
	}

	@Override
	public void commitAnswer(CertificationState c) {
		List<CertificationState> res = certificationStateDao.findByNamedQuery("select s from CertificationState s where s.id=?1",Arrays.asList(c.getId()).toArray());
		if ( res != null && res.size()<1) {
			certificationStateDao.create(c);
		}
	}
	
	@Override
	public List<RelCertificationEmploye> findCompletedCertifications() {
		List<RelCertificationEmploye> r = certification_employe.findByNamedQuery("select s from RelCertificationEmploye s where s.complete=?1", Arrays.asList(true).toArray());
		try {
			r.get(0);
			return r;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
}