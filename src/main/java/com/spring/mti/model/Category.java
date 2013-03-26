package com.spring.mti.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

@Entity
public class Category implements Serializable {
		@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@SuppressWarnings("restriction")
	@NotNull
	private String cname;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public long getId() {
		return id;
	}
}
