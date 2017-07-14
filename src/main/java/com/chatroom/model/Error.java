package com.chatroom.model;

public class Error {

	private String message;
	
	Error(String message)
	{
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
