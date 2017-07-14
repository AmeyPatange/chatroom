 package com.chatroom.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatroom.model.Job;
import com.chatroom.model.JobApplication;
import com.chatroom.service.JobService;

@RestController
public class JobController {

	@Autowired
	JobService jobServ;
	
	@RequestMapping(value="/registerjob" , method= RequestMethod.POST )
	public ResponseEntity<?> addJob( @RequestBody Job job, HttpSession session)
	{
		if(!session.isNew())
		{
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			job.setPostDate(dateFormat.format(date));
			job.setStatus('N');
			jobServ.addJob(job);
			return new ResponseEntity<Boolean>(true , HttpStatus.OK);
		}
		else
		{
			session.invalidate();
			Error error = new Error("Login To Post Job");
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
		}
	}
	
	//for user
	@RequestMapping(value="/getJobs" , method= RequestMethod.GET)
	public ResponseEntity<?> getJobs(HttpSession session)
	{
		if(!session.isNew())
		{
			List jobs = jobServ.getAllJobs();
			if(jobs!=null)
				return new ResponseEntity<List>(jobs , HttpStatus.OK);
			
			Error error = new Error("No Jobs Currently Available");
			return new ResponseEntity<Error>(error , HttpStatus.NO_CONTENT);
		}
		Error error= new Error("Log In To View All Jobs");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	
	//for admin
	@RequestMapping(value="/alljobsforadmin")
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		if(!session.isNew())
		{	
				List jobs = jobServ.getJobs();
				if(jobs != null)
						return new ResponseEntity<List>(jobs , HttpStatus.OK);
				Error error = new Error("No Jobs");
				return new ResponseEntity<Error>(error , HttpStatus.NO_CONTENT);
		}
		Error error= new Error("Log In To View All Jobs");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/validateJob")
	public ResponseEntity<?> validate(@RequestParam("id") int id , @RequestParam("status")char status , HttpSession session )
	{
		if(!session.isNew())
		{	boolean isDone = jobServ.validateJob(id , status);
			if(isDone)
			{	
				return new ResponseEntity<Boolean>(true ,HttpStatus.OK );
			}
			Error error= new Error("Wrong Parameters Passed!");
			return new ResponseEntity<Error>(error , HttpStatus.BAD_REQUEST);
		}
		Error error= new Error("Log In To Validate Job");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/applyForJob" , method = RequestMethod.POST ) //to be written and checked again
	public ResponseEntity<?> apply(@RequestBody JobApplication applied, HttpSession session)
	{
		if(!session.isNew())
		{
			applied.setValidCandidate('N');
			boolean isApplied = jobServ.applyForJob(applied);
			if(isApplied)
			{	
				return new ResponseEntity<Boolean>(true , HttpStatus.OK);
			}
			Error error = new Error("Please Try Again");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		session.invalidate();
		Error error = new Error("Login To Apply For Job");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
}
