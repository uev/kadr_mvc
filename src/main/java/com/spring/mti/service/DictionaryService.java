package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Category;
import com.spring.mti.model.Department;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;

public interface DictionaryService {
//	public void setPermissionsUser(Users user);
//	public void setPermissionsAdmin(Users user);
	public List<String> getAllPermissionsByUserId(long user_id);
	public List<Object[]> getAllRoles();
	void setPermissions(Users user, Role role);
	public Role getRoleByName(String role);
	public void createDepartment(Department dep);
	public void deleteDepartment(Department dep);
	public void createCategory(Category cat);
	public void deleteCategory(Category cat);
	public void createRole(Role role);
	public void deleteRole(Role role);

	
	
	
	
	
	
	
	
	
	
	
}
