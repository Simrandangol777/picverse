package com.picverse.service;

import com.picverse.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Service class responsible for deleting a post from the database.
 */
public class DeleteService {

    /**
     * Deletes a post record from the database based on its ID.
     *
     * @param postId The ID of the post to be deleted.
     * @return true if deletion was successful; false otherwise.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException 
     */
    public boolean deletePostById(int postId) throws SQLException, ClassNotFoundException {
        String deleteQuery = "DELETE FROM post WHERE id=?";

        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setInt(1, postId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
