package com.picverse.admin.service;

import com.picverse.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminUserManagementService {

    public List<Map<String, Object>> getAllUsers() throws SQLException, ClassNotFoundException {
        List<Map<String, Object>> users = new ArrayList<>();

        String sql = "SELECT id, name, email FROM user";

        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> user = new HashMap<>();
                user.put("userId", rs.getInt("id"));
                user.put("username", rs.getString("name"));
                user.put("email", rs.getString("email"));
                users.add(user);
            }
        }

        return users;
    }

    public boolean deleteUserById(int userId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
