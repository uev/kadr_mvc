package com.spring.mti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;

public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

}