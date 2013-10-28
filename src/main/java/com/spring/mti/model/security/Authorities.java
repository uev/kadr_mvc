package com.spring.mti.model.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.spring.mti.model.Question;

@Entity
public class Authorities {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="username")
	private Users fk_user;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER)
	@JoinColumn(name="rname")
	private Role fk_role;
	@Transient
	private String authority;
	
	@Transient
	private static String user_role="ROLE_USER";
	@Transient
	private static String admin_arole="ROLE_ADMIN";
	
	public long getId() {
		return id;
	}	
	
	public String getAuthority() {
	    return authority;
	}
	
	public void setAuthorityUser(Users user) {
		setUser(user);
		//setAuthority(this.user_role);
	}
	
	public void setAuthorityAdmin(Users user) {
		setUser(user);
		//setAuthority(this.admin_arole);
	}
	
	public void setAuthority(Users user, Role role) {
	    //this.authority = authority;
		setUser(user);
		setRole(role);
		
	}

	public Role getRole() {
		return fk_role;
	}

	public void setRole(Role role) {
		this.fk_role = role;
	}
	
	public Users getUser() {
		return fk_user;
	}

	public void setUser(Users user) {
		this.fk_user = user;
	}

	public static String getUser_role() {
		return user_role;
	}

	public static String getAdmin_arole() {
		return admin_arole;
	}

	public Users getFk_user() {
		return fk_user;
	}

	public void setFk_user(Users fk_user) {
		this.fk_user = fk_user;
	}

	public Role getFk_role() {
		return fk_role;
	}

	public void setFk_role(Role fk_role) {
		this.fk_role = fk_role;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() == this.getClass()){
        	Authorities a = (Authorities) obj;
        	if (a.id == this.id && this.fk_user.getId() == a.fk_user.getId() && this.fk_role.getId() == a.fk_role.getId()) 
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