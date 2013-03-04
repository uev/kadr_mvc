package com.spring.mti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	long id;
    
    @NotNull
    String name;
	
	public void setName(String name){
		this.name = name;
	}
}
