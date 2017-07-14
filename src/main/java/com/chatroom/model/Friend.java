package com.chatroom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Friend {

	@Id
	@GeneratedValue
	private int id;
	
	private String toUser;
	
	private String fromUser;
	
	private char status; // Y -- yes , N -- reject , P --pending
	
	@Column(name="On_line")
	private char online;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getOnline() {
		return online;
	}

	public void setOnline(char online) {
		this.online = online;
	}
	
	
	
}
