package com.chatroom.dao;

import java.util.List;

import com.chatroom.model.Friend;

public interface FriendDao {

	boolean sendRequest(Friend newFriend); //friend request sent

	boolean deletePendingRequest(int id); // delete Pending request

	List getFriends(String email); //get all friends for a particular user

	List suggestFriends(String email); //suggest friends

}
