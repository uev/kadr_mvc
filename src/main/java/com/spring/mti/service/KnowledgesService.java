package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Answer;
import com.spring.mti.model.Queshion;

public interface KnowledgesService {
	Queshion getQueshionByName(String name);
	void updateQueshionRelation(Queshion q);
	void deleteQueshion(Queshion q);
	void createQueshion(String name);
	void createAnswer(String name);
	Answer getAnswerByContent(String name);
	void deleteAnswer(Answer a);
	void updateAnswerRelation(Answer a);
	List<Queshion> getAllQueshions();
	List<Answer> getAnswersByQueshion(Queshion q);
	Queshion getQueshionById(Long id);
	void deleteAnswersByQueshionId(Long id);
	Answer getAnswerById(Long id);
	List<Queshion> getQueshionsFromCategory(long id);
	List<Queshion> getPageQueshionsFromCategory(int page, int size,
			Long category);
	List<Queshion> getPageQueshionsAll(int page, int size);
}
