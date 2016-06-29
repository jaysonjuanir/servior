package com.person.util;

/**
 * Hello world!
 *
 */
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;

import com.person.model.*;

public class UtilSession 
{
    private static AnnotationConfiguration configuration;
	static StandardServiceRegistryBuilder builder;
    static SessionFactory factory;
	static Configuration cfg;
	
	public UtilSession(){
		//configuration = new Configuration().configure();
		cfg = new AnnotationConfiguration()
			.addAnnotatedClass(com.person.model.Contact.class)
			.addAnnotatedClass(com.person.model.Roles.class)
			.addAnnotatedClass(com.person.model.Person.class)
			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/hibernatedb")
			.setProperty("hibernate.connection.username", "postgres")
			.setProperty("hibernate.connection.password", "password")
			.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider")
			.setProperty("hibernate.cache.use_second_level_cache","true")
			.setProperty("hibernate.cache.use_query_cache","true")
			.setProperty("hibernate.enable_lazy_load_no_trans","true")
			.setProperty("hibernate.show_sql","true")
			.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory")
			;
		builder = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
    	factory = cfg.buildSessionFactory(builder.build());
	}
	
	public static SessionFactory getSessionFactory(){
		
		return factory;
	}
	public static void log(){
		System.out.println(factory.getStatistics().getEntityFetchCount());   
		System.out.println(factory.getStatistics().getSecondLevelCacheHitCount());
	}
	public static void closeSessionFactory(){
		factory.close();
	}
}
