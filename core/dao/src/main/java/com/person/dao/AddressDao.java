package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.ArrayList;
public class AddressDao
{
	//UtilSession utilSession;
	Session session;
	Transaction transac;
	//public AddressDao(){
	//	utilSession = new UtilSession();	
	//}
    public void addAddress(Address address){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(address);
		session.getTransaction().commit();
		session.close();
	}
	public List<Address> getAllAddress(){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		List<Address> addresss = null;
		try{
			addresss = session.createCriteria(Address.class).list();
			//session.close();
			//session.flush();
		}catch(HibernateException hex){
			hex.printStackTrace();
		}finally{
			session.close();
		}
		//session.close();
		return addresss;
	}
	public void updateAddress(Address address){
		session = UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.update(address);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void deleteAddress(Address deleteThisAddress){
		session=UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.delete(deleteThisAddress);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public Address getAddressById(int id){
		session = UtilSession.getSessionFactory().openSession();
		Address thisAddress = (Address)session.get(Address.class, id);
		session.close();
		return thisAddress;
	}
	public void closeSessionFactory(){
		UtilSession.closeSessionFactory();
	}
}
