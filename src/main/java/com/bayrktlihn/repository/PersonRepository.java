package com.bayrktlihn.repository;

import java.util.List;

import com.bayrktlihn.entity.Person;

public interface PersonRepository {
	Person save(Person person);

	List<Person> findAll();
	
	Person findById(int id);
	
	void deleteById(int id);
}
