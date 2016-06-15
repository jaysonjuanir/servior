package com.person.app;

/**
 * Hello world!
 *
 */


import com.person.service.Service;

import java.util.*;
import java.io.*;
public class App 
{
    public static void main( String[] args )
    {
		
	//for updating.....
	//Person updateThisPerson = p.getPersonById(1);
		//updateThisPerson.setPerson_first_name("jayson");
	//p.deletePerson(updateThisPerson);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			try{
				App.printMenu();
				int choice = Integer.parseInt(br.nextLine());
				switch(choice){
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					default:
						break;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
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
			System.out.println("==================ROLES================");
			System.out.println("13. Add Role");
			System.out.println("13. Update Role");
			System.out.println("13. Delete Role");
			System.out.println("13. List Role");
			System.out.println("==================================");
			System.out.println("==================================");
	}
}
