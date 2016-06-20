package com.person.model;

import com.person.model.Person;
import java.text.MessageFormat;

public class Contact{
	private int contact_id;
	private String contact_type;
	private String contact_value;
	private Person person;

	public Contact(){}

	public Contact(String contact_type, String contact_value, Person person){
		this.contact_type = contact_type;
		this.contact_value = contact_value;
		this.person = person;
	}
	
	public void setContact_id(int id){
		this.contact_id=id;
	}
	public int getContact_id(){
		return contact_id;
	}
	
	public String getContact_type(){
		return contact_type;
	}

	public void setContact_type(String type){
		this.contact_type = type;
	}

	public String getContact_value(){
		return contact_value;
	}

	public void setContact_value(String value){
		this.contact_value = value;
	}
	public void setContact_person(Person person){
		this.person=person;
	}
	public Person getContact_person(){
		return person;
	}
	public String toString(){
		return MessageFormat.format("\n\tContacts: {0} = {1} ", this.contact_type, this.contact_value);
	}
}
