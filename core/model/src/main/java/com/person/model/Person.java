package com.person.model;

/**
 * Hello world!
 *
 */

import java.text.MessageFormat;
import com.person.model.Address;
import com.person.model.Contact;
import java.util.Set;
import java.util.Date;

public class Person 
{
    private int person_id;
	private String person_last_name;
	private String person_middle_name;
	private String person_first_name;
	private String person_suffix;
	private String person_title;
	private Address address;
	private Double person_gwa;
	private Date birthday;
	private boolean isEmployed;
	private Date date_hired;
	private Set<Contact> contact;
	private Set<Roles> roles;
	
	
	public Person(){}
	
	public Person(String firstName, String middleName, String lastName, String suffix, String title, Address personAddress, double gwa, Set<Contact> contact, Set<Roles> roles, Date birthday, Date date_hired, boolean isEmployed){
		person_last_name=lastName;
		person_middle_name=middleName;
		person_first_name=firstName;
		person_suffix=suffix;
		person_title=title;
		address = personAddress;
		person_gwa=gwa;
		this.contact=contact;
		this.roles=roles;
		this.birthday=birthday;
		this.date_hired=date_hired;
		this.isEmployed=isEmployed;
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
	public void setAddress(Address address){
		this.address=address;
	}
	public Address getAddress(){
		return this.address;
	}
	public void setPerson_GWA(Double gwa){
		person_gwa=gwa;
	}
	public Double getPerson_GWA(){
		return person_gwa;
	}
	public void setPerson_contact(Set<Contact> contact){
		this.contact=contact;
	}
	public Set<Contact> getPerson_contact(){
		return contact;
	}
	public void setRoles(Set<Roles> roles){
		this.roles=roles;	
	}
	public Set<Roles> getRoles(){
		return roles;
	}
	public Date getBirthday(){
		return this.birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getDate_hired(){
		return this.date_hired;
	}

	public void setDate_hired(Date date_hired){
		this.date_hired = date_hired;
	}
	public void setEmployed(boolean employed){
		isEmployed=employed;
	}
	public boolean getEmployed(){
		return isEmployed;
	}
	public String getFullName(){
		return person_first_name + " " + person_middle_name + " " + person_last_name;
	}
	public String toString(){
		return MessageFormat.format("{0} Name: {1} {2} {3} {4} {5} \n\tAddress: {6} \n\tGWA:{7} \n\tBirthday: {8}\n\tEmployed: {9}\tDate Hired: {10}", this.person_id, this.person_first_name, this.person_middle_name, this.person_last_name, this.person_suffix, this.person_title, this.address, this.person_gwa, this.birthday, this.isEmployed, this.date_hired);
	}
}
