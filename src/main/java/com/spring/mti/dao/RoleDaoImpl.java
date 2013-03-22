package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;

public class RoleDaoImpl implements RoleDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public void createRole(Role role) {
		List r = this.entityManager.createQuery("select rname from Role s where rname = :role").setParameter("role", role.getRname()).getResultList();
		if (r.size() == 0) {
			this.entityManager.persist(role);
		}
	}

	@Override
	@Transactional
	public List<Object[]> getAllRoles() {
		return this.entityManager.createQuery("select id,rname from Role s").getResultList();
	}

	@Override
	@Transactional
	public void deleteRole(Role role) {
		this.entityManager.createQuery("delete from Role s where id = :role_id").setParameter("role_id", role.getId()).executeUpdate();
		this.entityManager.flush();
	}
	
	@Override
	@Transactional
	public Role getRoleByName(String role) {
		try {
			return (Role)this.entityManager.createQuery("select s from Role s where rname = :role").setParameter("role", role).getResultList().get(0);
		}
		catch (Exception indexOutOfBoundsException) {
			return new Role();
		}
	}
}