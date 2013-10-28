package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Question;

public interface KnowledgesService {
	Question getQuestionByName(String name);
	void updateQuestionRelation(Question q);
	void deleteQuestion(Question q);
	void createQuestion(String name);
	void createAnswer(String name);
	Answer getAnswerByContent(String name);
	void deleteAnswer(Answer a);
	void updateAnswerRelation(Answer a);
	List<Question> getAllQuestions();
	List<Answer> getAnswersByQuestion(Question q);
	Question getQuestionById(Long id);
	void deleteAnswersByQuestionId(Long id);
	Answer getAnswerById(Long id);
	List<Question> getQuestionsFromCategory(long id);
	List<Question> getPageQuestionsFromCategory(int page, int size,
			Long category);
	List<Question> getPageQuestionsAll(int page, int size);
}
