package com.picverse.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;

public class HomeService {

	/**
	 * Retrieves all posts from the database, including user information and like
	 * counts.
	 *
	 * @param userId The ID of the user to check for likes (can be null).
	 * @return A list of PostModel objects representing all posts.
	 */
	public ArrayList<PostModel> getAllPosts(Integer userId) {
		// Initialize an empty list to store PostModel objects
		ArrayList<PostModel> posts = new ArrayList<>();

		try {
			Connection conn = DatabaseConfig.getDbConnection();

			/*
			 * SQL query to retrieve all posts along with user information and like counts.
			 * The subquery counts the number of likes for each post and checks if the user
			 * has liked the post.
			 */
			String sql = "SELECT p.id, p.caption, p.image, p.created_at, u.username, u.profile_picture, "
					+ "(SELECT COUNT(*) FROM post_like WHERE post_id = p.id) AS like_count, "
					+ "(SELECT COUNT(*) FROM post_like WHERE post_id = p.id AND user_id = ?) AS is_liked "
					+ "FROM post p JOIN user u ON p.user_id = u.id ORDER BY p.id DESC";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId == null ? -1 : userId);

			ResultSet rs = stmt.executeQuery();

			/*
			 * Iterate through the result set and create PostModel objects for each post.
			 * Set the like count and whether the user has liked the post.
			 */
			while (rs.next()) {
				PostModel post = new PostModel(rs.getInt("id"), rs.getString("caption"), rs.getString("image"),
						rs.getString("username"), rs.getString("profile_picture"), rs.getTimestamp("created_at"));

				post.setLikeCount(rs.getInt("like_count"));
				post.setLiked(rs.getInt("is_liked") > 0);

				posts.add(post);
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return posts;
	}
}
