package com.spring.mti.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.mti.dao.address.CityDao;
import com.spring.mti.dao.address.CountryDao;
import com.spring.mti.dao.address.RegionDao;
import com.spring.mti.model.address.City;
import com.spring.mti.model.address.Country;
import com.spring.mti.model.address.Region;

@Repository
public class AddressServiceImpl implements AddressService {
	//@Autowired 
	@Autowired private RegionDao regionDao;
	@Autowired private CityDao cityDao;
	@Autowired private CountryDao countryDao;
	
	@Override
	public List<Country> getCountries() {
		//return countryDao.findAll_toArray(new Country());
		return countryDao.findAll(new Country());
	}

	@Override
	public List<Region> getRegions(long countryid) {
		List<Long> param = new ArrayList<Long>();
		param.add((Long)countryid);
		return regionDao.findByNamedQuery("select s from Region s where s.countryid=?1",param.toArray());
		//System.out.println("regions getted");
		//return regionDao.listToArray(regionDao.findByNamedQuery("select s from Region s where s.countryid=?1",param.toArray()));
	}

	@Override
	public List<City> getCities(long regionid) {
		List<Object> param = new ArrayList<Object>();
		param.add(regionid);
		return cityDao.findByNamedQuery("select s from City s where s.regionid=?1",param.toArray());
		//return cityDao.listToArray(cityDao.findByNamedQuery("select s from City s where s.regionid=?1",param.toArray()));
	}
}
