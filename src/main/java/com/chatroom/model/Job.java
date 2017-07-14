package com.chatroom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Job {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=50)
	private String title;
	
	@Lob  // for more than 255 characters
	private String description;
	
	@Column(length=50)
	private String companyName;
	
	@Column(length=50)
	private String location;
	
	@Column(name="Experience")
	private String yearsOfExperience;
	
	@Column(length=20)
	private String postDate;
	
	private char status;
	
	@Column(length=50)
	private String minQualification;

	@Column(length=40)
	private String postedBy;
	
	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getMinQualification() {
		return minQualification;
	}

	public void setMinQualification(String minQualification) {
		this.minQualification = minQualification;
	}
	
	
	
}
