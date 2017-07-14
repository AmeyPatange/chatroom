package com.chatroom.dao;

import java.util.List;

import com.chatroom.model.Job;
import com.chatroom.model.JobApplication;

public interface JobDao {

	void addJob(Job job);// add Job

	List<Job> getAllJobs(); //get Jobs that are verified

	List getJobs(); //all jobs 

	boolean validateJob(int id, char status); // set staus of job

	boolean apply(JobApplication applied); //apply for job

}
