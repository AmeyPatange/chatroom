package com.chatroom.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.UserDao;
import com.chatroom.model.ProfilePic;
import com.chatroom.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired 
	SessionFactory sessionFactory; 
	
	public boolean addUser(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(user);
		t.commit();
		
		session.close();
		
		return true;
	}

	
	public boolean isUserAlreadyInDtabase(String email) {
		
		User user= null;
		Session session = sessionFactory.openSession();
		user = (User)session.get(User.class, email);
		if(user != null)
		{
			System.out.println(user.getEmail());
			session.close();
			return true;
		}
		
		session.close();
		return false;
	}


	public User getUserByEmail(String email) {
		Session session = sessionFactory.openSession();
		System.out.println(email);
		Query qry = session.createQuery("From User u where u.email=:email");
		qry.setParameter("email", email);
		try{
			User user = (User)qry.uniqueResult();
			return user;
		}
		catch(Exception e)
		{
			System.out.println("Hii");
			return null;
		}
	}


	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}


	public void enableOrDisable(String email, char approved) {
		Session session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		User user = (User)session.get(User.class, email);
		user.setApproved(approved);
		tx.commit();
		session.close();
	}


	public List getAllUser() {
		Session session = sessionFactory.openSession();
		Query qry = session.createQuery("From User");
		List users = qry.list();
		return users;
	}


	public void uploadPic(ProfilePic pp) {
	
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(pp);
		t.commit();
		session.close();
	}


	public ProfilePic getPic(String useremail) {
		
		Session session = sessionFactory.openSession();
		ProfilePic pp = (ProfilePic)session.get( ProfilePic.class , useremail );
		session.close();
		return pp;
	}
	
	

}
