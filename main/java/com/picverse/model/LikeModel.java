package com.picverse.model;

public class LikeModel {
	private int postId;
	private int userId;
	private boolean isLiked;

	public LikeModel(int postId, int userId, boolean isLiked) {
		this.postId = postId;
		this.userId = userId;
		this.isLiked = isLiked;
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

}
