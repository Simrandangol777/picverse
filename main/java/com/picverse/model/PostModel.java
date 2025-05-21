package com.picverse.model;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class PostModel {
	private int id;
	private String caption;
	private String image;
	private Timestamp createdAt;
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

	public PostModel(int id, String caption, String image, String username, String profilePicture,
			Timestamp createdAt) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.username = username;
		this.profilePicture = profilePicture;
		this.createdAt = createdAt;
	}

	public PostModel(int id, String caption, String image, Timestamp createdAt, String username, String profilePicture, int userId) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.createdAt = createdAt;
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	/*
	 * This method calculates the time elapsed since the post was created and
	 * returns it in a human-readable format. It uses the createdAt timestamp to
	 */
	public String getTimeAgo() {
		LocalDateTime createdTime = this.createdAt.toLocalDateTime();
		LocalDateTime now = LocalDateTime.now();

		Duration duration = Duration.between(createdTime, now);
		long seconds = duration.getSeconds();

		if (seconds < 60)
			return seconds + " s";
		if (seconds < 3600)
			return (seconds / 60) + " min";
		if (seconds < 86400)
			return (seconds / 3600) + " hr";
		if (seconds < 2592000)
			return (seconds / 86400) + " d";
		if (seconds < 31536000)
			return (seconds / 2592000) + " mon";
		return createdTime.toLocalDate().toString();
	}
}
