package com.spring.mti.security;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Authorities {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="username")
	private Users user;
	private String authority;
	
	@Transient
	private static String user_role="ROLE_USER";
	@Transient
	private static String admin_arole="ROLE_ADMIN";
	
	public String getAuthority() {
	    return authority;
	}
	
	public void setAuthorityUser(Users user) {
		setUser(user);
		setAuthority(this.user_role);
	}
	
	public void setAuthorityAdmin(Users user) {
	    this.user = user;
	}
	
	public void setAuthority(String authority) {
	    this.authority = authority;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public static String getUser_role() {
		return user_role;
	}

	public static String getAdmin_arole() {
		return admin_arole;
	}
	
}
