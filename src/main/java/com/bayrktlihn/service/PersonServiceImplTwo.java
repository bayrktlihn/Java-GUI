package com.bayrktlihn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bayrktlihn.entity.Person;
import com.bayrktlihn.repository.PersonRepository;

@Service
public class PersonServiceImplTwo implements PersonService {

	@Autowired
	@Qualifier("personRepositoryImplTwo")
	private PersonRepository personRepository;

	@Override
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(int id) {
		return personRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		personRepository.deleteById(id);
	}

}
