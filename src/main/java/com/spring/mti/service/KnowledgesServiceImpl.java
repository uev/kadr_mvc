package com.spring.mti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.AnswerDao;
import com.spring.mti.dao.QueshionDao;

@Repository
public class KnowledgesServiceImpl implements KnowledgesService {
	@Autowired private QueshionDao queshionDao;
	@Autowired private AnswerDao answerDao;

}
