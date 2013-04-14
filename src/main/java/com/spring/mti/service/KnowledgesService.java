package com.spring.mti.service;

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
}
