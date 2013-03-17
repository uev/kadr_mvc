package com.spring.mti.dao;

import com.spring.mti.security.Authorities;
import com.spring.mti.security.Users;

public interface AuthoritiesDao {
	public String getAuthority();
	public void setPermissionUser(Authorities auser);
}
