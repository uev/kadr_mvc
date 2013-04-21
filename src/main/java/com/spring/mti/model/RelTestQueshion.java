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
	@JoinColumn(name="fk_answer")
	private Answer fk_answer;
	public TestKnowledge getFk_test() {
		return fk_test;
	}
	public void setFk_test(TestKnowledge fk_test) {
		this.fk_test = fk_test;
	}
	public Answer getFk_answer() {
		return fk_answer;
	}
	public void setFk_answer(Answer fk_answer) {
		this.fk_answer = fk_answer;
	}
	public long getId() {
		return id;
	}
}