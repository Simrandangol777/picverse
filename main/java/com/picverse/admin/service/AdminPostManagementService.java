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

	public boolean deletePostById(int id) {
		String deleteCommentsSql = "DELETE FROM comment WHERE post_id=?";
		String deleteSavedPostsSql = "DELETE FROM saved_post WHERE post_id=?";
		String deleteLikesSql = "DELETE FROM post_like WHERE post_id=?";
		String deletePostSql = "DELETE FROM post WHERE id=?";

		try (Connection conn = DatabaseConfig.getDbConnection()) {
			conn.setAutoCommit(false); // Start transaction

			try (PreparedStatement deleteCommentsStmt = conn.prepareStatement(deleteCommentsSql);
					PreparedStatement deleteSavedPostsStmt = conn.prepareStatement(deleteSavedPostsSql);
					PreparedStatement deleteLikesStmt = conn.prepareStatement(deleteLikesSql);
					PreparedStatement deletePostStmt = conn.prepareStatement(deletePostSql)) {
				// Set parameters and execute
				deleteCommentsStmt.setInt(1, id);
				deleteCommentsStmt.executeUpdate();

				deleteSavedPostsStmt.setInt(1, id);
				deleteSavedPostsStmt.executeUpdate();

				deleteLikesStmt.setInt(1, id);
				deleteLikesStmt.executeUpdate();

				deletePostStmt.setInt(1, id);
				int rowsAffected = deletePostStmt.executeUpdate();

				conn.commit(); 
				return rowsAffected > 0;
			} catch (SQLException e) {
				conn.rollback(); 
				e.printStackTrace();
			} finally {
				conn.setAutoCommit(true);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

}
