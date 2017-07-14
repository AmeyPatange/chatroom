package com.chatroom.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.JobDao;
import com.chatroom.model.Job;
import com.chatroom.model.JobApplication;

@Repository
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void addJob(Job job) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		session.save(job);
		t.commit();
		session.close();
		
	}

	public List<Job> getAllJobs() {
		
		Session session = sessionFactory.openSession();
		try
		{
			Query qry = session.createQuery("from Job j where j.status='Y' ");
			Object obj = qry.list();
			return (List<Job>)obj;
		}catch(Exception e)
		{
			return null;
		}
		
	}

	public List getJobs() {
	
		Session session = sessionFactory.openSession();
		try
		{
			Query qry = session.createQuery("from Job");
			return (List)qry.list();
		}
		catch(Exception e)
		{
			return null;
		}
	}

	public boolean validateJob(int id, char status) {
		
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try{
			Job job = (Job)session.get(Job.class, id);
			job.setStatus(status);
			t.commit();
			session.close();
			return true;
		}catch(Exception e)
		{
			session.close();
			return false;
		}
	}

	public boolean apply(JobApplication applied) {
		try
		{
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			session.save(applied);
			t.commit();
			session.close();
			return true;
		}catch(Exception e){return false;}
	}

}
