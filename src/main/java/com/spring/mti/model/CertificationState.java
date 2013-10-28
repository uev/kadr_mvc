package com.spring.mti.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="certification_state")
public class CertificationState {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_certification")
	private Certification fk_certification;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_employe")
	private Employe fk_employe;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_answer")
	private Answer fk_answer;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_Question")
	private Question fk_Question;
	private String alt_answer;
	private boolean valid;
	
	public long getId() {
		return id;
	}
	public Certification getFk_certification() {
		return fk_certification;
	}
	public void setFk_certification(Certification fk_certification) {
		this.fk_certification = fk_certification;
	}
	public Employe getFk_employe() {
		return fk_employe;
	}
	public void setFk_employe(Employe fk_employe) {
		this.fk_employe = fk_employe;
	}
	public Answer getFk_answer() {
		return fk_answer;
	}
	public void setFk_answer(Answer fk_answer) {
		this.fk_answer = fk_answer;
	}
	public Question getFk_Question() {
		return fk_Question;
	}
	public void setFk_Question(Question fk_Question) {
		this.fk_Question = fk_Question;
	}
	public String getAlt_answer() {
		return alt_answer;
	}
	public void setAlt_answer(String alt_answer) {
		this.alt_answer = alt_answer;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}