package com.person.app;

/**
 * Hello world!
 *
 */


import com.person.service.Service;
import com.person.model.Person;
import com.person.model.Address;
import com.person.model.Roles;

import java.util.*;
import java.io.*;
public class App 
{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
						srvc.printAllPerson();
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
	public void listPersonByLastNameInput()throws IOException{
		
		List <Person> listPeople  = srvc.getPersonByLastName();
		listPeople.forEach(System.out::println);
	}
	public void listPersonByGWAInput()throws IOException{
		
		List<Person> listPeople = srvc.getPersonByGWA();
		listPeople.forEach(System.out::println);
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
			Address personAddress = new App().createPersonAddressInput();
			System.out.println("GWA: ");
			double gwa = Double.parseDouble(br.readLine());
			Person createdPerson = new Person(firstName, middleName, lastName, suffix, title, personAddress, gwa); //refactor this later!
			srvc.executeCreatePerson(createdPerson);
			
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
		}
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
			
			Person updatedPerson = srvc.updatePerson(tbUpdatePerson, firstName, middleName, lastName, suffix, title, gwa);
			srvc.executeUpdatedPerson(updatedPerson);
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
		}
	}
	public void deletePersonInput()throws IOException{
		try{
			System.out.print("Enter a valid id of person: ");
			int personId = Integer.parseInt(br.readLine());
			Person tbDeletePerson = srvc.getPersonById(personId);
			srvc.deletePerson(tbDeletePerson);
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
		}
	}
	//-----------------------------------ROLE---------------------------------------------------------
	public void createRoleInput() throws IOException{
		try{
			System.out.println("Enter role type: ");
			String roleType = br.readLine();
			Roles createdRole = new Roles(roleType); //refactor this later!
			srvc.executeCreateRole(createdRole);
			
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
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
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
		}
	}
	public void deleteRoleInput()throws IOException{
		try{
			System.out.print("Enter a valid id of role: ");
			int roleId = Integer.parseInt(br.readLine());
			Roles tbDeleteRole = srvc.getRoleById(roleId);
			srvc.deleteRole(tbDeleteRole);
		}catch(NumberFormatException|IOException|NullPointerException ex){
			System.out.print("ERROR! \t");
			ex.printStackTrace();
			br.read();
		}
	}
	
	public static void printMenu(){
		System.out.println("==================================");
		System.out.println("==================================");
		System.out.println("1. Print the Peoples");
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
