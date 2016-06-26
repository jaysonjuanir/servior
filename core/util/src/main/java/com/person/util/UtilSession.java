package com.person.util;

/**
 * Hello world!
 *
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

public class UtilSession 
{
    static Configuration configuration;
	static StandardServiceRegistryBuilder builder;
    static SessionFactory factory;
	
	public UtilSession(){
		configuration = new Configuration().configure();
		builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    	factory = configuration.buildSessionFactory(builder.build());
	}
	
	public static SessionFactory getSessionFactory(){
		
		return factory;
	}
	public static void closeSessionFactory(){
		factory.close();
	}
}
