package com.spring.mti.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.mti.domain.Person;

public interface PersonRep extends CrudRepository<Person, Long>{

}
