package com.person.model;

import com.person.model.Person;
import java.text.MessageFormat;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Embedded;
import javax.persistence.Transient;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "contact")
@Access(value=AccessType.FIELD)
public class Contact{
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "contact_id", unique = true, nullable = false)
	private int contact_id;
	
	@Column(name="contact_type")
	private String contact_type;
	
	@Column(name="contact_value")private String contact_value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id", nullable = false)
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
	public Person getContact_person(){
		return person;
	}
	public void setContact_person(Person person){
		this.person=person;
	}
	public String toString(){
		return MessageFormat.format("\tContacts: {0} = {1} ", this.contact_type, this.contact_value);
	}
}
