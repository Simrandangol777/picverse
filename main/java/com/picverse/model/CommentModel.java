package com.picverse.model;

public class CommentModel {
	private int id;
	private String comment;
	private int postId;
	private String userId;
    private String commentUserName;
    private String commentProfilePicture;
	
	public CommentModel() {
		// Default constructor
	}
	
	public CommentModel(int id, String comment, int postId, String userId) {
		this.id = id;
		this.comment = comment;
		this.postId = postId;
		this.userId = userId;
	}
	
	public CommentModel(int id, String comment, int postId, String userId, String commentUserName, String commentProfilePicture) {
        this.id = id;
        this.comment = comment;
        this.postId = postId;
        this.userId = userId;
        this.commentUserName = commentUserName;
        this.commentProfilePicture = commentProfilePicture;
    }
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getCommentProfilePicture() {
		return commentProfilePicture;
	}

	public void setCommentProfilePicture(String commentProfilePicture) {
		this.commentProfilePicture = commentProfilePicture;
	}
	
	@Override
	public String toString() {
	    return "Comment {id=" + id + ", userId=" + userId + ", comment=" + comment + "}";
	}
}
