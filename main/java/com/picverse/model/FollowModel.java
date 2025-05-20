package com.picverse.model;

public class FollowModel {
	private int id;
	private int followerId;
	private int followingId;

	// Default constructor
	public FollowModel() {
	}

	// Constructor with fields
	public FollowModel(int id, int followerId, int followingId) {
		this.id = id;
		this.followerId = followerId;
		this.followingId = followingId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public int getFollowingId() {
		return followingId;
	}

	public void setFollowingId(int followingId) {
		this.followingId = followingId;
	}

}
