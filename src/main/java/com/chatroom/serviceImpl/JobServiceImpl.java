package com.chatroom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.dao.JobDao;
import com.chatroom.model.Job;
import com.chatroom.model.JobApplication;
import com.chatroom.service.JobService;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	JobDao jobD;
	
	public void addJob(Job job) {
		 jobD.addJob(job);
	}

	public List<Job> getAllJobs() {
		return jobD.getAllJobs();
	}

	public List getJobs() {
		return jobD.getJobs();
	}

	public boolean validateJob(int id, char status) {
		return jobD.validateJob(id, status);
	}

	public boolean applyForJob(JobApplication applied) {
		return jobD.apply(applied);
	}

}
