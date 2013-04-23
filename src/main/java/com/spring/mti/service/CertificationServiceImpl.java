package com.spring.mti.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.dao.QueshionDao;
import com.spring.mti.dao.RelTestQueshionDao;
import com.spring.mti.dao.TestKnowledgeDao;
import com.spring.mti.model.Queshion;
import com.spring.mti.model.RelTestQueshion;
import com.spring.mti.model.TestKnowledge;

@Repository
public class CertificationServiceImpl implements CertificationService {
	@Autowired private TestKnowledgeDao testDao;
	@Autowired private RelTestQueshionDao test_queshionDao;
	@Autowired private QueshionDao queshionDao;
	
	
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
}