package com.bayrktlihn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bayrktlihn.entity.Person;
import com.bayrktlihn.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	@Qualifier("personRepositoryImpl")
	private PersonRepository personRepository;

	@Override
	@Transactional
	public Person save(Person person) {
		return personRepository.save(person);
	}

	@Override
	@Transactional
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	@Transactional
	public Person findById(int id) {
		return personRepository.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		personRepository.deleteById(id);
	}

}
