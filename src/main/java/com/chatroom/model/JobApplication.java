package com.chatroom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JobApplication {

	@Id
	@GeneratedValue
	private int  id;
	
	private int jobId;
	
	@Column(length=40)
	private String userEmail;

	private char validCandidate;
	
	public char getValidCandidate() {
		return validCandidate;
	}

	public void setValidCandidate(char validCandidate) {
		this.validCandidate = validCandidate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
