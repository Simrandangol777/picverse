package com.picverse.model;

/**
 * User model class representing a user in the system
 */
public class UserModel {
	private int id;
	private String name;
	private String username;
	private String email;
	private Long phone;
	private String location;
	private String hobby;
	private String bio;
	private String profilePicture;
	private String password;

	// Default constructor
	public UserModel() {
	}

	public UserModel(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserModel(int id, String username, String profilePicture) {
		this.id = id;
		this.username = username;
		this.profilePicture = profilePicture;
	}


	public UserModel(String name, String username, String email, Long phone, String location, String hobby,
			String bio, String profilePicture) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.hobby = hobby;
		this.bio = bio;
		this.profilePicture = profilePicture;
	}

	// Constructor with fields
	public UserModel(int id, String name, String username, String email, Long phone, String location, String hobby,
			String bio, String profilePicture, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.location = location;
		this.hobby = hobby;
		this.bio = bio;
		this.profilePicture = profilePicture;
		this.password = password;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", phone="
				+ phone + ", location=" + location + ", hobby=" + hobby + ", bio=" + bio + ", profilePicture="
				+ profilePicture + "]";
	}

}