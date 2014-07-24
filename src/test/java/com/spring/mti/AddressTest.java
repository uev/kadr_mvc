package com.spring.mti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import com.spring.mti.model.address.City;
import com.spring.mti.model.address.Region;
import com.spring.mti.service.AddressService;

public class AddressTest extends AbstractTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testRelationsheeps(){
		City c = asrv.getCitiyByName("Барнаул");
		Region r = c.getFk_region();
		assertTrue("Алтайский край".equals(r.getName()));
	}
}