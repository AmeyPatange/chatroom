package com.chatroom.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatroom.model.User;
import com.chatroom.model.UserLogin;
import com.chatroom.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userServ;
	//adds User
	@RequestMapping(value="/addUser" , method= RequestMethod.POST , consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		System.out.println(user.getDateOfBirth()); //testing to see if object is modelled properly...
		
		String email = user.getEmail();
		System.out.println(email);
		boolean isPresent = userServ.isUserAlreadyPresent(email);
		System.out.println(isPresent);
		if(!isPresent)
		{	
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			user.setRegistrationDate(dateFormat.format(date));
			System.out.println(user.getRegistrationDate()); 
			
			user.setApproved('Y');
			user.setOnline('N');
			user.setCurrentRole(user.getUserIdentity());
			userServ.addUser(user);
			return new ResponseEntity<User>(user , HttpStatus.CREATED) ;
		}
		Error error = new Error("User Already Exist! Log In To Continue.");
		return new ResponseEntity<Error>(error, HttpStatus.CONFLICT );
	}
	
	//authenticate user
	@RequestMapping(value="/authenticate", method= RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> authenticate(@RequestBody UserLogin uLogin , HttpSession session)
	{
		User userPresent = userServ.getUser(uLogin.getEmail()); 
		
		if(userPresent == null)
		{
			session.invalidate();
			Error error = new Error("User Does Not Exist. Please SIGN UP To Continue!");
			return new ResponseEntity<Error>( error , HttpStatus.UNAUTHORIZED);
		}
		if(userPresent.getPassword().equals(uLogin.getPassword()))
		{
			userPresent.setOnline('Y');
			userServ.updateUser(userPresent);
			session.setAttribute("user", userPresent);
			return new ResponseEntity<User>(userPresent , HttpStatus.ACCEPTED);
		}
		session.invalidate();
		Error error = new Error("Invalid Id or Password");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
		
		
	}
	//update User
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public ResponseEntity<?> updateUser(@RequestBody User user, HttpSession session)
	{
		if(session.isNew())
		{
			session.invalidate();
			Error error = new Error("You Were Never Logged In!");
			return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
		}
		session.removeAttribute("user");
		session.setAttribute("user", user);
		System.out.println(user.getEmail());
		userServ.updateUser(user);
		return new ResponseEntity<User>(user , HttpStatus.OK);
	}

	//enable or disable user
	@RequestMapping(value="/enableOrDiableUser",method = RequestMethod.GET)
	public ResponseEntity<?> enableOrDisable(@RequestParam String email , @RequestParam char approved, HttpSession session)
	{
		if(!session.isNew())
		{
			userServ.enableOrDisableUser(email, approved);
			User user = (User)session.getAttribute("user");
			return new ResponseEntity<Boolean>(true , HttpStatus.OK);
		}
		session.invalidate();
		Error error = new Error("You Have Not Logged In!");
		return new ResponseEntity<Boolean>(false , HttpStatus.BAD_REQUEST);
	}
	
	
	//get all users
	@RequestMapping(value="/allUsers" ,method=RequestMethod.GET )
	public ResponseEntity<?> getAllUsers()
	{
		List users = userServ.getAllUsers();
		return new ResponseEntity<List>(users , HttpStatus.OK);
	}
	
	@RequestMapping(value="/Logout")
	public ResponseEntity<?> logout(HttpSession session)
	{
		if(session.isNew())
		{
			session.invalidate();
			Error error = new Error("You Were Never Logged In!");
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		User user = (User)session.getAttribute("user");
		user.setOnline('N');
		userServ.updateUser(user);
		session.invalidate();
		return new ResponseEntity<Boolean>(true , HttpStatus.OK);
	}
	
	@RequestMapping(value="/recoverAccount/")
	public ResponseEntity<?> recoverAcc(@RequestParam("email") String email)
	{
		System.out.println(email);
		User user = userServ.getUser(email);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
	}
}
