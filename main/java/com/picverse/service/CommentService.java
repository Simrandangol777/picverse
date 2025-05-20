package com.picverse.service;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.CommentModel;
import com.picverse.model.PostModel; // You might need PostModel if you decide to fetch comments with post details

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

    /**
     * Fetches all comments for a given post ID.
     * @param postId The ID of the post.
     * @return A list of CommentModel objects.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     */
    public List<CommentModel> getCommentsByPostId(int postId) throws SQLException, ClassNotFoundException {
        List<CommentModel> comments = new ArrayList<>();
        String sqlComments = "SELECT c.id, c.comment, c.post_id, c.user_id AS comment_user_id, u.username AS comment_user_name, u.profile_picture AS comment_profile_picture "
                + "FROM comment c JOIN user u ON c.user_id = u.id "
                + "WHERE c.post_id = ?";

        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmtComments = conn.prepareStatement(sqlComments)) {

            stmtComments.setInt(1, postId);
            try (ResultSet rsComments = stmtComments.executeQuery()) {
                while (rsComments.next()) {
                    CommentModel comment = new CommentModel(
                            rsComments.getInt("id"),
                            rsComments.getString("comment"),
                            rsComments.getInt("post_id"),
                            String.valueOf(rsComments.getInt("comment_user_id")), // Ensure correct type casting if user_id is int
                            rsComments.getString("comment_user_name"),
                            rsComments.getString("comment_profile_picture")
                    );
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    /**
     * Adds a new comment to a post.
     * @param postId The ID of the post the comment belongs to.
     * @param userId The ID of the user making the comment.
     * @param commentText The text content of the comment.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     */
    public void addComment(int postId, int userId, String commentText) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO comment (comment, post_id, user_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, commentText);
            stmt.setInt(2, postId);
            stmt.setInt(3, userId);
            stmt.executeUpdate();
        }
    }

    /**
     * Deletes a comment by its ID.
     * @param commentId The ID of the comment to delete.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the JDBC driver is not found.
     */
    public void deleteComment(int commentId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM comment WHERE id=?";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, commentId);
            stmt.executeUpdate();
        }
    }
}