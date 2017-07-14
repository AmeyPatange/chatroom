package com.chatroom.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.CommentDao;
import com.chatroom.model.Comment;
@Repository
public class CommentDaoImpl implements CommentDao{

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addComment(Comment comment) {
		
		Session session =sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try
		{
			session.save(comment);
			t.commit();
			session.close();
			return true;
		}catch(Exception e)
		{
			session.close();
			return false;
		}
		
	}

	public List getComment(int blogid) {
		
		Session session = sessionFactory.openSession();
		try
		{
			Query qry = session.createQuery("From Comment c where c.blogid=:blogid");
			qry.setParameter("blogid", blogid);
			List comments = qry.list();
			if( comments!= null)
			{
				session.close();
				return comments;
			}
			throw new Exception();
		}catch(Exception e)
		{
			session.close();
			return null;
		}
	}

	
}
