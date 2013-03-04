package com.spring.mti.service;

import java.util.List;

import com.spring.mti.domain.Person;

public interface PersonService {
    public long countAllPersons();

    public void deletePerson(final Person person);
    
    public void deletePersonId(final Integer id);

    public Person findPerson(final Long id);

    public List<Person> findAllPerson();
    
    public Person findPerson(final String name);
    
    //public List<Person> findEmployeeEntries(final int firstResult, final int maxResults);

    public void savePerson(final Person person);

    public Person updateEmployee(final Person employee);

}
