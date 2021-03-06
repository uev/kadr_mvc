package com.spring.mti;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.spring.mti.model.Category;
import com.spring.mti.model.security.Role;
import com.spring.mti.model.security.Users;
import com.spring.mti.service.AuthorityService;
import com.spring.mti.service.CustomUserDetailsService;

public class AuthUsersTest extends AbstractTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testUsersDAOCreateUser() {
		Users user = new Users();
		user.setUsermame("testic");
		user.setPassword("testic");
		user.setEnabled(1);
		dao.setSalt(user);
		dao.createUser(user);
		assertEquals(dao.getUserByLoginName("testic").getUsername(), "testic");
	}
	
	@Test
	public void testSetPermissionsUser(){
		Users user = dao.getUserByLoginName("testic");
		CustomUserDetailsService authStorage = (CustomUserDetailsService)context.getBean("userDetailsService");
//		AuthorityService aservice = (AuthorityService) context.getBean("serviceAuth");
		if (!authStorage.isUserRoleSet(user.getUsername())){
			Role role = sauth.getRoleByName("ROLE_USER");
			sauth.setPermissions(user,role);
			System.out.println("Add user permission");
		}
		assertTrue(authStorage.isUserRoleSet(user.getUsername()));
	}
	
	@Test
	public void testAuthManager(){
		Authentication arequest = new UsernamePasswordAuthenticationToken("testic", "testic");
        AuthenticationManager am = (AuthenticationManager) context.getBean("authenticationManager");
        am.authenticate(arequest);
	}
	
	@Test
	public void testUsersDAORemoveUser() {
		Users user = dao.getUserByLoginName("testic");
		try {
			user.getUsername().equals(null);
			dao.deleteUser(user);
		} catch(NullPointerException exception) {
			System.out.println("User is epsent");
		}
	}
	
	@Test
	public void testAllPermissionUsers() {
		dao.getAllUsersPermissions();
	}
	
	
	@Test
	public void testGetAllRoles(){
	    List<Role> m = sauth.getAllRoles();
	    assertTrue(m.size()>0);
	}

	@Test
	@Ignore
	public void testAddCategory(){
	    Category c = new Category();
	    c.setCname("Testic");
	    dao.appendCategory(c);
	}	
}