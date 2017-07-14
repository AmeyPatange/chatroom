package com.chatroom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatroom.model.Friend;
import com.chatroom.model.User;
import com.chatroom.service.FriendService;

@RestController
public class FriendController {

	@Autowired
	FriendService serv;
	
	@RequestMapping(value="/addFriend" , method=RequestMethod.GET)
	public ResponseEntity<?> addFriend(@RequestParam("useremail")String email, HttpSession session)
	{
		if(!session.isNew())
		{
			User loggedInUser = (User)session.getAttribute("user");
			Friend newFriend = new Friend();
			newFriend.setFromUser(loggedInUser.getEmail());
			newFriend.setToUser(email);
			newFriend.setStatus('N');
			newFriend.setOnline('N');
			boolean isSent = serv.sendFriendRequest(newFriend);
			if(isSent)
			{
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
			else
			{
				Error error  = new Error("Failed To Send Friend Request");
				return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		Error error = new Error("You Are Not Logged In.");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/deletePendingRequest/{id}")
	public ResponseEntity<?> deletePendingRequest(@PathVariable("id")int id, HttpSession session)
	{
		if(!session.isNew())
		{
			boolean isDeleted = serv.deletePendingRequest(id);
			if(isDeleted)
			{
				return new ResponseEntity<Boolean>(true , HttpStatus.OK);
			}
			Error error = new Error("Friend Request Accepted");
			return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
		}
		Error error = new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/getAllFriends")
	public ResponseEntity<?> getAllFriendRequest(HttpSession session)//get all friend request for a user
	{
		if( !session.isNew())
		{
			User user = (User)session.getAttribute("user");
			List friends  = serv.getFriendsList(user.getEmail());
			return new ResponseEntity<List>( friends , HttpStatus.OK);
		}
		Error error = new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED );
	}
	
	@RequestMapping(value="/suggestFriends")
	public ResponseEntity<?> suggestFriends(HttpSession session)
	{
		if(!session.isNew())
		{
			User user = (User)session.getAttribute("user");
			List friends = serv.suggestFriends(user.getEmail());
			return new ResponseEntity<List>(friends, HttpStatus.OK);
		}
		Error error = new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
	
}
