package com.picverse.admin.service;

import com.picverse.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardService {

    public int getTotalUsers() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM user";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public int getTotalPosts() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM post";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    public int getTotalLikes() throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(*) FROM post_like";
        try (Connection conn = DatabaseConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }
}
