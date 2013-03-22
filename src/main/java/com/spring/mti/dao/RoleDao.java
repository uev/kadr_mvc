package com.spring.mti.dao;

import java.util.List;

import com.spring.mti.model.security.Role;

public interface RoleDao {
	void createRole(Role role);
	List<Object[]> getAllRoles();
	void deleteRole(Role role);
}
