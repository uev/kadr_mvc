package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.Category;
import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;

public interface DictionaryService {
//	public void setPermissionsUser(Users user);
//	public void setPermissionsAdmin(Users user);
	public List<String> getAllPermissionsByUserId(long user_id);
	public List<Object[]> getAllRoles();
	void setPermissions(Users user, Role role);
	public Role getRoleByName(String role);
	public void createDepartment(String depname);
	public void deleteDepartment(Department dep);
	public void createCategory(String categoryname);
	public void deleteCategory(Category cat);
	//public void createRole(Role role);
	//public void deleteRole(Role role);
	public void createEmploye(String fio);
	public void deleteEmploye(Employe e);
	public Category getCategoryByName(String cat);
	public List<Employe> getEmployeByName(String emp);
	public Department getDepartmentByName(String dep);
	void updateEmployeRelation(Employe e);
	void updateLoginRelation(Users u);
	List<Employe> getEmployeAll();
	Employe getEmployeById(long id);
	List<Department> getAllDepartments();
	List<Category> getAllCategories();
	List<Employe> getEmployersByDepartment(Department d);
}
