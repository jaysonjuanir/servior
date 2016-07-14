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
import com.person.model.Name;

import com.person.dao.ContactDao;
import com.person.model.Contact;
import com.person.model.ContactType;

import com.person.dto.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import com.person.util.UtilSession;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.Date;
public class Service
{
	public Service(){
		new UtilSession();
	}
    PersonDao p = new PersonDao();
	ContactDao c = new ContactDao();
	
	public void printAllPeople(List<PersonDto> people){
		people.forEach((ppl) ->{
			System.out.println(ppl);
			ppl.getPerson_contact().forEach(System.out::println);
			System.out.println();
			ppl.getRoles().forEach(System.out::println);
			//System.out.println();
		});
		UtilSession.log();
	}
	
	//Person newPerson = new Person("a", "b", "c", "d", "e");
	public void executeCreatePerson(PersonDto newPerson){
		p.addPerson(newPerson);
		
	}
	public void executeUpdatedPerson(PersonDto updatedPerson){
		p.updatePerson(updatedPerson);
		
	}
	public void executeCreatedPersonContact(Contact contact){
		c.addContact(contact);
		System.out.println("CONTACT CREATED!");
	}
	public void deletePerson(PersonDto deletePerson){
		p.deletePerson(deletePerson);
		
	}
	public PersonDto toDto(Person person){
		return p.toDto(person);
	}
	public Person toEntity(PersonDto personDto){
		return p.toEntity(personDto);
	}
	public PersonDto getPersonById(int personId){
		return p.getPersonById(personId);
	}
	public Address createAddress(String streetNumber, String barangay, String city, String zipCode){
		return new Address(streetNumber, barangay, city, zipCode);
	}
	public Contact createContact(ContactType type, String value){
		Contact contact = new Contact();
		contact.setContact_type(type);
		contact.setContact_value(value);
		return contact;
	}
	public PersonDto updatePerson(PersonDto tbUpdatePerson, String firstName, String middleName, String lastName, String suffix, String title, double gwa, AddressDto address, Date birthday, Date date_hired, boolean employed){
		PersonDto updatedPerson = tbUpdatePerson;
		NameDto updatedName = updatedPerson.getName();
		if(!firstName.equals(""))//validation if empty
			updatedName.setPerson_first_name(firstName);
		if(!middleName.equals(""))
			updatedName.setPerson_middle_name(middleName);
		if(!lastName.equals(""))
			updatedName.setPerson_last_name(lastName);
		if(!suffix.equals(""))
			updatedName.setPerson_suffix(suffix);
		if(!title.equals(""))
			updatedName.setPerson_title(title);
		updatedPerson.setName(updatedName);
		updatedPerson.setPerson_GWA(gwa);
		updatedPerson.setBirthday(birthday);
		updatedPerson.setDate_hired(date_hired);
		updatedPerson.setEmployed(employed);
		updatedPerson.setAddress(address);
		return updatedPerson;
	}
	public AddressDto updateAddress(AddressDto tbUpdateAddress, String streetNumber, String barangay, String city, String zipcode){
		AddressDto updatedAddress = tbUpdateAddress;
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
	public List<PersonDto> getPersonByLastName(){
		return p.getPersonByLastName();
	}
	public List<PersonDto> getPersonByGWA(){
		return p.getPersonByGWA();
	}
	public List<PersonDto> getPersonByDateHired(){
		return p.getPersonByDateHired();
	}
	public List<PersonDto> getPeople(){
		return p.getPeople();
	}
	
	public List<Contact> getContactByPersonId(int personId){
		PersonDto person = p.getPersonById(personId);
		return c.getContactByPerson(p.toEntity(person));
	}
	public Contact getContactById(int contact_id){
		return c.getContactById(contact_id);
	}
	public Contact createContact(ContactType type, String value, Person personContact){
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
	public void executeCreateContactDto(ContactDto createdContact, PersonDto person){
		c.addContactDto(createdContact,toEntity(person));
		System.out.println("CONTACT ADDED!");
	}
	public void executeUpdateContactDto(ContactDto createdContact, PersonDto person){
		c.updateContactDto(createdContact,toEntity(person));
		System.out.println("CONTACT ADDED!");
	}
	public void executeUpdateContact(Contact updatedContact){
		//p.updatePersonContact(personId, updatedContact);
		c.updateContact(updatedContact);
		System.out.println("CONTACT UPDATED!");
	}
	public boolean checkRequired(String firstName, String middleName, String lastName, String streetNumber, String barangay, String city, String zipcode){
		boolean indicator = true;
		try{
			if(firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() ||
			streetNumber.isEmpty() || barangay.isEmpty() || city.isEmpty() || zipcode.isEmpty()){
				indicator = false;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return indicator;
	}
	
	public boolean checkDate(String date){
		boolean indicator = false;
		try{
			Date bday = convertDate(date);
			indicator = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return indicator;
	}
	public boolean checkEmployed(String dateHired, String [] roles){
		//boolean indicator =false;
		try{
			if(!dateHired.isEmpty() && roles != null){
				System.out.println("\nemployed\n");
				convertDate(dateHired);
				System.out.println("\nno error in employed\n");
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("\nerror in employed\n");
		return false;
	}
	public boolean checkDecimal(String dec){
		try{
			Double.parseDouble(dec);
			return false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}
	public Date convertDate(String date)throws Exception{
		Date d = null;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
			d = df.parse(date);
		return d;
	}
	public PersonDto createPerson(String personId, String firstName, String middleName, String lastName, String suffix, String title, String gender, String birthday, String employed, String dateHired, String gwa, String [] roles, String streetNumber, String barangay, String city, String zipcode, String [] type, String emailValue, String mobileValue, String landlineValue, String emailId, String mobileId, String landlineId){
		PersonDto person = new PersonDto();
		try{
			NameDto names = new NameDto(firstName, middleName, lastName, suffix, title);
			AddressDto address = new AddressDto(streetNumber, barangay, city, zipcode);
			person.setName(names);
			person.setAddress(address);
			if(!personId.isEmpty()){
				person.setId(Integer.parseInt(personId));
			}
			if(checkDate(birthday)){
				person.setBirthday(convertDate(birthday));
			}
			boolean isEmployed = false;
			Date hired = null;
			Set<RolesDto> rolesDto = new HashSet<>();
			if(employed.equals("yes")){
				isEmployed = true;
				if(checkDate(dateHired)){
					hired = (convertDate(dateHired));
				}
				if(roles != null){
					for(String role : roles){
						System.out.println(role);
						RolesDto roleDto = getRoleByType(role);
						rolesDto.add(roleDto);
					}
				}
			}
			System.out.println("SET OF ROLESSSSSSSS: " + rolesDto);
			person.setEmployed(isEmployed);
			person.setDate_hired(hired);
			person.setRoles(rolesDto);
			if(!checkDecimal(gwa)){
				person.setPerson_GWA(Double.parseDouble(gwa));
			}
			
			Set<ContactDto> contacts = new HashSet<>();
			
			if(type != null){
				//clearContact(Integer.parseInt(personId));
				for(String t:type){
					System.out.println(t);
				}
				int countTest=0;
				for(String contactType : type){
					System.out.println(contactType);
					ContactDto contactDto = new ContactDto();
					contactDto.setContact_type(ContactType.valueOf(contactType));
					if(contactType.equals("email")){
						contactDto.setContact_value(emailValue);
						contactDto.setId(Integer.parseInt(emailId));
					}else if(contactType.equals("mobile")){
						contactDto.setContact_value(mobileValue);
						contactDto.setId(Integer.parseInt(mobileId));
					}else{
						contactDto.setContact_value(landlineValue);
						contactDto.setId(Integer.parseInt(landlineId));
					}
					contactDto.setContact_person(person);
					System.out.println(++countTest);
					contacts.add(contactDto);
					// if(!personId.isEmpty())
						// executeCreateContactDto(contactDto,person);
					
				}
			}
			
			person.setPerson_contact(contacts);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return person;
	}
	//
	//FOR CONTACTS!!
	//
	public void clearContact(int personId){
		c.clearContact(personId);
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
	public RolesDto getRoleByType(String type){
		Roles role = r.getRoleByType(type);
		System.out.println(role);
		RolesDto rs = new RolesDto();
		rs.setId(role.getId());
		rs.setRole_type(role.getRole_type());
		return rs;
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
