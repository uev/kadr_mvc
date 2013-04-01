package com.spring.mti.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

@Entity
public class Department implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@SuppressWarnings("restriction")
	@NotNull
	private String dep_name;

	public long getId() {
		return id;
	}

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	
	
}
