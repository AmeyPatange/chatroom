package com.chatroom.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatroom.dao.CommentDao;
import com.chatroom.model.Comment;
import com.chatroom.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao comD;
	
	public boolean addComment(Comment comment) {
		return comD.addComment(comment);
		
	}

	public List getCommentsForBlog(int blogid) {
		return comD.getComment(blogid);
	}

}
