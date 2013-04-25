package com.spring.mti.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.sun.istack.internal.NotNull;

public class CertificationState {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_certification")
	private Certification fk_certification;
	@JoinColumn(name="fk_employe")
	private Employe fk_employe;
	@JoinColumn(name="fk_answer")
	private Answer fk_answer;
	@JoinColumn(name="fk_queshion")
	private Queshion fk_queshion;
	private String alt_answer;
}