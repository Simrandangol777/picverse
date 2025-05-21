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
	 * This method handles the like/unlike functionality for posts.
	 * It checks if the user is logged in, and if so, it toggles the like status for the specified post.
	 * It updates the database accordingly and returns the updated like status and count in JSON format.
	 * If the user is not logged in, it returns an unauthorized status.
	 * If an error occurs during the process, it returns an internal server error status.
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws NumberFormatException
	 * @throws Exception
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));

		try {
			Integer loggedInUserId = (Integer) request.getSession().getAttribute("userId");
			if (loggedInUserId == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			/*
			 * Check if the user has already liked the post
			 * If the user has liked the post, remove the like
			 * If the user has not liked the post, add a like
			 */
			Connection conn = DatabaseConfig.getDbConnection();

			String sql = "SELECT * FROM post_like WHERE post_id = ? AND user_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postId);
			stmt.setInt(2, loggedInUserId);

			ResultSet rs = stmt.executeQuery();

			boolean liked;
			if (rs.next()) {
				/*
				 * Unlike the post
				 * If the user has already liked the post, remove the like
				 * This is done by deleting the record from the post_like table
				 */
				String deleteSql = "DELETE FROM post_like WHERE post_id = ? AND user_id = ?";
				PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
				deleteStmt.setInt(1, postId);
				deleteStmt.setInt(2, loggedInUserId);
				deleteStmt.executeUpdate();
				deleteStmt.close();
				liked = false;
			} else {
				/* 
				 * Like the post
				 * If the user has not liked the post, add a like
				 * This is done by inserting a record into the post_like table
				 */
				String insertSql = "INSERT INTO post_like (post_id, user_id, is_liked) VALUES (?, ?, ?)";
				PreparedStatement insertStmt = conn.prepareStatement(insertSql);
				insertStmt.setInt(1, postId);
				insertStmt.setInt(2, loggedInUserId);
				insertStmt.setBoolean(3, true);
				insertStmt.executeUpdate();
				insertStmt.close();
				liked = true;
			}

			rs.close();
			stmt.close();

			/*
			 * Get the total number of likes for the post
			 * This is done by counting the number of records in the post_like table for the given post_id
			 */
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

			// Respond with JSON
			response.setContentType("application/json");
			response.getWriter().write("{\"liked\":" + liked + ",\"likeCount\":" + likeCount + "}");

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
