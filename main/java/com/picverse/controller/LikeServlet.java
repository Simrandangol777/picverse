package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picverse.config.DatabaseConfig;

/**
 * Servlet implementation class LikeServlet
 */
//@WebServlet("/likepost")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LikeServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int postId = Integer.parseInt(request.getParameter("postId"));
		int userId = -1;
		HttpSession session = request.getSession(false);
		if (session != null) {
			userId = (int) request.getSession().getAttribute("userId");
		}
		
		System.out.println("Post ID: " + userId);
		
		if (userId == -1) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		boolean liked = false;
		int likeCount = 0;

		try (Connection conn = DatabaseConfig.getDbConnection()) {
			// Check if user already liked the post
			PreparedStatement psCheck = conn.prepareStatement("SELECT * FROM post_like WHERE user_id=? AND post_id=?");
			psCheck.setInt(1, userId);
			psCheck.setInt(2, postId);
			ResultSet rs = psCheck.executeQuery();

			if (rs.next()) {
				// User already liked, so remove like
				PreparedStatement psDelete = conn
						.prepareStatement("DELETE FROM post_like WHERE user_id=? AND post_id=?");
				psDelete.setInt(1, userId);
				psDelete.setInt(2, postId);
				psDelete.executeUpdate();
				liked = false;
				psDelete.close();
			} else {
				// Insert new like
				PreparedStatement psInsert = conn
						.prepareStatement("INSERT INTO post_like(user_id, post_id, is_liked) VALUES (?, ?, ?)");
				psInsert.setInt(1, userId);
				psInsert.setInt(2, postId);
				psInsert.setBoolean(3, true);
				psInsert.executeUpdate();
				liked = true;
				psInsert.close();
			}

			rs.close();
			psCheck.close();

			// Count total likes for the post
			PreparedStatement psCount = conn.prepareStatement("SELECT COUNT(*) FROM post_like WHERE post_id=?");
			psCount.setInt(1, postId);
			ResultSet rsCount = psCount.executeQuery();

			if (rsCount.next()) {
				likeCount = rsCount.getInt(1);
			}
			rsCount.close();
			psCount.close();

			response.setContentType("application/json");
			response.getWriter().write("{\"liked\":" + liked + ",\"count\":" + likeCount + "}");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error");
		}
	}

}
