package com.spring.mti.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.model.security.Authorities;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;

@Repository("authDAO")
public class AuthoritiesDaoImpl implements AuthoritiesDao {
	private Users user;
	
	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
		
	
	@Override
	public String getAuthority() {
		return "";
	}

/*
	@Override
	@Transactional
	public void setPermissionUser(Users user){
		Authorities a = new Authorities();
		a.setAuthorityUser(user);
		this.entityManager.persist(a);
	}
	
	@Transactional
	@Override
	public void setPermissionAdmin(Users user) {
		Authorities a = new Authorities();
		a.setAuthorityAdmin(user);
		this.entityManager.persist(a);
	}
*/

	@Transactional
	@Override
	public void setPermission(Users user, Role role) {
		Authorities a = new Authorities();
		a.setAuthority(user,role);
		this.entityManager.persist(a);
	}
	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	
	private boolean payloadRoleSet(Users user, String rolename){
		List<Object> query_userrules =  this.entityManager.createQuery("select m.rname from Authorities s, Role m where s.user.id = :user_id and s.role.id = m.id ").setParameter("user_id", user.getId()).getResultList();
		try {
			if (rolename.equals((String)query_userrules.get(0)) == true) {
				return true;
			}
		} catch(Exception e) {
			return false;
		}
		return false; 		
	}
	
	@Override
	@Transactional
	public boolean isUserRoleSet(Users user){
		return payloadRoleSet(user, "ROLE_USER");
	}
	
	@Override
	@Transactional
	public boolean isAdminRoleSet(Users user){
		return payloadRoleSet(user, "ROLE_ADMIN"); 
	}
	/*
	@Override
	public boolean isAdminRoleSet(Users user) {
		List query_userrules =  this.entityManager.createQuery("select s.id from Authorities s where s.user.id = :user_id and s.authority = :role_id ").setParameter("user_id", user.getId()).setParameter("role_id", new Authorities().getAdmin_arole()).getResultList();
		if (query_userrules.size()>0){ 
			return true;
		}
		return false;
	}
	*/

	@Override
	@Transactional
	public List<String> getAllPermissionsBuUsername(long user_id) {
		// TODO Auto-generated method stub
		return this.entityManager.createQuery("select m.rname from Authorities s, Role m where s.user.id = :user_id").setParameter("user_id", user_id).getResultList();
	}

	@Override
	@Transactional
	//"select m.rname from Authorities s, Role m where s.user.id = :user_id and s.role.id = m.id ").setParameter("user_id", user.getId())
	public HashMap<String,List> getAllUsersPermissions() {
		List<Object[]> res = this.entityManager.createQuery("select u.username, m.rname from Authorities s, Users u, Role m where s.user.id = u.id").getResultList();
		HashMap<String,List> map = new HashMap<String, List>();
		for (Object[] result : res) {
		      	List<String> t = new ArrayList<String>();
				t.add((String)result[1]);
		      	map.put((String)result[0], t);
		  }
		return map;
	}
}
