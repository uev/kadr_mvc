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
	long id;
    
    @NotNull
    String name;

    /*
    @NotNull
    Integer age;
	
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
*/
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
}
