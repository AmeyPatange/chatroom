package com.chatroom.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.FriendDao;
import com.chatroom.model.Friend;

@Repository
public class FriendDaoImpl implements FriendDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean sendRequest(Friend newFriend) {
		
		Session session = sessionFactory.openSession();
		try
		{
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(newFriend);
			t.commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			session.close();
			return false;
		}
	}

	public boolean deletePendingRequest(int id) {
		
		Session session = sessionFactory.openSession();
		try
		{
			Query qry = session.createQuery("from Friend f where f.id=:id AND f.status='P' ");
			qry.setParameter("id", id);
			Friend req = (Friend)qry.uniqueResult();
			Transaction t = session.beginTransaction();
			session.delete(req);
			t.commit();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			session.close();
			return false;
		}
	}

	public List getFriends(String email) {
	
		Session session = sessionFactory.openSession();
		try
		{
			Query qry = session.createQuery("From Friend f WHERE f.status='Y' AND f.toUser=:email ");
			qry.setParameter("email", email);
			List friends = qry.list();
			session.close();
			return friends;
		}catch(Exception e)
		{
			session.close();
			return null;
		}
		
	}

	public List suggestFriends(String email) {
		
		Session session = sessionFactory.openSession();
		String qry = "Select * From Users where Email in (select Email from Users where email!=? minus (select fromUser from Friend where toUser=? union select toUser from Friend where fromUser=?))";
		
		SQLQuery sqlQuery = session.createSQLQuery(qry); 
		sqlQuery.setString(0, email);
		sqlQuery.setString(1, email);
		sqlQuery.setString(2, email);
		List list = sqlQuery.list();
		session.close();
		return list;
	}

	

}
