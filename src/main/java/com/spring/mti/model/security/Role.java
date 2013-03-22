package com.spring.mti.model.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@SuppressWarnings("restriction")
	@NotNull
	private String rname;

	public long getId() {
		return id;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}	
}
