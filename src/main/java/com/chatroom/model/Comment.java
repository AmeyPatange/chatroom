package com.chatroom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="BlogComment")
public class Comment {
	
	@Id
	@GeneratedValue
	public int id;
	
	public int blogid;
	
	@Lob
	public String blogComment;
	
	@Column(length=40)
	public String email_commentBy;
	
	public String fname_commentBy; 
	
	@Column(length=20)
	public String commentDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBlogid() {
		return blogid;
	}

	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}

	public String getComment() {
		return blogComment;
	}

	public String getEmail_commentBy() {
		return email_commentBy;
	}

	public void setEmail_commentBy(String email_commentBy) {
		this.email_commentBy = email_commentBy;
	}

	public String getFname_commentBy() {
		return fname_commentBy;
	}

	public void setFname_commentBy(String fname_commentBy) {
		this.fname_commentBy = fname_commentBy;
	}

	public void setComment(String comment) {
		this.blogComment = comment;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	

}
