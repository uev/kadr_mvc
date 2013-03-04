package com.spring.mti.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.spring.mti.domain.Person;

public interface  PersonRep extends CrudRepository<Person, Long>,Repository<Person, Long>{
	public List<Person> findByName(String name);
}
