package com.chatroom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.dao.UserDao;
import com.chatroom.model.ProfilePic;
import com.chatroom.model.User;
import com.chatroom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userD;
	
	public boolean addUser(User user) {
		boolean added = userD.addUser(user);
		return added;
	}

	public boolean isUserAlreadyPresent(String email) {
		return userD.isUserAlreadyInDtabase(email);
	}

	public User getUser(String email) {
		
		User user = (User) userD.getUserByEmail(email);
		if(user!=null)
		{	
			if(user.getApproved()=='N' || user.getApproved()=='n')
			{
				user.setPassword("");
				return user;
			}
			return user;
		}
		return null;
	}

	public void updateUser(User user) {
		 userD.updateUser(user);
	}

	public void enableOrDisableUser(String email, char approved) {
		userD.enableOrDisable(email, approved);
		
	}

	public List getAllUsers() {
		return userD.getAllUser();
	}

	public void uploadPic(ProfilePic pp) {
		userD.uploadPic(pp);
	}

	public ProfilePic getProfilePic(String useremail) {
		return userD.getPic(useremail);
	}

	
}
