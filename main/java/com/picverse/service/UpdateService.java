package com.picverse.service;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Service class responsible for retrieving and updating post data.
 */
public class UpdateService {

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return PostModel if found, otherwise null.
     * @throws Exception if database access fails.
     */
    public PostModel getPostById(int postId) throws Exception {
        String sql = "SELECT * FROM post WHERE id = ?";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new PostModel(
                        rs.getInt("id"),
                        rs.getString("caption"),
                        rs.getString("image")
                );
            }

            return null;
        }
    }

    /**
     * Checks if a given user is the owner of a post.
     *
     * @param postId The ID of the post.
     * @param userId The ID of the user.
     * @return true if the user is the post owner.
     * @throws Exception if database access fails.
     */
    public boolean isPostOwner(int postId, int userId) throws Exception {
        String sql = "SELECT user_id FROM post WHERE id = ?";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, postId);
            ResultSet rs = stmt.executeQuery();

            return rs.next() && rs.getInt("user_id") == userId;
        }
    }

    /**
     * Updates the caption of a post.
     *
     * @param postId  The ID of the post.
     * @param caption The new caption text.
     * @throws Exception if update fails.
     */
    public void updateCaption(int postId, String caption) throws Exception {
        String sql = "UPDATE post SET caption = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, caption);
            stmt.setInt(2, postId);
            stmt.executeUpdate();
        }
    }
}
