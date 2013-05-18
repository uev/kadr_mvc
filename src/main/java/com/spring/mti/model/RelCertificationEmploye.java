package com.spring.mti.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="certification_employe")
public class RelCertificationEmploye {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_employe")
	private Employe fk_employe;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_certification")
	private Certification fk_certification;
	private boolean complete = false;
	
	public long getId() {
		return id;
	}
	
	public Employe getFk_employe() {
		return fk_employe;
	}
	
	public void setFk_employe(Employe fk_employe) {
		this.fk_employe = fk_employe;
	}
	public Certification getFk_certification() {
		return fk_certification;
	}
	
	public void setFk_certification(Certification fk_certification) {
		this.fk_certification = fk_certification;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
}