package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.Contact;
import com.person.model.Person;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		session.save(contact);
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
	public void closeSessionFactory(){
		UtilSession.closeSessionFactory();
	}
}
