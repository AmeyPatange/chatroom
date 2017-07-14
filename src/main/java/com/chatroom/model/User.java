package com.chatroom.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="USERS")
public class User implements Serializable {

	@Id
	@NotEmpty
	@Column(name="Email" , length = 40 )
	private String email;
	
	@Column(name="FName", length = 20)
	private String fname;
	
	@Column(name="LName", length = 20)
	private String lname;
	
	@Column(name="DOB", length = 12)
	private String dateOfBirth;
	
	@Column(name="Gender")
	private char gender;
	
	@Column(name="City" , length = 20)
	private String city;
	
	@Column(name="Country" , length = 20)
	private String country;
	
	@Column(name="Password", length= 12)
	@Size(min=6 , max=12)
	private String password;
	
	@Column(name="MobileNo" , unique=true, length=10)
	@Size(min=10, max=10)
	private String mobile;
	
	@Column(name="RegisteredOn")
	private String registrationDate;
	
	@Column(name="Approved")
	@NotNull
	private char approved;
	
	@Column(name="User_Online")
	private char online;
	
	@Column(name="Initial_Role" , length=20)
	private String userIdentity;
	
	@Column(name="Current_Role", length=20)
	private String currentRole;
	

	public String getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public char getApproved() {
		return approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public char getOnline() {
		return online;
	}

	public void setOnline(char online) {
		this.online = online;
	}
	
	
}
