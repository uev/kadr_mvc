package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.model.security.Authorities;
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


	
	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}
	
	@Override
	@Transactional
	public boolean isUserRoleSet(Users user){
		List query_userrules =  this.entityManager.createQuery("select s.id from Authorities s where s.user.id = :user_id and s.authority = :role_id ").setParameter("user_id", user.getId()).setParameter("role_id", new Authorities().getUser_role()).getResultList();
		if (query_userrules.size()>0){ 
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isAdminRoleSet(Users user) {
		List query_userrules =  this.entityManager.createQuery("select s.id from Authorities s where s.user.id = :user_id and s.authority = :role_id ").setParameter("user_id", user.getId()).setParameter("role_id", new Authorities().getAdmin_arole()).getResultList();
		if (query_userrules.size()>0){ 
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public List<String> getAllPermissionsBuUsername(long user_id) {
		// TODO Auto-generated method stub
		return this.entityManager.createQuery("select s.authority from Authorities s where s.user.id = :user_id").setParameter("user_id", user_id).getResultList();
	}
}
