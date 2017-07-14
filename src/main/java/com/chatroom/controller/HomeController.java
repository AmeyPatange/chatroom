package com.chatroom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.chatroom.model.ProfilePic;
import com.chatroom.model.User;
import com.chatroom.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService serv;
	
	@RequestMapping(value="/uploadImage" , method= RequestMethod.POST)
	public ResponseEntity<?> uploadPic(@RequestParam CommonsMultipartFile image , HttpSession session)
	{
		if(!session.isNew())
		{
			User user = (User)session.getAttribute("user");
			ProfilePic  pp = new ProfilePic();
			pp.setEmail(user.getEmail());
			pp.setImage(image.getBytes());
			serv.uploadPic(pp);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		Error error = new Error("Please Log In to Upload File");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
		
	}
	
	@RequestMapping(value="/getPic/{useremail}")
	public @ResponseBody byte[] getPic(@PathVariable String useremail , HttpSession session)
	{
		if(!session.isNew())
		{
			ProfilePic pp = serv.getProfilePic(useremail);
			return pp.getImage();
		}
		return null;
	}
	
}
