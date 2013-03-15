package com.spring.mti.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Authorities {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private Users user;
	
	private String authority;
	
	public String getAuthority() {
	    return authority;
	}
	public void setAuthority(String authority) {
	    this.authority = authority;
	}
	/*
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	*/
	
}
