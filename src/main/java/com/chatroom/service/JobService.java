package com.chatroom.service;

import java.util.List;

import com.chatroom.model.Job;
import com.chatroom.model.JobApplication;

public interface JobService {

	void addJob(Job job); // add Job

	public List getAllJobs(); //get all jobs that are Verified

	public List getJobs(); // get all Jobs that are Verified as well as non-verified

	boolean validateJob(int id, char status); //set status i.e. validate job

	boolean applyForJob(JobApplication applied); //applied for job 

}
