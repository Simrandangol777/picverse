package com.picverse.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picverse.config.DatabaseConfig;

/*
 * PostLikeService handles the like/unlike functionality for posts.
 * It manages database interactions for liking and unliking posts.
 */
public class PostLikeService {

	/*
	 * LikeResult is a simple data structure to hold the result of the like/unlike
	 * operation.
	 */
	public static class LikeResult {
		public boolean liked;
		public int likeCount;

		public LikeResult(boolean liked, int likeCount) {
			this.liked = liked;
			this.likeCount = likeCount;
		}
	}

	/**
	 * Toggles the like status of a post for a user.
	 * 
	 * @param postId The ID of the post to like/unlike.
	 * @param userId The ID of the user liking/unliking the post.
	 * @return A LikeResult object containing the new like status and count.
	 * @throws Exception If an error occurs during database operations.
	 */
	public LikeResult toggleLike(int postId, int userId) throws Exception {
		boolean liked;

		Connection conn = DatabaseConfig.getDbConnection();

		// Check if already liked
		String checkSql = "SELECT * FROM post_like WHERE post_id = ? AND user_id = ?";
		PreparedStatement checkStmt = conn.prepareStatement(checkSql);
		checkStmt.setInt(1, postId);
		checkStmt.setInt(2, userId);
		ResultSet rs = checkStmt.executeQuery();

		if (rs.next()) {
			// Unlike
			String deleteSql = "DELETE FROM post_like WHERE post_id = ? AND user_id = ?";
			PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
			deleteStmt.setInt(1, postId);
			deleteStmt.setInt(2, userId);
			deleteStmt.executeUpdate();
			deleteStmt.close();
			liked = false;
		} else {
			// Like
			String insertSql = "INSERT INTO post_like (post_id, user_id, is_liked) VALUES (?, ?, ?)";
			PreparedStatement insertStmt = conn.prepareStatement(insertSql);
			insertStmt.setInt(1, postId);
			insertStmt.setInt(2, userId);
			insertStmt.setBoolean(3, true);
			insertStmt.executeUpdate();
			insertStmt.close();
			liked = true;
		}

		rs.close();
		checkStmt.close();

		// Get like count
		String countSql = "SELECT COUNT(*) FROM post_like WHERE post_id = ?";
		PreparedStatement countStmt = conn.prepareStatement(countSql);
		countStmt.setInt(1, postId);
		ResultSet countRs = countStmt.executeQuery();

		int likeCount = 0;
		if (countRs.next()) {
			likeCount = countRs.getInt(1);
		}

		countRs.close();
		countStmt.close();
		conn.close();

		return new LikeResult(liked, likeCount);
	}
}