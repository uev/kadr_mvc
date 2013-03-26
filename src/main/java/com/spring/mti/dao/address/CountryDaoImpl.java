package com.spring.mti.dao.address;
//import org.springframework.context.annotation.Bean;
import com.spring.mti.dao.GenericDaoImpl;
import com.spring.mti.model.address.Country;

public class CountryDaoImpl extends GenericDaoImpl<Country, Long> implements CountryDao  {
	/*
	@Bean
	public CountryDao countryDao(){
		return new CountryDaoImpl();
	}
	*/
}