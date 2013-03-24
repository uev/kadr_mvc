package com.spring.mti;

import java.lang.reflect.Method;

import org.junit.Test;

public class ReflexionTest {

	@Test
	public void testReflexion(){
		Method[] met = this.getClass().getDeclaredMethods();
	    for (Method i : met){
	    	System.out.println(i.getName().matches("^fix.*"));
	    	if (i.getName().matches("^fix")) {
	    		System.out.println("OK");
	    	}
	    }
	    try {
			Method method = this.getClass().getMethod("fixture");
			method.invoke(this, null);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	}

	
	public void fixture(){
		System.out.println("Reflected");
	}
}
