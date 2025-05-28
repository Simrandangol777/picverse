package com.picverse.service;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SavePostService {

    public ArrayList<PostModel> getSavedPosts(int userId) throws Exception {
        ArrayList<PostModel> savedPosts = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "SELECT p.id, p.image FROM post p " +
                         "JOIN saved_post ps ON p.id = ps.post_id " +
                         "WHERE ps.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PostModel post = new PostModel();
                post.setId(rs.getInt("id"));
                post.setImage(rs.getString("image"));
                savedPosts.add(post);
            }

            rs.close();
            stmt.close();
        }

        return savedPosts;
    }

    public void toggleSavedPost(int userId, int postId) throws Exception {
        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String checkSql = "SELECT * FROM saved_post WHERE post_id = ? AND user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, postId);
            checkStmt.setInt(2, userId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Unsave
                String deleteSql = "DELETE FROM saved_post WHERE post_id = ? AND user_id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setInt(1, postId);
                deleteStmt.setInt(2, userId);
                deleteStmt.executeUpdate();
                deleteStmt.close();
            } else {
                // Save
                String insertSql = "INSERT INTO saved_post (post_id, user_id, is_saved) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, postId);
                insertStmt.setInt(2, userId);
                insertStmt.setBoolean(3, true);
                insertStmt.executeUpdate();
                insertStmt.close();
            }

            rs.close();
            checkStmt.close();
        }
    }
}
