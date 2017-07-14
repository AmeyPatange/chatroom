package com.chatroom.dao;

import java.util.List;

import com.chatroom.model.Comment;

public interface CommentDao {

	boolean addComment(Comment comment); // add Comment

	List getComment(int blogid); // get comments

}
