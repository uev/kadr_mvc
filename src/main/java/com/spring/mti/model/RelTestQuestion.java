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
@Table(name="test_question")
public class RelTestQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_test")
	private TestKnowledge fk_test;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_Question")
	private Question fk_Question;
	public TestKnowledge getFk_test() {
		return fk_test;
	}
	public void setFk_test(TestKnowledge fk_test) {
		this.fk_test = fk_test;
	}
	public Question getFk_Question() {
		return fk_Question;
	}
	public void setFk_Question(Question fk_Question) {
		this.fk_Question = fk_Question;
	}
	public long getId() {
		return id;
	}
}