package com.bayrktlihn.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.bayrktlihn.FormEvent;
import com.bayrktlihn.Utils;
import com.bayrktlihn.entity.AgeCategory;
import com.bayrktlihn.entity.EmploymentCategory;
import com.bayrktlihn.entity.Gender;
import com.bayrktlihn.entity.Person;
import com.bayrktlihn.service.PersonService;

@Controller
public class MainController {

	@Autowired
	@Qualifier("personServiceImpl")
	private PersonService personService;

	public Person savePerson(FormEvent event) {

		String employmentCategoryString = Utils.replaceAndUpperCase(event.getEmploymentCategory(), ' ', '_');

		String genderString = event.getGender().toUpperCase();

		String name = event.getName();
		String occupation = event.getOccupation();
		AgeCategory ageCategory = AgeCategory.values()[event.getAgeCategoryId()];
		EmploymentCategory employmentCategory = EmploymentCategory.valueOf(employmentCategoryString);
		boolean usCitizen = event.isUsCitizen();
		String taxId = event.getTaxId();
		Gender gender = Gender.valueOf(genderString);

		Person person = new Person(name, occupation, ageCategory, employmentCategory, usCitizen, taxId, gender);

		personService.save(person);

		return person;
	}

	public Person savePerson(Person person) {
		
		personService.save(person);
		
		return null;
	}

	public List<Person> findAll() {
		return personService.findAll();
	}

	public void savePersonsToFile(File file) {
		Utils.savePersonsToFile(personService.findAll(), file);
	}

	public void loadPersonsToFile(File file) {
		Utils.laodPersonsFromFile(personService.findAll(), file);
	}

	public void deleteById(int id) {
		personService.deleteById(id);
	}

}
