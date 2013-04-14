package com.spring.mti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.AnswerDao;
import com.spring.mti.dao.QueshionDao;
import com.spring.mti.model.Answer;
import com.spring.mti.model.Queshion;

@Repository
public class KnowledgesServiceImpl implements KnowledgesService {
	@Autowired private QueshionDao queshionDao;
	@Autowired private AnswerDao answerDao;
	
	
	@Override
	public void createQueshion(String name) {
		List<String> ls = new ArrayList<String>();
		ls.add(name);
		List<Queshion>res = queshionDao.findByNamedQuery("select s from Queshion s where s.name=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Queshion q = new Queshion();
			q.setName(name);
			queshionDao.create(q);
		}
	}
	
	@Override
	public Queshion getQueshionByName(String name) {
		List<String> r = new ArrayList<String>();
		r.add(name);
		try{
			return queshionDao.findByNamedQuery("select s from Queshion s where s.name=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public void deleteQueshion(Queshion q) {
		queshionDao.delete(q);
	}
	
	@Override
	public void updateQueshionRelation(Queshion q){
		queshionDao.update(q);
	}

	@Override
	public void createAnswer(String name) {
		List<String> ls = new ArrayList<String>();
		ls.add(name);
		List<Answer>res = answerDao.findByNamedQuery("select s from Answer s where s.content=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Answer q = new Answer();
			q.setContent(name);
			answerDao.create(q);
		}
	}
	
	@Override
	public Answer getAnswerByContent(String name) {
		List<String> r = new ArrayList<String>();
		r.add(name);
		try{
			return answerDao.findByNamedQuery("select s from Answer s where s.content=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	@Override
	public void deleteAnswer(Answer a) {
		answerDao.delete(a);
	}
	
	@Override
	public void updateAnswerRelation(Answer a){
		answerDao.update(a);
	}
}
