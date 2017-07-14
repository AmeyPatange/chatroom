package com.chatroom.dao;

import java.util.List;

import com.chatroom.model.Blog;

public interface BlogDao {

	boolean addBlog(Blog blog); // adds Blog

	List getAllBlogs(); // get all Blogs

	List getAllValidBlogs();// get all Valid Blogs

	boolean enableBlog(int id, char approved);//enable or disable Blog

	void updateLikes(int id, int likes); //update Likes of Blog

	boolean updateBlog(Blog blog); // update blog

}
