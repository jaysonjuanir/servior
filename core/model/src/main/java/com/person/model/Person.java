package com.person.model;

/**
 * Hello world!
 *
 */

import java.text.MessageFormat;

public class Person 
{
    private int person_id;
	private String person_last_name;
	private String person_middle_name;
	private String person_first_name;
	private String person_suffix;
	private String person_title;
	
	public Person(){}
	
	public Person(String lastName, String middleName, String firstName, String suffix, String title){
		person_last_name=lastName;
		person_middle_name=middleName;
		person_first_name=firstName;
		person_suffix=suffix;
		person_title=title;
	}
	public void setPerson_id(int id){
		person_id = id;
	}
	public int getPerson_id(){
		return person_id;
	}
	public void setPerson_last_name(String lastName){
		person_last_name=lastName;
	}
	public String getPerson_last_name(){
		return person_last_name;
	}
	public void setPerson_middle_name(String middleName){
		person_middle_name=middleName;
	}
	public String getPerson_middle_name(){
		return person_middle_name;
	}
	public void setPerson_first_name(String firstName){
		person_first_name=firstName;
	}
	public String getPerson_first_name(){
		return person_first_name;
	}
	public void setPerson_suffix(String suffix){
		person_suffix=suffix;
	}
	public String getPerson_suffix(){
		return person_suffix;
	}
	public void setPerson_title(String title){
		person_title=title;
	}
	public String getPerson_title(){
		return person_title;
	}
	
	public String toString(){
		return MessageFormat.format("{0} {1} {2} {3} {4}", this.person_first_name, this.person_middle_name, this.person_last_name, this.person_suffix, this.person_title);
	}
}
