package com.spring.mti.model;

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
	private long id;
    
    @NotNull
    private String name;

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
