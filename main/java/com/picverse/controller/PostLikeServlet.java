package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.picverse.config.DatabaseConfig;

/**
 * Servlet implementation class PostLikeServlet
 */
@WebServlet("/likepost")
public class PostLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostLikeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Like post servlet called");

	    int postId = Integer.parseInt(request.getParameter("postId"));

	    try {
	        Integer loggedInUserId = (Integer) request.getSession().getAttribute("userId");
	        if (loggedInUserId == null) {
	            response.sendRedirect("login");
	            return;
	        }

	        Connection conn = DatabaseConfig.getDbConnection();

	        // Check if the user has already liked the post
	        String sql = "SELECT * FROM post_like WHERE post_id = ? AND user_id = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, postId);
	        stmt.setInt(2, loggedInUserId);

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            // Unlike
	            String deleteSql = "DELETE FROM post_like WHERE post_id = ? AND user_id = ?";
	            PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
	            deleteStmt.setInt(1, postId);
	            deleteStmt.setInt(2, loggedInUserId);
	            deleteStmt.executeUpdate();
	            deleteStmt.close();
	        } else {
	            // Like
	            String insertSql = "INSERT INTO post_like (post_id, user_id, is_liked) VALUES (?, ?, ?)";
	            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
	            insertStmt.setInt(1, postId);
	            insertStmt.setInt(2, loggedInUserId);
	            insertStmt.setBoolean(3, true);
	            insertStmt.executeUpdate();
	            insertStmt.close();
	        }

	        rs.close();
	        stmt.close();
	        conn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // Redirect to controller that loads all posts with like status
	    response.sendRedirect("home");
	}


}
