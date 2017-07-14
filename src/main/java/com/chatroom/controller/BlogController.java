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

import com.chatroom.model.Blog;
import com.chatroom.model.User;
import com.chatroom.service.BlogService;

@RestController
public class BlogController {

	@Autowired
	BlogService serv;
	
	@RequestMapping(value="/postBlog", method= RequestMethod.POST)
	public ResponseEntity<?> post(@RequestBody Blog blog , HttpSession session)
	{
		if(!session.isNew())
		{	User user = (User)session.getAttribute("user");
			blog.setPostedBy(user.getEmail());
			
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			blog.setBlogCreateDate(dateFormat.format(date));
			blog.setApproved('N');
			blog.setLikes(0);
			boolean isAdded = serv.addBlog(blog);
			if(isAdded)
			{
				return new ResponseEntity<Boolean>(true , HttpStatus.OK);
			}
			Error error = new Error("Blog Not Added! Try Again.");
			return new ResponseEntity<Error>(error, HttpStatus.NOT_MODIFIED);
		}
		Error error = new Error("You Are Not Logged IN! Please Log In..");
		return new ResponseEntity<Error>(error, HttpStatus.NOT_MODIFIED);
	
	}
	
	@RequestMapping(value="/getAllBlog" , method=RequestMethod.GET)
	public ResponseEntity<?> getBlog(HttpSession session)
	{
		if(!session.isNew())
		{
			List blogs = serv.getAllBlogs();
		
			if(blogs == null)
			{
				Error error = new Error("No Jobs Available");
				return new ResponseEntity<Error>(error, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List>(blogs , HttpStatus.OK);
		}
		Error error = new Error("Not Logged In!");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/getAllValidBlogs")
	public ResponseEntity<?> getValidBlog(HttpSession session)
	{
		if(!session.isNew())
		{
			List blogs = serv.getAllValidBlogs();
		
			if(blogs == null)
			{
				Error error = new Error("No New Blogs.");
				return new ResponseEntity<Error>(error,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List>(blogs, HttpStatus.OK);
		}
		Error error =new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/enableBlog")
	public ResponseEntity<?> enableBlog(@RequestParam("blogid")int id, @RequestParam("enable")char approved, HttpSession session)
	{
		if(!session.isNew())
		{
			boolean isEnabled =  serv.enableBlog(id , approved);
			if(!isEnabled)
			{
				Error error = new Error("Could Not Approve Job.Try Again");
				return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		Error error = new Error("You Are Not Logged IN!");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/updateLike")
	public ResponseEntity<?> updateLike(@RequestParam("blogid") int id, @RequestParam("like")int likes, HttpSession session)
	{
		if(!session.isNew())
		{
			serv.updateLikes(id , likes);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		Error error = new Error("You Are Not Logged IN!");
		return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value="/updateBlog")
	public ResponseEntity<?> updateBlog(@RequestBody Blog blog , HttpSession session)
	{
		if(!session.isNew())
		{
			boolean isUpdated = serv.updateBlog(blog);
			if(isUpdated)
			{
				return new ResponseEntity<Boolean>(true , HttpStatus.OK);
			}
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Error error = new Error("You Are Not Logged In!");
		return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED);
	}
	
}
