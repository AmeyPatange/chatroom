package com.chatroom.service;

import java.util.List;

import com.chatroom.model.Comment;

public interface CommentService {

	boolean addComment(Comment comment); // add comment

	List getCommentsForBlog(int blogid); // get all comments

}
