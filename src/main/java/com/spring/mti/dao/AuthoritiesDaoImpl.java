package com.spring.mti.dao;

import com.spring.mti.security.Authorities;

public class AuthoritiesDaoImpl implements AuthoritiesDao {
	private Authorities dao;
	
	@Override
	public String getAuthority() {
		return dao.getAuthority();
	}

	@Override
	public void setAuthority(String authority) {
		dao.setAuthority(authority);
	}

}
