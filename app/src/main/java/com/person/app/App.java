package com.person.app;

/**
 * Hello world!
 *
 */


import com.person.service.Service;
import com.person.model.Person;
import com.person.model.Address;
import com.person.model.Roles;
import com.person.model.Contact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.io.*;
public class App 
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static Service srvc = new Service();
    public static void main( String[] args )
    {
		
	//for updating.....
	//Person updateThisPerson = p.getPersonById(1);
		//updateThisPerson.setPerson_first_name("jayson");
	//p.deletePerson(updateThisPerson);
		
		
		boolean indicator = true;
		App temp = new App();
		while(indicator){
			try{
				App.printMenu();
				int choice = Integer.parseInt(br.readLine());
				switch(choice){
					case 1:
						temp.listPeople();
						br.read();
						break;
					case 2:
						temp.createPersonInput();
						break;
					case 3:
						temp.deletePersonInput();
						break;
					case 4:
						temp.updatePersonInput();
						break;
					case 5:
						temp.listPersonByGWAInput();
						break;
					case 6:
						temp.listPersonByLastNameInput();
						break;
					case 7:
						temp.listPersonByDateHiredInput();
						break;
					case 8:
						temp.addPersonRoles();
						break;
					case 9:
						temp.deletePersonRoles();
						break;
					case 10:
						temp.createPersonContact();
						break;
					case 11:
						temp.updateContactsInput();
						break;
					case 12:
						temp.deleteContactsInput();
						break;
					case 13:
						temp.createRoleInput();
						break;
					case 14:
						temp.updateRoleInput();
						break;
					case 15:
						temp.deleteRoleInput();
						break;
					case 16:
						srvc.printAllRoles();
						break;
						
					case 0:
						System.out.println("The program will exit!");
						srvc.endProgram();
						indicator = false;
						break;
					default:
						System.out.println("Out of BOUNDS!!");
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		System.out.println("lol");
		System.exit(0);
    }
	public void listPeople(){
		List <Person> listPeople  = srvc.getPeople();
		srvc.printAllPeople(listPeople);
	}
	public void listPersonByDateHiredInput()throws IOException{
		
		List <Person> listPeople  = srvc.getPersonByDateHired();
		srvc.printAllPeople(listPeople);
	}
	public void listPersonByLastNameInput()throws IOException{
		List <Person> listPeople  = srvc.getPersonByLastName();
		srvc.printAllPeople(listPeople);
	}
	public void listPersonByGWAInput()throws IOException{
		
		List<Person> listPeople = srvc.getPersonByGWA();
		srvc.printAllPeople(listPeople);
	}
	public void createPersonInput()throws IOException{
		try{
			System.out.println("First Name: ");
			String firstName = br.readLine();
			System.out.println("Middle Name: ");
			String middleName = br.readLine();
			System.out.println("Last Name: ");
			String lastName = br.readLine();
			System.out.println("Suffix: ");
			String suffix = br.readLine();
			System.out.println("Title: ");
			String title = br.readLine();
			
			
			
			
			System.out.println("Birthday [MM/dd/yyyy]: ");
			Date birthday = new App().personDateInput();
			
			boolean isEmployed = false;
			Date date_hired = null;
			String input = "";
			while(!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))){
				System.out.println("Employed? [Y/n]");
				input = br.readLine();
				if(input.equalsIgnoreCase("y")){
					isEmployed=true;
					System.out.println("Date Hired [MM/dd/yyyy]:");
					date_hired = new App().personDateInput();
				}
			}
			
			
			
			
			Address personAddress = new App().createPersonAddressInput();
			
			System.out.println("GWA: ");
			double gwa = Double.parseDouble(br.readLine());
			
			Set<Roles> roles = new App().createPersonRole();//
			
			Person createdPerson = new Person(firstName, middleName, lastName, suffix, title, personAddress, gwa, null, roles, birthday, date_hired, isEmployed);
			Set<Contact> contacts = new App().createPersonContacts();
			contacts.forEach(a->{
				a.setContact_person(createdPerson);
			});
			createdPerson.setPerson_contact(contacts);
			
			
			//contacts.forEach(c->{
			//	srvc.executeCreatedPersonContact(c);
			//});
			
			//createdPerson.setPerson_contact(contacts);
			
			srvc.executeCreatePerson(createdPerson);
			
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	public Date personDateInput(){
		boolean indicator = false;
		//Date date = null;
		Scanner sc1 = new Scanner(System.in);
		String dateInput = "";
		
		/*while(!indicator){
			//sc1.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sc1.nextLine();
			System.out.println(!sc1.hasNext("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)$"));
			//sdf.setLenient(false);
			if(sc1.hasNext("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)$")){
				try{
					System.out.print("Enter valid date format [MM/dd/yyyy]: ");
					dateInput=sc1.nextLine();
					date = sdf.parse(dateInput);
					indicator=true;
				}catch(ParseException e){
					e.printStackTrace();
				}
			}
		}*/
		while(!sc1.hasNext("(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)$")){
			System.out.print("Please enter valid date format [MM/dd/yyyy]: ");
			sc1.nextLine();
		}
		String birthDate = sc1.nextLine();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try{
			date = df.parse(birthDate);
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		System.out.println(date);
		return date;
	}
	public Set<Roles> createPersonRole() throws IOException{
		
		Set<Roles> roles = new HashSet<Roles>();
		
		roles.add(new App().selectPersonRoleInput());
			
		return roles;
	}
	public void addPersonRoles()throws IOException{
		try{
			System.out.println("Please enter a valid person id: ");
			int personId = Integer.parseInt(br.readLine());
			Person person = srvc.getPersonById(personId);
			System.out.println("Person Found! Name: " +person.getFullName());

			Set<Roles> roles = person.getRoles();
			
			roles.add(new App().selectPersonRoleInput());
			
			person.setRoles(roles);
			srvc.executeUpdatedPerson(person);
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	public void deletePersonRoles()throws IOException{
		try{
			System.out.println("Please enter a valid person id: ");
			int personId = Integer.parseInt(br.readLine());
			Person person = srvc.getPersonById(personId);
			
			Set<Roles> roles = person.getRoles();
			Set<Roles> updatedRoles = new HashSet<Roles>();
			System.out.println("Begin display of person roles:");
			System.out.println("Person Found! Name: "+ person.getFullName());

			roles.forEach(System.out::println);
			Roles role = new App().selectPersonRoleInput();
				
			roles.forEach(r->{
				
				if( !(r.toString().equals( role.toString())) ){
					System.out.println(r.toString() + " and " + role.toString());
					updatedRoles.add(r);	
				}
			});

			System.out.println(updatedRoles);
			
			
			person.setRoles(updatedRoles);
			srvc.executeUpdatedPerson(person);
		}catch(NumberFormatException|IOException|NullPointerException ex){
			ex.printStackTrace();
		}
	}
	public Roles selectPersonRoleInput()throws IOException{
		srvc.printAllRoles();
		System.out.print("[Enter the id number]Choose role: ");
		int roleId = Integer.parseInt(br.readLine());
		Roles role = srvc.getRoleById(roleId);
		return role;
	}
	public Address createPersonAddressInput()throws IOException{
		System.out.println("Street Number: ");
		String streetNumber = br.readLine();
		System.out.println("Barangay: ");
		String barangay = br.readLine();
		System.out.println("City: ");
		String city = br.readLine();
		System.out.println("Zipcode: ");
		String zipCode = br.readLine();
		Address personAddress = srvc.createAddress(streetNumber, barangay, city, zipCode);
			
		return personAddress;
	}
	public Contact createPersonContactInput()throws IOException{ //for creating person included
		
		String type = "";
		String contactType = "";
		while(!(type.equalsIgnoreCase("m") || type.equalsIgnoreCase("l") || type.equalsIgnoreCase("e"))){
			System.out.println("Choose Type [Landline(L) Mobile Number(M) E-mail(E)]: {Enter capital of the letter only}" );
			type = br.readLine();
			if(type.equalsIgnoreCase("m") || type.equalsIgnoreCase("l") || type.equalsIgnoreCase("e"))
				contactType = type.equalsIgnoreCase("L")?"landline":type.equalsIgnoreCase("M")?"mobile":"email";
		}
		System.out.println("Enter Value: ");
		String value = br.readLine();
		return srvc.createContact(contactType, value);
		
	}
	public Set<Contact> createPersonContacts()throws IOException{
		boolean indicator = true;
		Set<Contact> contacts = new HashSet<Contact>();
		while(indicator){
			contacts.add(new App().createPersonContactInput());
			System.out.println("Add new contact? [Y/n]");
			String choice = br.readLine();
			if(choice.equalsIgnoreCase("N"))
				indicator=false;
		}
		
		return contacts;
	}
	public void createPersonContact(){
		try{
			System.out.println("Please enter a valid person id: ");
			int personId = Integer.parseInt(br.readLine());
			Person personContact = srvc.getPersonById(personId);
			
			String type = "";
			String contactType = "";
			String value = "";
			personContact.getPerson_contact().forEach(c->{System.out.println("Contact ID: "+c.getContact_id() + " " + c);});
			try{
				type = br.readLine();
				while(!(type.equalsIgnoreCase("m") || type.equalsIgnoreCase("l") || type.equalsIgnoreCase("e"))){
					System.out.println("Choose Type [Landline(L) Mobile Number(M) E-mail(E)]: {Enter capital of the letter only}" );
					type = br.readLine();
					if(type.equalsIgnoreCase("m") || type.equalsIgnoreCase("l") || type.equalsIgnoreCase("e"))
						contactType = type.equalsIgnoreCase("L")?"landline":type.equalsIgnoreCase("M")?"mobile":"email";
				}
				System.out.println("Enter "+contactType+" value: ");
				value = br.readLine();
				Contact contact = srvc.createContact(contactType, value, personContact);
				srvc.executeCreateContact(contact);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}catch(NumberFormatException|IOException|NullPointerException ex){
			ex.printStackTrace();
		}
	}
	public void updatePersonInput()throws IOException{
		try{
			System.out.print("Enter a valid id of person: ");
			int personId = Integer.parseInt(br.readLine());
			Person tbUpdatePerson = srvc.getPersonById(personId);
			System.out.println("First Name Update "+tbUpdatePerson.getPerson_first_name() + ": ");
			String firstName = br.readLine();
			System.out.println("Middle Name Update "+tbUpdatePerson.getPerson_middle_name() + ": ");
			String middleName = br.readLine();
			System.out.println("Last Name Update "+tbUpdatePerson.getPerson_last_name() + ": ");
			String lastName = br.readLine();
			System.out.println("Suffix Update "+tbUpdatePerson.getPerson_suffix() + ": ");
			String suffix = br.readLine();
			System.out.println("Title Update "+tbUpdatePerson.getPerson_title() + ": ");
			String title = br.readLine();
			System.out.println("GWA Update "+tbUpdatePerson.getPerson_GWA()+" : ");
			double gwa = Double.parseDouble(br.readLine());
			
			System.out.println("Birthday Update " +tbUpdatePerson.getBirthday()+"  :");
			System.out.println("Enter by [MM/dd/yyyy] format: ");
			Date birthday = new App().personDateInput();
			//System.out.println("Date Hired Update " +tbUpdatePerson.getDate_hired()+" : ");
			Date date_hired = null;
			boolean isEmployed = false;
			
			System.out.println("Update Employed? [Current value: "+tbUpdatePerson.getEmployed()+"] : ");
			String input = "";
			while(!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("n"))){
				System.out.println("Employed? [Y/n]");
				input = br.readLine();
				if(input.equalsIgnoreCase("y")){
					isEmployed=true;
					System.out.println("Date Hired:");
					System.out.println("Enter by [MM/dd/yyyy] format: ");
					date_hired = new App().personDateInput();
				}
			}
			
			Address tbUpdateAddress = tbUpdatePerson.getAddress();
			String promptUser = "";
			while(!( promptUser.equalsIgnoreCase("y") || promptUser.equalsIgnoreCase("n") )){
				System.out.print("Do you want to edit address? [Y/n] \t");
				promptUser = br.readLine();
				if(promptUser.equalsIgnoreCase("y"))
					tbUpdateAddress = new App().updateAddressInput(tbUpdateAddress);
			}
			
			
			Person updatedPerson = srvc.updatePerson(tbUpdatePerson, firstName, middleName, lastName, suffix, title, gwa, tbUpdateAddress, birthday, date_hired, isEmployed);
			srvc.executeUpdatedPerson(updatedPerson);
			
			
			
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	public Address updateAddressInput(Address tbUpdateAddress){
		Address updatedAddress = tbUpdateAddress;
		try{
			System.out.println("Street Number Update "+tbUpdateAddress.getAddress_street_number() + ": ");
			String streetNumber = br.readLine();
			System.out.println("Barangay "+tbUpdateAddress.getAddress_barangay() + ": ");
			String barangay = br.readLine();
			System.out.println("City Update "+tbUpdateAddress.getAddress_city() + ": ");
			String city = br.readLine();
			System.out.println("Zip Code Update "+tbUpdateAddress.getAddress_zipcode() + ": ");
			String zipcode = br.readLine();
			updatedAddress = srvc.updateAddress(tbUpdateAddress, streetNumber, barangay, city, zipcode);
			
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
		}
		return updatedAddress;
	}
	public void updateContactsInput()throws IOException{
		System.out.println("Please enter a valid person id: ");
		int personId = Integer.parseInt(br.readLine());
		Person personContact = srvc.getPersonById(personId);
		Set<Contact> contactSet = personContact.getPerson_contact();
		String promptUser="";
		while(!promptUser.equalsIgnoreCase("n")){
			System.out.println("Person Found! Name: " +personContact.getFullName());
			System.out.println("Continue Updating Contacts? [Y/n]");
			promptUser = br.readLine();
			if(promptUser.equalsIgnoreCase("y")){
				contactSet.forEach(c->{System.out.println(c.getContact_id() + " " + c);});
				System.out.println("Enter contact id: ");
				try{
					int contact_id = Integer.parseInt(br.readLine());
					Contact contact = new App().updateContactInput(personContact, contact_id);
					//System.out.println(contact.getContact_person() + " is equals to " + personContact + " " );
					if(contact.getContact_person().toString().equals(personContact.toString()))
						srvc.executeUpdateContact(contact);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		
	}
	public Contact updateContactInput(Person person, int id)throws IOException{
		Contact contact = srvc.getContactById(id);
		if(contact.getContact_person().toString().equals(person.toString())){
			String contactType = "";
			System.out.print("Update contact ["+contact.getContact_type()+"] value ["+contact.getContact_value()+"] : ");
			String contactValue = br.readLine();

			contact.setContact_value(contactValue);
			System.out.println(contact.getContact_person());
		}
		return contact;
	}
	public void deleteContactsInput()throws IOException{
		System.out.println("Please enter a valid person id: ");
		int personId = Integer.parseInt(br.readLine());
		Person personContact = srvc.getPersonById(personId);
		Set<Contact> contactSet = personContact.getPerson_contact();
		String promptUser="";
		while(!promptUser.equalsIgnoreCase("n")){
			System.out.println("Continue Deleting Contact? [Y/n]");
			promptUser = br.readLine();
			if(promptUser.equalsIgnoreCase("y")){
				contactSet.forEach(c->{System.out.println(c.getContact_id() + " " + c);});
				System.out.println("Enter contact id: ");
				try{
					int contact_id = Integer.parseInt(br.readLine());
					Contact contact = srvc.getContactById(contact_id);
					//System.out.println(contact.getContact_person() + " is equals to " + personContact + " " );
					if(contact.getContact_person().toString().equals(personContact.toString()))
						srvc.deleteContact(contact);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	
	public void deletePersonInput()throws IOException{
		try{
			System.out.print("Enter a valid id of person: ");
			int personId = Integer.parseInt(br.readLine());
			Person tbDeletePerson = srvc.getPersonById(personId);
			srvc.deletePerson(tbDeletePerson);
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	//-----------------------------------ROLE---------------------------------------------------------
	public void createRoleInput() throws IOException{
		try{
			System.out.println("Enter role type: ");
			String roleType = br.readLine();
			Roles createdRole = new Roles(roleType); //refactor this later!
			srvc.executeCreateRole(createdRole);
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	public void updateRoleInput()throws IOException{
		try{
			System.out.print("Enter a valid id of role: ");
			int roleId = Integer.parseInt(br.readLine());
			Roles tbUpdateRole = srvc.getRoleById(roleId);
			System.out.println("Role Type Update "+tbUpdateRole.getRole_type() + ": ");
			String type = br.readLine();
			Roles updatedRole = srvc.updateRole(tbUpdateRole, type);
			srvc.executeUpdatedRole(updatedRole);
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	public void deleteRoleInput()throws IOException{
		try{
			System.out.print("Enter a valid id of role: ");
			int roleId = Integer.parseInt(br.readLine());
			Roles tbDeleteRole = srvc.getRoleById(roleId);
			srvc.deleteRole(tbDeleteRole);
			br.read();
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			
		}
	}
	
	public static void printMenu(){
		System.out.println("==================================");
		System.out.println("==================================");
		System.out.println("1. Print the People");
		System.out.println("2. Create a Person");
		System.out.println("3. Delete a Person");
		System.out.println("4. Update a Person");
		System.out.println("5. List People by GWA");
		System.out.println("6. List People by Last name");
		System.out.println("7. List People by Date Hired");
		System.out.println("8. Add Person Role");
		System.out.println("9. Delete Person Role");
		System.out.println("10. Add Contacts");
		System.out.println("11. Update Contacts");
		System.out.println("12. Delete Contacts");
		System.out.println("==================================");
		System.out.println("===============ROLES==============");
		System.out.println("13. Add Role");
		System.out.println("14. Update Role");
		System.out.println("15. Delete Role");
		System.out.println("16. List Role");
		System.out.println("==================================");
		System.out.println("==================================");
		System.out.println("0. Exit Program");
		System.out.print("Please enter a valid number: ");
	}
}
