package com.chatroom.configuration;

import java.util.Properties;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.chatroom.model.*;

@Configuration
@EnableTransactionManagement
public class DBConfig {
	
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder lsf= new LocalSessionFactoryBuilder(getDataSource());
		Properties hp=new Properties();
		hp.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hp.setProperty("hibernate.hbm2ddl.auto", "update");
		hp.setProperty("hibernate.show_sql", "true");
		lsf.addProperties(hp);
		Class classes[]=new Class[] {User.class , Job.class , JobApplication.class , Blog.class , Friend.class , Comment.class , ProfilePic.class};
	    return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}
	
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("CHAT"); //CHAT schema name-- CHAT username
	    dataSource.setPassword("admin"); 
	    return dataSource;
	}
	
	@Bean
	public HibernateTransactionManager hibTransManagement(){
		return new HibernateTransactionManager(sessionFactory());
	}


}
