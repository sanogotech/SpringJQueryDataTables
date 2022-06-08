package com.macrosoft.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosoft.model.Person;
import com.macrosoft.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Collection<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		for (Person k : personRepository.findAll()) {
			persons.add(k);
		}
		return persons;
	}

	public Person findOne(Long id) {
		return (Person) personRepository.findById(id).get();
	}

	public void delete(long id) {
		personRepository.deleteById(id);
	}

	public void save(Person person) {
		personRepository.save(person);
	}
}
