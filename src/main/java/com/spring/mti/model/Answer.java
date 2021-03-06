package com.spring.mti.model;
import com.sun.istack.internal.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Answer {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String content;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_Question")
	private Question fk_Question;
	private boolean valid;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Question getFk_Question() {
		return fk_Question;
	}
	public void setFk_Question(Question fk_Question) {
		this.fk_Question = fk_Question;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public long getId() {
		return id;
	}
}