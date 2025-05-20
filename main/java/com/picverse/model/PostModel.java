package com.picverse.model;

public class PostModel {
	private int id;
	private String caption;
	private String image;
	private int userId;
	private String username;
	private String profilePicture;
	private int likeCount;
	private boolean isLiked;
	private boolean isSaved;

	public PostModel() {
		super();
	}

	public PostModel(int id, String caption, String image) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
	}

	public PostModel(String caption, String image) {
		super();
		this.caption = caption;
		this.image = image;
	}

	public PostModel(int id, String caption, String image, String username, String profilePicture) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.username = username;
		this.profilePicture = profilePicture;
	}

	public PostModel(int id, String caption, String image, String username, String profilePicture, int userId) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.username = username;
		this.profilePicture = profilePicture;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public boolean isLiked() {
		return isLiked;
	}
	
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	
	public boolean isSaved() {
		return isSaved;
	}
	
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
}
