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
@Table(name="test_queshion")
public class RelTestQueshion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_test")
	private TestKnowledge fk_test;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_queshion")
	private Queshion fk_queshion;
	public TestKnowledge getFk_test() {
		return fk_test;
	}
	public void setFk_test(TestKnowledge fk_test) {
		this.fk_test = fk_test;
	}
	public Queshion getFk_queshion() {
		return fk_queshion;
	}
	public void setFk_queshion(Queshion fk_queshion) {
		this.fk_queshion = fk_queshion;
	}
	public long getId() {
		return id;
	}
}