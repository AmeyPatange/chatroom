package com.chatroom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.dao.FriendDao;
import com.chatroom.model.Friend;
import com.chatroom.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDao friendD;
	
	public boolean sendFriendRequest(Friend newFriend) {
		return friendD.sendRequest(newFriend);
	}

	public boolean deletePendingRequest(int id) {
		return friendD.deletePendingRequest(id);
	}

	public List getFriendsList(String email) {
	   return friendD.getFriends(email);
	}

	public List suggestFriends(String email) {
		
		return friendD.suggestFriends(email);
	}

}
