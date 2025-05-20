package com.picverse.model;

public class SavePostModel {
	private int id;
	private int userId;
	private String postId;
	private Boolean isSaved;
	
	public SavePostModel() {
		super();
	}
	
	public SavePostModel(int id, int userId, String postId, Boolean isSaved) {
		super();
		this.id = id;
		this.userId = userId;
		this.postId = postId;
		this.isSaved = isSaved;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getPostId() {
		return postId;
	}
	
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public Boolean getIsSaved() {
		return isSaved;
	}
	
	public void setIsSaved(Boolean isSaved) {
		this.isSaved = isSaved;
	}

}
