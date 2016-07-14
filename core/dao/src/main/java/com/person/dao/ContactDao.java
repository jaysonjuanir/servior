package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.*;
import com.person.dto.ContactDto;
import com.person.dto.PersonDto;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
public class ContactDao
{
	//UtilSession utilSession;
	Session session;
	Transaction transac;
	//public ContactDao(){
	//	utilSession = new UtilSession();	
	//}
    public void addContact(Contact contact){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(contact);
		session.getTransaction().commit();
		session.close();
	}
	public void clearContact(int id){
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "delete Contact where person_id=:ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", id);

		query.executeUpdate();
		session.close();
	}
	public void addContactDto(ContactDto contact, Person person){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(toEntity(contact,person));
		session.getTransaction().commit();
		session.close();
	}
	public void updateContactDto(ContactDto contact, Person person){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(toEntity(contact,person));
		session.getTransaction().commit();
		session.close();
	}
	public List<Contact> getAllContact(){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		List<Contact> contacts = null;
		try{
			contacts = session.createCriteria(Contact.class).list();
			//session.close();
			//session.flush();
		}catch(HibernateException hex){
			hex.printStackTrace();
		}finally{
			session.close();
		}
		//session.close();
		return contacts;
	}
	public List<Contact> getContactByPerson(Person person){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		List<Contact> contacts = null;
		Integer id = person.getId();
		try{
			contacts = session.createCriteria(Contact.class).add(Restrictions.like("person_id", id)).setCacheable(true).list();
			//session.close();
			//session.flush();
		}catch(HibernateException hex){
			hex.printStackTrace();
		}finally{
			session.close();
		}
		//session.close();
		return contacts;
	}
	public void updateContact(Contact contact){
		session = UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.update(contact);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void deleteContact(Contact deleteThisContact){
		session=UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.delete(deleteThisContact);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public Contact getContactById(int id){
		session = UtilSession.getSessionFactory().openSession();
		Contact thisContact = (Contact)session.get(Contact.class, id);
		session.close();
		return thisContact;
	}
	public Contact toEntity(ContactDto c, Person person){
		
			Contact contact = new Contact();
			ContactType type=c.getContact_type();
			
			contact.setContact_type(type);
			contact.setContact_value(c.getContact_value());
			//PersonDto prs = new PersonDto();
			//BeanUtils.copyProperties(prs, c.getContact_person());
			contact.setContact_person(person);
			contact.setId(c.getId());
			
			
			//ContactDto contactDto = new ContactDto();
			//BeanUtils.copyProperties(contactDto, c);
		
		return contact;
	}
	public void closeSessionFactory(){
		UtilSession.closeSessionFactory();
	}
}
