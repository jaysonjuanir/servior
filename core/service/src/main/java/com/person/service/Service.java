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

import com.person.dao.ContactDao;
import com.person.model.Contact;

import com.person.util.UtilSession;
import java.util.List;
import java.util.Set;
import java.util.Date;
public class Service
{
	public Service(){
		UtilSession.getSessionFactory();
	}
    PersonDao p = new PersonDao();
	ContactDao c = new ContactDao();
	
	public void printAllPeople(List<Person> people){
		people.forEach((p) ->{
			System.out.println(p);
			p.getPerson_contact().forEach(System.out::println);
			System.out.println();
			p.getRoles().forEach(System.out::println);
			System.out.println();
		});
	}
	
	//Person newPerson = new Person("a", "b", "c", "d", "e");
	public void executeCreatePerson(Person newPerson){
		p.addPerson(newPerson);
		
	}
	public void executeUpdatedPerson(Person updatedPerson){
		p.updatePerson(updatedPerson);
		
	}
	public void executeCreatedPersonContact(Contact contact){
		c.addContact(contact);
		System.out.println("CONTACT CREATED!");
	}
	public void deletePerson(Person deletePerson){
		p.deletePerson(deletePerson);
		
	}
	public Person getPersonById(int personId){
		return p.getPersonById(personId);
	}
	public Address createAddress(String streetNumber, String barangay, String city, String zipCode){
		return new Address(streetNumber, barangay, city, zipCode);
	}
	public Contact createContact(String type, String value){
		Contact contact = new Contact();
		contact.setContact_type(type);
		contact.setContact_value(value);
		return contact;
	}
	public Person updatePerson(Person tbUpdatePerson, String firstName, String middleName, String lastName, String suffix, String title, double gwa, Address address, Date birthday, Date date_hired, boolean employed){
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
		updatedPerson.setBirthday(birthday);
		updatedPerson.setDate_hired(date_hired);
		updatedPerson.setEmployed(employed);
		updatedPerson.setAddress(address);
		return updatedPerson;
	}
	public Address updateAddress(Address tbUpdateAddress, String streetNumber, String barangay, String city, String zipcode){
		Address updatedAddress = tbUpdateAddress;
		if(!streetNumber.equals(""))
			updatedAddress.setAddress_street_number(streetNumber);
		if(!barangay.equals(""))
			updatedAddress.setAddress_barangay(barangay);
		if(!city.equals(""))
			updatedAddress.setAddress_city(city);
		if(!zipcode.equals(""))
			updatedAddress.setAddress_zipcode(zipcode);
		return updatedAddress;
	}
	public List<Person> getPersonByLastName(){
		return p.getPersonByLastName();
	}
	public List<Person> getPersonByGWA(){
		return p.getPersonByGWA();
	}
	public List<Person> getPersonByDateHired(){
		return p.getPersonByDateHired();
	}
	public List<Person> getPeople(){
		return p.getPeople();
	}
	
	public List<Contact> getContactByPersonId(int personId){
		Person person = p.getPersonById(personId);
		return c.getContactByPerson(person);
	}
	public Contact getContactById(int contact_id){
		return c.getContactById(contact_id);
	}
	public Contact createContact(String type, String value, Person personContact){
		return new Contact(type, value, personContact);
	}
	public void deleteContact(Contact contact){
		c.deleteContact(contact);
		System.out.println("DELETE CONTACT!");
	}
	public void executeCreateContact(Contact createdContact){
		c.addContact(createdContact);
		System.out.println("CONTACT ADDED!");
	}
	public void executeUpdateContact(Contact updatedContact){
		//p.updatePersonContact(personId, updatedContact);
		c.updateContact(updatedContact);
		System.out.println("CONTACT UPDATED!");
	}
	//
	//FOR CONTACTS!!
	//
	
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
