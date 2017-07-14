package com.chatroom.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.BlogDao;
import com.chatroom.model.Blog;

@Repository
public class BlogDaoImpl implements BlogDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean addBlog(Blog blog) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(blog);
			t.commit();
			session.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public List getAllBlogs() {
		
		Session session = sessionFactory.openSession();
		Query qry = session.createQuery("from Blog");
		List blogs = (List)qry.list();
		session.close();
		
		return blogs;
	}

	public List getAllValidBlogs() {
		
		Session session = sessionFactory.openSession();
		Query qry = session.createQuery("from Blog b where b.approved='Y' ");
		List blogs = (List)qry.list();
		session.close();
		
		return blogs;
	}

	public boolean enableBlog(int id, char approved) {
	
		Session session = sessionFactory.openSession();
		try
		{
			Transaction t = session.beginTransaction();
			Blog blog = (Blog)session.get(Blog.class, id);
			blog.setApproved(approved);
			t.commit();
			session.close();
			return true;
			
		}catch(Exception e){
			session.close();
			return false;
		}
	}

	public void updateLikes(int id, int likes) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		Blog blog = (Blog)session.get(Blog.class, id);
		blog.setLikes(likes);
		t.commit();
		session.close();
		
	}

	public boolean updateBlog(Blog blog) {
		
		Session session = sessionFactory.openSession();
		try
		{
			Transaction t = session.beginTransaction();
			session.update(blog);
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
	
}
