package com.spring.mti.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	long id;
    
    @SuppressWarnings("restriction")
	@NotNull
    String username;
    String password;
    Integer enabled;
    
	public void setUsermame(String name){
		this.username = name;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
}
