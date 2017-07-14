package com.chatroom.dao;

import java.util.List;

import com.chatroom.model.ProfilePic;
import com.chatroom.model.User;

public interface UserDao {

	boolean addUser(User user); //adding user to database 

	boolean isUserAlreadyInDtabase(String email); //checks if User already exist in database with particular email

	User getUserByEmail(String email); //get User By Email

	public void updateUser(User user); //updates User

    public	void enableOrDisable(String email, char approved); //to enable or disable user

	public List getAllUser(); // to get All Users 

	void uploadPic(ProfilePic pp); //upload pic in database

	ProfilePic getPic(String useremail); // get pics

}
