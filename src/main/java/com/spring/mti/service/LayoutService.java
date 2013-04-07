package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Employe;
import com.spring.mti.model.security.Authorities;

public interface LayoutService {

	List<Object> employeToMapJson(List<Employe> e);
	List<Object> authorityToMapJson(List<Authorities> e);

}
