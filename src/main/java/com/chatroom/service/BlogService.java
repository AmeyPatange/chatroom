package com.chatroom.service;

import java.util.List;

import com.chatroom.model.Blog;

public interface BlogService {

	boolean addBlog(Blog blog); // method to add Blog

	List getAllBlogs(); //get all blogs

	List getAllValidBlogs(); // get all Valid Blogs

	boolean enableBlog(int id, char approved); //enable or disable Blog

	void updateLikes(int id, int likes); //updates like of Blog

	boolean updateBlog(Blog blog); // update blog

}
