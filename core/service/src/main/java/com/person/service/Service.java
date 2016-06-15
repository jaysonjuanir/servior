package com.person.service;

/**
 * Hello world!
 *
 */

import com.person.dao.PersonDao;
import com.person.model.Person;

public class Service
{
    PersonDao p = new PersonDao();
	
	public void printAllPerson(){
		List<Person> s = p.getAllPerson();
		s.forEach(System.out::println);
	}
	
	Person newPerson = new Person("a", "b", "c", "d", "e");
	public void addPerson(Person newPerson){
		p.addPerson(newPerson);
		System.out.println("PERSON ADDED!!");
	}
	public void updatePerson(Person updatedPerson){
		p.updatePerson(updatedPerson);
		System.out.println("PERSON UPDATED!");
	}
	
	public void deletePerson(Person deletePerson){
		p.deletePerson(deletePerson);
		System.out.println("PERSON DELETED!!");
	}
	
	public void exitProgram(){
		System.exit(0);
	}
}
