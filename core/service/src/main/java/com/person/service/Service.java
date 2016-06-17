package com.person.service;

/**
 * Hello world!
 *
 */

import com.person.dao.PersonDao;
import com.person.model.Person;

import com.person.dao.RolesDao;
import com.person.model.Roles;

import com.person.model.Address;
import com.person.util.UtilSession;
import java.util.List;

public class Service
{
	public Service(){
		UtilSession.getSessionFactory();
	}
    PersonDao p = new PersonDao();
	
	public void printAllPerson(){
		List<Person> s = p.getAllPerson();
		s.forEach(System.out::println);
	}
	
	//Person newPerson = new Person("a", "b", "c", "d", "e");
	public void executeCreatePerson(Person newPerson){
		p.addPerson(newPerson);
		System.out.println("PERSON CREATED!!");
	}
	public void executeUpdatedPerson(Person updatedPerson){
		p.updatePerson(updatedPerson);
		System.out.println("PERSON UPDATED!");
	}
	
	public void deletePerson(Person deletePerson){
		p.deletePerson(deletePerson);
		System.out.println("PERSON DELETED!!");
	}
	public Person getPersonById(int personId){
		return p.getPersonById(personId);
	}
	public Address createAddress(String streetNumber, String barangay, String city, String zipCode){
		return new Address(streetNumber, barangay, city, zipCode);
	}
	public Person updatePerson(Person tbUpdatePerson, String firstName, String middleName, String lastName, String suffix, String title, double gwa){
		Person updatedPerson = tbUpdatePerson;
		if(!firstName.equals(""))//validation if empty
			updatedPerson.setPerson_first_name(firstName);
		if(!middleName.equals(""))
			updatedPerson.setPerson_middle_name(middleName);
		if(!lastName.equals(""))
			updatedPerson.setPerson_last_name(lastName);
		if(!suffix.equals(""))
			updatedPerson.setPerson_suffix(suffix);
		if(!title.equals(""))
			updatedPerson.setPerson_title(title);
		updatedPerson.setPerson_GWA(gwa);
		return updatedPerson;
	}
	public List<Person> getPersonByLastName(){
		return p.getPersonByLastName();
	}
	public List<Person> getPersonByGWA(){
		return p.getPersonByGWA();
	}
	
	
	
	
	//-----------------------------ROLES--------------------------------------------------------
	RolesDao r = new RolesDao(); 
	
	public void printAllRoles(){
		List<Roles> s = r.getAllRoles();
		s.forEach(System.out::println);
	}
	public Roles getRoleById(int roleId){
		return r.getRoleById(roleId);
	}
	public void executeCreateRole(Roles newRole){
		r.addRole(newRole);
		System.out.println("ROLE CREATED!!");
	}
	public void executeUpdatedRole(Roles updatedRole){
		r.updateRole(updatedRole);
		System.out.println("ROLE UPDATED!");
	}
	
	public Roles updateRole(Roles tbUpdateRole, String type){
		Roles updatedRole = tbUpdateRole;
		if(!type.equals(""))//validation if empty
			updatedRole.setRole_type(type);
		return updatedRole;
	}
	public void deleteRole(Roles deleteRole){
		r.deleteRole(deleteRole);
		System.out.println("ROLE DELETED!!");
	}
	
	public void endProgram(){
		p.closeSessionFactory();
	}
}
