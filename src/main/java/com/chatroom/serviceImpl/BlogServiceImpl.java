package com.chatroom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.chatroom.dao.BlogDao;
import com.chatroom.model.Blog;
import com.chatroom.service.BlogService;

@Repository
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogDao blogD;
	public boolean addBlog(Blog blog) {
		
		return blogD.addBlog(blog);
	}
	public List getAllBlogs() {
		
		return blogD.getAllBlogs();
	}
	public List getAllValidBlogs() {
		
		return blogD.getAllValidBlogs();
	}
	public boolean enableBlog(int id, char approved) {
		
		 return blogD.enableBlog(id, approved);
	}
	public void updateLikes(int id, int likes) {
		blogD.updateLikes(id, likes);
	}
	public boolean updateBlog(Blog blog) {
		return blogD.updateBlog(blog);
	}

	
}
