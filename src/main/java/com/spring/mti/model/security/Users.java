package com.spring.mti.model.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.mti.model.Employe;
import com.sun.istack.internal.NotNull;

@Entity
public class Users {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@SuppressWarnings("restriction")
	@NotNull
    private String username;
	private String password;
	private Integer enabled;
    
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fio")
	private Employe fk_employe;
	
	public long getId() {
		return id;
	}	
	
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

	public Employe getFk_employe() {
		return fk_employe;
	}

	public void setFk_employe(Employe fk_employe) {
		this.fk_employe = fk_employe;
	}
}
