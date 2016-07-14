package com.person.dao;

/**
 * Hello world!
 *
 */

import com.person.util.UtilSession;
import com.person.model.Roles;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import java.util.ArrayList;
public class RolesDao
{
	//UtilSession utilSession;
	Session session;
	Transaction transac;
	//public RolesDao(){
	//	utilSession = new UtilSession();	
	//}
    public void addRole(Roles roles){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(roles);
		session.getTransaction().commit();
		session.close();
	}
	public List<Roles> getAllRoles(){
		//UtilSession utilSession = new UtilSession();
		session = UtilSession.getSessionFactory().openSession();
		List<Roles> roles = null;
		try{
			roles = session.createCriteria(Roles.class).addOrder( Order.asc("id") ).setCacheable(true).list();
			//session.close();
			//session.flush();
		}catch(HibernateException hex){
			hex.printStackTrace();
		}finally{
			session.close();
		}
		//session.close();
		return roles;
	}
	public void updateRole(Roles roles){
		session = UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.update(roles);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public void deleteRole(Roles deleteThisRole){
		session=UtilSession.getSessionFactory().openSession();
		try{
			transac = session.beginTransaction();
			session.delete(deleteThisRole);
			transac.commit();
		}catch(HibernateException hex){
			if(transac!=null)
				transac.rollback();
			hex.printStackTrace();
		}finally{
			session.close();
		}
	}
	public Roles getRoleById(int id){
		session = UtilSession.getSessionFactory().openSession();
		Roles thisRole = (Roles)session.get(Roles.class, id);
		session.close();
		return thisRole;
	}
	public Roles getRoleByType(String type){
		session = UtilSession.getSessionFactory().openSession();
		Roles role = null;
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Roles.class);
			criteria.add(Restrictions.eq("role_type",type));
			role = (Roles)criteria.uniqueResult();
			session.getTransaction().commit();
		}catch(HibernateException e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return role;
	}
	public void closeSessionFactory(){
		UtilSession.closeSessionFactory();
	}
}
