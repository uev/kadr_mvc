package com.spring.mti.service;

import java.util.List;

import com.spring.mti.model.address.City;
import com.spring.mti.model.address.Country;
import com.spring.mti.model.address.Region;

public interface AddressService {
	public List<Country> getCountries();
	public List<Region> getRegions(long countryid);
	public List<City> getCities(long regionid);
}