package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.Person;
import com.person.model.Contact;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
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
		System.out.println("PERSON CREATED!!");
		session.close();
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
			session.close();
		}
		//session.close();
		return persons;
	}
	public void updatePerson(Person person){
		session = UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.update(person);
			transac.commit();
			System.out.println("PERSON UPDATED!");
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
			System.out.println("PERSON DELETED!!");
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public List<Person> getPersonByLastName(){
		List<Person> people = new ArrayList<>();
		session = UtilSession.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			//tx = session.beginTransaction();
			//String hql = "FROM com.person.model.Person ORDER BY person_last_name";
			//Query query = session.createQuery(hql);
			//query.setParameter("id",1);
			
			people = session.createCriteria(Person.class).addOrder( Order.asc("person_last_name") ).list();
			System.out.println(people);
			//people = query.list();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return people;
	}
	public List<Person> getPersonByGWA(){
		List<Person> people = new ArrayList<>();
		session = UtilSession.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			/*tx = session.beginTransaction();
			String hql = "from person where gwa = :gwa";
			Query query = session.createQuery(hql);
			query.setParameter("gwa",gwa);
			persons = query.list();*/
			people = session.createCriteria(Person.class).addOrder( Order.asc("person_GWA") ).list();
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return people;
	}
	public Person getPersonById(int id){
		session = UtilSession.getSessionFactory().openSession();
		Person thisPerson = (Person)session.get(Person.class, id);
		session.close();
		return thisPerson;
	}
	
	public void closeSessionFactory(){
		UtilSession.closeSessionFactory();
	}
}
