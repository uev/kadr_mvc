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

import com.sun.istack.internal.NotNull;

@Entity
public class Certification {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String name;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_test")
	private TestKnowledge fk_test;
	private String comment;
	private Date date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestKnowledge getFk_test() {
		return fk_test;
	}
	public void setFk_test(TestKnowledge fk_test) {
		this.fk_test = fk_test;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() == this.getClass()){
        	Certification c = (Certification) obj;
        	if (c.id == this.id && this.name.equals(c.name) && this.fk_test.getId() == c.getFk_test().getId()) 
        		return true;
        }
        return false;
    }
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		Long l = (Long)this.id; 
		return l.intValue()*1121;
	}
	
}