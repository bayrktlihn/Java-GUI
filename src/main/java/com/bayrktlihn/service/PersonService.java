package com.bayrktlihn.service;

import java.util.List;

import com.bayrktlihn.entity.Person;

public interface PersonService {
	Person save(Person person);

	List<Person> findAll();

	Person findById(int id);
	
	void deleteById(int id);
}
