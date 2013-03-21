package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.security.Users;

public interface AuthorityService {
	public void setPermissionsUser(Users user);
	public void setPermissionsAdmin(Users user);
	public List<String> getAllPermissionsByUserId(long user_id);
}
