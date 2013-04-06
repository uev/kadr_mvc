package com.spring.mti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.DapartmentDao;
import com.spring.mti.dao.CategoryDao;
import com.spring.mti.dao.EmployeDao;
import com.spring.mti.dao.RoleDao;
import com.spring.mti.dao.UsersDao;
import com.spring.mti.model.Category;
import com.spring.mti.model.Department;
import com.spring.mti.model.Employe;
import com.spring.mti.model.address.City;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
@Repository
@Service(value="serviceDictionary")
public class DictionaryServiceImpl implements DictionaryService {
	@Autowired
	//@Resource
	private AuthoritiesDao authDao;
	@Autowired private RoleDao rolesDao;
	@Autowired private EmployeDao employeDao;
	@Autowired private DapartmentDao departmentDao;
	@Autowired private CategoryDao categoryDao;
	@Autowired private UsersDao userDao;
	
	/*
	public void setAuthDao(AuthoritiesDaoImpl authDao) {
		this.authDao = authDao;
	}
	 */
/*
	@Override
	public void setPermissionsUser(Users user) {
		authDao.setPermissionUser(user);
	}
	@Override
	public void setPermissionsAdmin(Users user) {
		authDao.setPermissionAdmin(user);
	}
*/	
	

	@Override
	public List<String> getAllPermissionsByUserId(long user_id) {
		return authDao.getAllPermissionsBuUsername(user_id);
	}

	
	@Override
	public List<Object[]> getAllRoles() {
		return rolesDao.findAll_toArray(new Role());
	}
	
	@Override
	public Role getRoleByName(String role) {
		List<String> r = new ArrayList<String>();
		r.add(role);
		return rolesDao.findByNamedQuery("select s from Role s where s.rname=?1",r.toArray()).get(0); 	
	}
	@Override
	public void setPermissions(Users user, Role role) {
		// TODO Auto-generated method stub
		
	}
	

//===========================================================================================================================
	
	@Override
	public void createDepartment(String depname) {
		List<String> ls = new ArrayList<String>();
		ls.add(depname);
		List<Department>res = departmentDao.findByNamedQuery("select s from Department s where s.dep_name=?1",ls.toArray());
		if ( res != null && res.size()<1) {
			Department d = new Department();
			d.setDep_name(depname);
			departmentDao.create(d);
		}
	}
	
	@Override
	public Department getDepartmentByName(String dep) {
		List<String> r = new ArrayList<String>();
		r.add(dep);
		return departmentDao.findByNamedQuery("select s from Department s where s.dep_name=?1",r.toArray()).get(0); 	
	}

	@Override
	public void deleteDepartment(Department dep) {
		departmentDao.delete(dep);
	}
	
	@Override
	public void createCategory(String categoryname) {
		List<String> ls = new ArrayList<String>();
		ls.add(categoryname);
		List<Category> cat = categoryDao.findByNamedQuery("select s from Category s where s.cname=?1",ls.toArray());
		if ( cat != null && cat.size()<1) {
			Category c = new Category();
			c.setCname(categoryname);
			categoryDao.create(c);
		}
	}
	
	@Override
	public Category getCategoryByName(String cat) {
		List<String> r = new ArrayList<String>();
		r.add(cat);
		return categoryDao.findByNamedQuery("select s from Category s where s.cname=?1",r.toArray()).get(0); 	
	}

	
	@Override
	public void deleteCategory(Category cat) {
		categoryDao.delete(cat);
	}
	
	@Override
	public void createEmploye(String fio) {
		Employe e = new Employe();
		e.setFio(fio);
		employeDao.create(e);
	}
	
	@Override
	public List<Employe> getEmployeByName(String emp) {
		List<String> r = new ArrayList<String>();
		r.add(emp);
		return employeDao.findByNamedQuery("select s from Employe s where s.fio=?1",r.toArray()); 	
	}
	
	@Override
	public List<Employe> getEmployeAll() {
		return employeDao.findAll(new Employe()); 	
	}
	
	@Override
	public Employe getEmployeById(long id) {
		return employeDao.getByid(new Employe(), id); 	
	}

	@Override
	public void deleteEmploye(Employe e) {
		employeDao.delete(e);
	}

	@Override
	public void updateEmployeRelation(Employe e){
		employeDao.update(e);
	}
		
	@Override
	public void updateLoginRelation(Users u){
		userDao.update(u);
	}
}
