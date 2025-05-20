package com.picverse.admin.service;

import java.sql.*;
import java.util.*;

import com.picverse.config.DatabaseConfig;

public class AdminPostManagementService {
	
	/**
	 * Retrieves all posts from the database.
	 *
	 * @return a list of maps, each containing post details
	 */
    public ArrayList<Map<String, Object>> getAllPosts() {
        ArrayList<Map<String, Object>> posts = new ArrayList<>();
        String sql = "SELECT p.id, p.caption, p.image, u.username FROM post p JOIN user u ON p.user_id = u.id";

        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> post = new HashMap<>();
                post.put("postId", rs.getInt("id"));
                post.put("username", rs.getString("username"));
                post.put("caption", rs.getString("caption"));
                post.put("image", rs.getString("image"));
                posts.add(post);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // You can improve error handling later
        }

        return posts;
    }

    public boolean deletePostById(int postId) {
        String sql = "DELETE FROM post WHERE id = ?";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
