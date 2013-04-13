package com.spring.mti.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.spring.mti.model.address.City;
import com.sun.istack.internal.NotNull;

@Entity
public class Employe {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String fio;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_city")
	private City fk_city;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_department")
	private Department fk_department;
	
	public long getId() {
		return id;
	}

	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public City getFk_city() {
		return fk_city;
	}
	public void setFk_city(City fk_city) {
		this.fk_city = fk_city;
	}

	public Department getFk_department() {
		return fk_department;
	}

	public void setFk_department(Department fk_department) {
		this.fk_department = fk_department;
	}
}
