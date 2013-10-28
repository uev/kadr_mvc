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
public class Question{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotNull
	private String name;
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="fk_category")
	private Category fk_catgory;
	private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getFk_catgory() {
		return fk_catgory;
	}
	public void setFk_catgory(Category fk_catgory) {
		this.fk_catgory = fk_catgory;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
        	Question q = (Question) obj;
        	if (q.id == this.id && this.content.equals(q.content) && this.name.equals(q.name) && this.fk_catgory.getId() == q.fk_catgory.getId()) 
        		return true;
        }
        return false;
    }
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		Long l = (Long)this.id; 
		return l.intValue()*112;
	}
}