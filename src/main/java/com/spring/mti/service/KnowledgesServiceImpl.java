package com.spring.mti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.AnswerDao;
import com.spring.mti.dao.QuestionDao;
import com.spring.mti.model.Answer;
import com.spring.mti.model.Category;
import com.spring.mti.model.Question;

@Repository
public class KnowledgesServiceImpl implements KnowledgesService {
	@Autowired private QuestionDao questionDao;
	@Autowired private AnswerDao answerDao;
	
	
	@Override
	public void createQuestion(String name) {
		if (this.getQuestionByName(name) == null) {
			Question q = new Question();
			q.setName(name);
			questionDao.create(q);
		}
		/*
		List<String> ls = new ArrayList<String>();
		ls.add(name);
		List<Question>res = questionDao.findByNamedQuery("select s from Question s where s.name=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Question q = new Question();
			q.setName(name);
			questionDao.create(q);
		}
		*/
	}
	
	@Override
	public Question getQuestionByName(String name) {
		List<String> r = new ArrayList<String>();
		r.add(name);
		try{
			return questionDao.findByNamedQuery("select s from Question s where s.name=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Question getQuestionById(Long id) {
		List<Long> r = new ArrayList<Long>();
		r.add(id);
		try{
			return questionDao.findByNamedQuery("select s from Question s where s.id=?1",r.toArray()).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	
	
	@Override
	public void deleteQuestion(Question q) {
		try{
			questionDao.delete(q);
		} catch(Exception e){
			//Removing answers
			List<Answer> answ = this.getAnswersByQuestion(q);
			for (Answer item : answ) {
				this.deleteAnswer(item);
			}
			questionDao.delete(q);
		}
	}
	
	@Override
	public void updateQuestionRelation(Question q){
		questionDao.update(q);
	}

	@Override
	public List<Question> getAllQuestions(){
		return questionDao.findAll(new Question());
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
	public List<Answer> getAnswersByQuestion(Question q) {
		List<Long> r = new ArrayList<Long>();
		r.add(q.getId());
		try{
			List<Answer> ans = answerDao.findByNamedQuery("select s from Answer s where s.fk_Question.id=?1",r.toArray()); 
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
	public void deleteAnswersByQuestionId(Long id) {
		List<Answer> ans = answerDao.findByNamedQuery("select s from Answer s where s.fk_Question.id=?1",Arrays.asList(id).toArray());
		for (Answer answer : ans) {
			answerDao.delete(answer);
		}
	}

	@Override
	public void updateAnswerRelation(Answer a){
		answerDao.update(a);
	}
	
	@Override
	public List<Question> getQuestionsFromCategory(long id){
		return questionDao.findByNamedQuery("select s from Question s where s.fk_catgory.id=?1", Arrays.asList(id).toArray());
	}
	
	@Override
	public List<Question> getPageQuestionsFromCategory(int page, int size, Long category){
		return questionDao.getPage(page, size, "select s from Question s where s.fk_catgory.id=".concat(category.toString()));
	}
	
	@Override
	public List<Question> getPageQuestionsAll(int page, int size){
		return questionDao.getPage(page, size, "select s from Question s ");
	}
}