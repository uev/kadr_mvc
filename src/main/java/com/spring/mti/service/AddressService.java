package com.spring.mti.service;

import java.util.List;

public interface AddressService {
	public List<Object[]> getCountries();
	public List<Object[]> getRegions(long countryid);
	public List<Object[]> getCities(long regionid);
}