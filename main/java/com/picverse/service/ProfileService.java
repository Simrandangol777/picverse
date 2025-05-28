package com.picverse.service;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;
import com.picverse.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Service class for handling user profile operations. Connects to the database,
 * retrieves user information and posts based on user ID or username.
 */
public class ProfileService {
		/**
	 * Retrieves user information based on the provided username.
	 *
	 * @param username the username of the user to retrieve
	 * @return a UserModel object containing user information, or null if not found
	 * @throws Exception if an error occurs during database access
	 */
    public UserModel getUserByUsername(String username) throws Exception {
        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "SELECT id, username, profile_picture FROM user WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
			// Check if the user exists in the database
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setProfilePicture(rs.getString("profile_picture"));
                return user;
            }
        }
        return null;
    }

    public UserModel getUserById(int userId) throws Exception {
        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "SELECT id, username, profile_picture FROM user WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
//
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setProfilePicture(rs.getString("profile_picture"));
                return user;
            }
        }
        return null;
    }
/**
	 * Retrieves a list of posts made by the user with the specified user ID.
	 *
	 * @param userId the ID of the user whose posts to retrieve
	 * @return a list of PostModel objects containing post information
	 * @throws Exception if an error occurs during database access
	 */
    public List<PostModel> getPostsByUserId(int userId) throws Exception {
        List<PostModel> posts = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "SELECT id, image FROM post WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PostModel post = new PostModel();
                post.setId(rs.getInt("id"));
                post.setImage(rs.getString("image"));
                posts.add(post);
            }
        }
        return posts; // Return the list of posts
    }
}
