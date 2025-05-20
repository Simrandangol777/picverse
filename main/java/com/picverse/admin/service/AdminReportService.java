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
    public List<Map<String, Object>> getUserReports() throws Exception {
        List<Map<String, Object>> userReports = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getDbConnection()) {
            String sql = "SELECT u.username, " +
                         " (SELECT COUNT(*) FROM post p WHERE p.user_id = u.id) AS post_count, " +
                         " (SELECT COUNT(*) FROM comment c WHERE c.user_id = u.id) AS comment_count " +
                         " FROM user u";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("username", rs.getString("username"));
                userData.put("postCount", rs.getInt("post_count"));
                userData.put("commentCount", rs.getInt("comment_count"));

                userReports.add(userData);
            }
        }

        return userReports;
    }
}
