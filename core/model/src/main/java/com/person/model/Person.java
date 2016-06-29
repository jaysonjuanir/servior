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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="person")
@Table(name="person")
public class Person 
{
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name = "person_id", unique=true, nullable=false)
    private int person_id;
	
	@Column(name = "person_last_name")
	private String person_last_name;
	
	@Column(name = "person_middle_name")
	private String person_middle_name;
	
	@Column(name = "person_first_name")
	private String person_first_name;
	
	@Column(name = "person_suffix")
	private String person_suffix;
	
	@Column(name = "person_title")
	private String person_title;
	
	@Embedded
	private Address address;
	
	@Column(name = "gwa")
	private Double person_gwa;
	
	@Column(name = "person_birthday")
	private Date birthday;
	
	@Column(name = "person_employed")
	private boolean isEmployed;
	
	
	@Column(name = "person_date_hired")
	private Date date_hired;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
	private Set<Contact> contact;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "personrole", joinColumns = { 
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
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
	@Transient
	public String getFullName(){
		return person_first_name + " " + person_middle_name + " " + person_last_name;
	}
	
	@Override
	public String toString(){
		return MessageFormat.format("{0} Name: {1} {2} {3} {4} {5} \n\tAddress: {6} \n\tGWA:{7} \n\tBirthday: {8}\n\tEmployed: {9}\tDate Hired: {10}", this.person_id, this.person_first_name, this.person_middle_name, this.person_last_name, this.person_suffix, this.person_title, this.address, this.person_gwa, this.birthday, this.isEmployed, this.date_hired);
	}
}
