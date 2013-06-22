package com.spring.mti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.spring.mti.dao.AuthoritiesDao;
import com.spring.mti.dao.RoleDao;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
import com.spring.mti.model.security.Authorities;
@Repository
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	//@Resource
	private AuthoritiesDao authDao;
	@Autowired private RoleDao rolesDao;
	
	
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
	public void setPermissions(Users user, Role role) {
		authDao.setPermission(user,role);
	}

	@Override
	public List<String> getAllPermissionsByUserId(long user_id) {
		return authDao.getAllPermissionsBuUsername(user_id);
	}
	
	@Override
	public List<Authorities> getAllPermissions() {
		return authDao.findAll(new Authorities());
	}
	
	@Override
	public void createRole(Role role) {
		rolesDao.create(role);
	}
	
	@Override
	public void deleteRole(Role role) {
		rolesDao.delete(role);
	}
	
	@Override
	public List<Role> getAllRoles() {
		return rolesDao.findAll(new Role());
	}
	
	@Override
	public Role getRoleByName(String role) {
		List<String> r = new ArrayList<String>();
		r.add(role);
		return rolesDao.findByNamedQuery("select s from Role s where s.rname=?1",r.toArray()).get(0); 	
	}
	
	@Override
	public Role getRoleByContent(String role) {
		return rolesDao.findByNamedQuery("select s from Role s where s.content=?1",Arrays.asList(role).toArray()).get(0); 	
	}
}