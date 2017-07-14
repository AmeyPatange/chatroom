package com.chatroom.service;

import java.util.List;
import com.chatroom.model.Friend;

public interface FriendService {

	boolean sendFriendRequest(Friend newFriend); // to send friend request

	boolean deletePendingRequest(int id); // to delete pending request

	List getFriendsList(String email); // get Friends By Email

	List suggestFriends(String email); // suggest friends for a user

	
}
