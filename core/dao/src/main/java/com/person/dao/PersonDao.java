package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;
public class PersonDao
{
	//UtilSession utilSession;
	Session session;
	Transaction transac;
	//public PersonDao(){
	//	utilSession = new UtilSession();	
	//}
    public void addPerson(Person person){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(person);
		session.getTransaction().commit();
	}
	public List<Person> getAllPerson(){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		List<Person> persons = null;
		try{
			persons = session.createCriteria(Person.class).list();
			//session.close();
			//session.flush();
		}catch(HibernateException hex){
			hex.printStackTrace();
		}finally{
			//session.close();
		}
		session.close();
		return persons;
	}
	public void updatePerson(Person person){
		session = UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.update(person);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void deletePerson(Person deleteThisPerson){
		session=UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.delete(deleteThisPerson);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}
	}
	public Person getPersonById(int id){
		session = UtilSession.getSessionFactory().openSession();
		Person thisPerson = (Person)session.get(Person.class, id);
		return thisPerson;
	}
}
