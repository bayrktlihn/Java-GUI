package com.bayrktlihn.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bayrktlihn.entity.Person;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Person save(Person person) {
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(person);

		return person;
	}

	@Override
	public List<Person> findAll() {
		Session currentSession = sessionFactory.getCurrentSession();

		List<Person> persons = currentSession.createQuery("from Person", Person.class).list();

		return persons;
	}

	@Override
	public Person findById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Person person = currentSession.get(Person.class, id);

		return person;
	}

	@Override
	public void deleteById(int id) {
		Session currentSession = sessionFactory.getCurrentSession();

		Person person = new Person();
		person.setId(id);
		
		currentSession.remove(person);
	}

}
