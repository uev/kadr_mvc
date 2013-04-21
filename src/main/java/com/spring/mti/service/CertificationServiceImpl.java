package com.spring.mti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.RelTestQueshionDao;
import com.spring.mti.dao.TestKnowledgeDao;

@Repository
public class CertificationServiceImpl implements CertificationService {
	@Autowired private TestKnowledgeDao testknowledge;
	@Autowired private RelTestQueshionDao test_queshion;
}
