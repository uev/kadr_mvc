package com.spring.mti.service;

import java.util.List;
import java.util.Map;

import com.spring.mti.model.Answer;
import com.spring.mti.model.CertificationState;
import com.spring.mti.model.Employe;
import com.spring.mti.model.security.Authorities;

public interface LayoutService {

	List<Object> employeToMapJson(List<Employe> e);
	List<Object> authorityToMapJson(List<Authorities> e);
	Map<String, Object> queshionProfileToMapJson(List<Answer> a);
	List<Object> decorateAnswersOnQueshions(List<CertificationState> c);
	List<Integer> generateNaviPagination(int count, int step, int size,
			int current);
}
