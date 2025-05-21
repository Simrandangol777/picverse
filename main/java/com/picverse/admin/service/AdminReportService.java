package com.picverse.admin.service;

import com.picverse.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * AdminReportService.java
 * 
 * This class provides methods to generate reports for the admin panel.
 * It retrieves user-related data such as post and comment counts.
 */
public class AdminReportService {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getAdminDashboardReports() throws Exception {
	    Map<String, Object> reports = new HashMap<>();

	    try (Connection conn = DatabaseConfig.getDbConnection()) {

	        // Total users
	        String totalUsersSQL = "SELECT COUNT(*) AS total_users FROM user";
	        PreparedStatement ps1 = conn.prepareStatement(totalUsersSQL);
	        ResultSet rs1 = ps1.executeQuery();
	        if (rs1.next()) {
	            reports.put("totalUsers", rs1.getInt("total_users"));
	        }

	        // Most followed users
	        String mostFollowedSQL = "SELECT u.username, COUNT(f.follower_id) AS followers FROM follow f " +
	                                 "JOIN user u ON f.following_id = u.id GROUP BY u.username ORDER BY followers DESC LIMIT 5";
	        PreparedStatement ps2 = conn.prepareStatement(mostFollowedSQL);
	        ResultSet rs2 = ps2.executeQuery();
	        List<Map<String, Object>> mostFollowedUsers = new ArrayList<>();
	        while (rs2.next()) {
	            Map<String, Object> user = new HashMap<>();
	            user.put("username", rs2.getString("username"));
	            user.put("followers", rs2.getInt("followers"));
	            mostFollowedUsers.add(user);
	        }
	        reports.put("mostFollowedUsers", mostFollowedUsers);

	        // Users with most likes received
	        String likesSQL = "SELECT u.username, COUNT(pl.id) AS likesReceived FROM post_like pl " +
	                          "JOIN post p ON pl.post_id = p.id JOIN user u ON p.user_id = u.id " +
	                          "GROUP BY u.username ORDER BY likesReceived DESC LIMIT 5";
	        PreparedStatement ps3 = conn.prepareStatement(likesSQL);
	        ResultSet rs3 = ps3.executeQuery();
	        List<Map<String, Object>> mostLikedUsers = new ArrayList<>();
	        while (rs3.next()) {
	            Map<String, Object> user = new HashMap<>();
	            user.put("username", rs3.getString("username"));
	            user.put("likesReceived", rs3.getInt("likesReceived"));
	            mostLikedUsers.add(user);
	        }
	        reports.put("mostLikedUsers", mostLikedUsers);

	        // Most active users (posts + comments)
	        String activeSQL = "SELECT u.username, " +
	                           " (SELECT COUNT(*) FROM post p WHERE p.user_id = u.id) AS postCount, " +
	                           " (SELECT COUNT(*) FROM comment c WHERE c.user_id = u.id) AS commentCount " +
	                           "FROM user u ORDER BY (postCount + commentCount) DESC LIMIT 5";
	        PreparedStatement ps4 = conn.prepareStatement(activeSQL);
	        ResultSet rs4 = ps4.executeQuery();
	        List<Map<String, Object>> mostActiveUsers = new ArrayList<>();
	        while (rs4.next()) {
	            Map<String, Object> user = new HashMap<>();
	            user.put("username", rs4.getString("username"));
	            user.put("postCount", rs4.getInt("postCount"));
	            user.put("commentCount", rs4.getInt("commentCount"));
	            mostActiveUsers.add(user);
	        }
	        reports.put("mostActiveUsers", mostActiveUsers);

	        // Users who saved the most posts
	        String savedSQL = "SELECT u.username, COUNT(sp.id) AS savedPosts FROM saved_post sp " +
	                          "JOIN user u ON sp.user_id = u.id GROUP BY u.username ORDER BY savedPosts DESC LIMIT 5";
	        PreparedStatement ps5 = conn.prepareStatement(savedSQL);
	        ResultSet rs5 = ps5.executeQuery();
	        List<Map<String, Object>> mostSavingUsers = new ArrayList<>();
	        while (rs5.next()) {
	            Map<String, Object> user = new HashMap<>();
	            user.put("username", rs5.getString("username"));
	            user.put("savedPosts", rs5.getInt("savedPosts"));
	            mostSavingUsers.add(user);
	        }
	        reports.put("mostSavingUsers", mostSavingUsers);

	        // Most liked post
	        String mostLikedPostSQL = "SELECT p.id, u.username, COUNT(pl.id) AS totalLikes FROM post_like pl " +
	                                  "JOIN post p ON pl.post_id = p.id JOIN user u ON p.user_id = u.id " +
	                                  "GROUP BY p.id, u.username ORDER BY totalLikes DESC LIMIT 1";
	        PreparedStatement ps6 = conn.prepareStatement(mostLikedPostSQL);
	        ResultSet rs6 = ps6.executeQuery();
	        if (rs6.next()) {
	            Map<String, Object> mostLikedPost = new HashMap<>();
	            mostLikedPost.put("postId", rs6.getInt("id"));
	            mostLikedPost.put("username", rs6.getString("username"));
	            mostLikedPost.put("totalLikes", rs6.getInt("totalLikes"));
	            reports.put("mostLikedPost", mostLikedPost);
	        }

	        // Most commented post
	        String mostCommentedPostSQL = "SELECT p.id, u.username, COUNT(c.id) AS totalComments FROM comment c " +
	                                      "JOIN post p ON c.post_id = p.id JOIN user u ON p.user_id = u.id " +
	                                      "GROUP BY p.id, u.username ORDER BY totalComments DESC LIMIT 1";
	        PreparedStatement ps7 = conn.prepareStatement(mostCommentedPostSQL);
	        ResultSet rs7 = ps7.executeQuery();
	        if (rs7.next()) {
	            Map<String, Object> mostCommentedPost = new HashMap<>();
	            mostCommentedPost.put("postId", rs7.getInt("id"));
	            mostCommentedPost.put("username", rs7.getString("username"));
	            mostCommentedPost.put("totalComments", rs7.getInt("totalComments"));
	            reports.put("mostCommentedPost", mostCommentedPost);
	        }
	    }

	    return reports;
	}

}