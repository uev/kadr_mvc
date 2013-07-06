package com.spring.mti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.AnswerDao;
import com.spring.mti.dao.QueshionDao;
import com.spring.mti.model.Answer;
import com.spring.mti.model.Category;
import com.spring.mti.model.Queshion;

@Repository
public class KnowledgesServiceImpl implements KnowledgesService {
	@Autowired private QueshionDao queshionDao;
	@Autowired private AnswerDao answerDao;
	
	
	@Override
	public void createQueshion(String name) {
		if (this.getQueshionByName(name) == null) {
			Queshion q = new Queshion();
			q.setName(name);
			queshionDao.create(q);
		}
		/*
		List<String> ls = new ArrayList<String>();
		ls.add(name);
		List<Queshion>res = queshionDao.findByNamedQuery("select s from Queshion s where s.name=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Queshion q = new Queshion();
			q.setName(name);
			queshionDao.create(q);
		}
		*/
	}
	
	@Override
	public Queshion getQueshionByName(String name) {
		List<String> r = new ArrayList<String>();
		r.add(name);
		try{
			return queshionDao.findByNamedQuery("select s from Queshion s where s.name=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Queshion getQueshionById(Long id) {
		List<Long> r = new ArrayList<Long>();
		r.add(id);
		try{
			return queshionDao.findByNamedQuery("select s from Queshion s where s.id=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	
	
	@Override
	public void deleteQueshion(Queshion q) {
		try{
			queshionDao.delete(q);
		} catch(Exception e){
			//Removing answers
			List<Answer> answ = this.getAnswersByQueshion(q);
			for (Answer item : answ) {
				this.deleteAnswer(item);
			}
			queshionDao.delete(q);
		}
	}
	
	@Override
	public void updateQueshionRelation(Queshion q){
		queshionDao.update(q);
	}

	@Override
	public List<Queshion> getAllQueshions(){
		return queshionDao.findAll(new Queshion());
	}
	
	@Override
	public void createAnswer(String name) {
		if (this.getAnswerByContent(name) == null) {
			Answer a = new Answer();
			a.setContent(name);
			answerDao.create(a);
		}	
		/*
		List<String> ls = new ArrayList<String>();
		ls.add(name);
		List<Answer>res = answerDao.findByNamedQuery("select s from Answer s where s.content=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Answer q = new Answer();
			q.setContent(name);
			answerDao.create(q);
		}*/
	}
	
	@Override
	public Answer getAnswerById(Long id) {
		List<Long> r = new ArrayList<Long>();
		r.add(id);
		try{
			return answerDao.findByNamedQuery("select s from Answer s where s.id=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
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
	public List<Answer> getAnswersByQueshion(Queshion q) {
		List<Long> r = new ArrayList<Long>();
		r.add(q.getId());
		try{
			List<Answer> ans = answerDao.findByNamedQuery("select s from Answer s where s.fk_queshion.id=?1",r.toArray()); 
			ans.get(0);
			return ans;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public void deleteAnswer(Answer a) {
		answerDao.delete(a);
	}
	
	@Override
	public void deleteAnswersByQueshionId(Long id) {
		List<Answer> ans = answerDao.findByNamedQuery("select s from Answer s where s.fk_queshion.id=?1",Arrays.asList(id).toArray());
		for (Answer answer : ans) {
			answerDao.delete(answer);
		}
	}

	@Override
	public void updateAnswerRelation(Answer a){
		answerDao.update(a);
	}
	
	@Override
	public List<Queshion> getQueshionsFromCategory(long id){
		return queshionDao.findByNamedQuery("select s from Queshion s where s.fk_catgory.id=?1", Arrays.asList(id).toArray());
	}
	
	@Override
	public List<Queshion> getPageQueshionsFromCategory(int page, int size, Long category){
		return queshionDao.getPage(page, size, "select s from Queshion s where s.fk_catgory.id=".concat(category.toString()));
	}
}