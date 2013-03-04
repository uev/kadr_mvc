package com.spring.mti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.spring.mti.domain.Person;
import com.spring.mti.repository.PersonRep;
@Service("servicePerson")
@Repository
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRep repo; 
	
	public long countAllPersons() {
		// TODO Auto-generated method stub
		return repo.count();
	}
	
	public void deletePersonId(Integer id){
		repo.delete(new Long(id));
	}
	
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		repo.delete(person);
		
	}

	public Person findPerson(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Person> findAlPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		repo.save(person);
	}

	public Person updateEmployee(Person employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findAllPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPerson(String name) {
		// TODO Auto-generated method stub
		return repo.findByName(name).get(0);
	}
}
