package com.bayrktlihn.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.bayrktlihn.entity.Person;

@Repository
public class PersonRepositoryImplTwo implements PersonRepository {

	private List<Person> persons = new ArrayList<Person>();
	private int count = 0;

	@Override
	public Person save(Person person) {

		if (person.getId() <= 0) {
			person.setId(++count);
			persons.add(person);
		} else {
			Optional<Person> findedPerson = persons.stream().filter(p -> p.getId() == person.getId()).findFirst();

			if (findedPerson.isPresent()) {
				Person findedPersonValue = findedPerson.get();

				persons.set(persons.indexOf(findedPersonValue), person);
			}
		}

		System.out.println(person);

		return person;
	}

	@Override
	public List<Person> findAll() {
		return persons;
	}

	@Override
	public Person findById(int id) {
		return persons.stream().filter(person -> person.getId() == id).collect(Collectors.toList()).get(0);
	}

	@Override
	public void deleteById(int id) {
		Optional<Person> findedPerson = persons.stream().filter(p -> p.getId() == id).findFirst();

		if (findedPerson.isPresent()) {
			Person findedPersonValue = findedPerson.get();

			persons.remove(persons.indexOf(findedPersonValue));
		}

	}

}
