package com.chatroom.service;

import java.util.List;

import com.chatroom.model.ProfilePic;
import com.chatroom.model.User;

public interface UserService {

	public boolean addUser(User user);   // to add User 

	public boolean isUserAlreadyPresent(String email); // to check if user already exist with particular email ID

	public User getUser(String email); //get User by Email

	public void updateUser(User user); //updates User 

	public void enableOrDisableUser(String email, char approved); //to Enable Or Disable particular email

	public List getAllUsers(); //to get All Users

	public void uploadPic(ProfilePic pp); // upload pic (insert and update)

	public ProfilePic getProfilePic(String useremail); // get images 
	
	
}
