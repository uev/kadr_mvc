package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Employe;

public interface LayoutService {

	List<Object> employeToMapJson(List<Employe> e);

}
