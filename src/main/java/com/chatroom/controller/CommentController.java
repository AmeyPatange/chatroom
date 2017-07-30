package com.chatroom.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatroom.model.Comment;
import com.chatroom.model.User;
import com.chatroom.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	CommentService serv;
	
	@RequestMapping(value="/addComment" , method= RequestMethod.POST)
	public ResponseEntity<?> addcomment(@RequestBody Comment comment , HttpSession session)
	{
		if(!session.isNew())
		{
			User user = (User)session.getAttribute("user");
			comment.setEmail_commentBy(user.getEmail());
			comment.setFname_commentBy(user.getFname());
			
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			comment.setCommentDate(dateFormat.format(date));
			boolean isDone = serv.addComment(comment);
			
			if(isDone)
			{
				return new ResponseEntity<Boolean>(true , HttpStatus.OK);
			}
			Error error = new Error("Something Wen Wrong! Please Try Again");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Error error = new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/getAllComment" , method= RequestMethod.GET)
	public ResponseEntity<?> getComment(@RequestParam("blogid")int blogid)
	{
		List comments = serv.getCommentsForBlog(blogid);
		if(comments!=null)
			return new ResponseEntity<List>(comments, HttpStatus.OK);
		Error error = new Error("No Comments");
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}
	
}

