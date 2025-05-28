package com.picverse.service;

/*
 * Import statements for the EditProfileService class.
 */
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.UserModel;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

/*
 * EditProfileService handles the editing of user profiles. It manages database
 * interactions for updating user information and saving profile pictures.
 */
public class EditProfileService {

	/*
	 * getuserById method retrieves user details from the database based on the
	 * provided userId. It returns a UserModel object containing the user's
	 * information.
	 * 
	 */
	public UserModel getUserById(int userId) throws Exception {
		Connection conn = DatabaseConfig.getDbConnection();
		String sql = "SELECT * FROM user WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		UserModel user = null;

		/*
		 * If a user is found, create a UserModel object with the retrieved data. The
		 * profile picture is set to "logo.png" if it is null or empty.
		 */
		if (rs.next()) {
			user = new UserModel(rs.getString("name"), rs.getString("username"), rs.getString("email"),
					rs.getLong("phone_number"), rs.getString("location"), rs.getString("hobby"), rs.getString("bio"),
					rs.getString("profile_picture") == null || rs.getString("profile_picture").isEmpty() ? "logo.png"
							: rs.getString("profile_picture"));
		}

		rs.close();
		stmt.close();
		conn.close();
		return user;
	}

	/*
	 * updateUserProfile method updates the user's profile information in the
	 * database. It takes various parameters such as name, phone number, location,
	 * hobby, bio, and image name. It returns true if the update is successful,
	 * false otherwise.
	 * 
	 */
	public boolean updateUserProfile(int userId, String name, Long phoneNumber, String location, String hobby,
			String bio, String imageName) throws Exception {
		Connection conn = DatabaseConfig.getDbConnection();
		String sql = "UPDATE user SET name=?, phone_number=?, location=?, hobby=?, bio=?, profile_picture=? WHERE id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, name);
		if (phoneNumber != null) {
			stmt.setLong(2, phoneNumber);
		} else {
			stmt.setNull(2, java.sql.Types.BIGINT);
		}
		stmt.setString(3, location);
		stmt.setString(4, hobby);
		stmt.setString(5, bio);
		stmt.setString(6, imageName);
		stmt.setInt(7, userId);

		int rowsUpdated = stmt.executeUpdate();

		stmt.close();
		conn.close();
		return rowsUpdated > 0;
	}

	/*
	 * saveProfilePicture method saves the uploaded profile picture to the server
	 * and returns the image name. It takes a Part object representing the file and
	 * the ServletContext for file path resolution.
	 * 
	 */
	public String saveProfilePicture(Part file, ServletContext context) throws Exception {
		String imageName = file.getSubmittedFileName();
		
		// To give a abosolute path so that image save in the upload image folder.
		// Give absolute path like "C:Advance Java/picverse/src/main/webapp/uploads/images/" of the project in the blank 
//		String uploadPath = "E:/20th/Advance Java/Restart/picverse/src/main/webapp/uploads/images/" + imageName;
		
		
		// This is the link of the temp folder of the image
		String uploadPath = context.getRealPath("uploads/images/") + imageName;
		
		System.out.println("Upload Path: " + uploadPath);

		try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = file.getInputStream()) {
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
		}

		return imageName;
	}
}