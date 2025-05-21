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
import java.util.ArrayList;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login");
			return;
		}

		try (Connection conn = DatabaseConfig.getDbConnection()) {
			int loggedInUserId = (int) session.getAttribute("userId");
			String targetUsername = request.getParameter("username");

			int userIdToFetch;
			String username, profilePicture;

			if (targetUsername != null && !targetUsername.trim().isEmpty()) {
				String sql = "SELECT id, username, profile_picture FROM user WHERE username = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, targetUsername);
				ResultSet rs = stmt.executeQuery();

				if (!rs.next()) {
					response.sendRedirect("not-found.jsp");
					return;
				}

				userIdToFetch = rs.getInt("id");
				username = rs.getString("username");
				profilePicture = rs.getString("profile_picture");
			} else {
				userIdToFetch = loggedInUserId;
				String sql = "SELECT username, profile_picture FROM user WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, userIdToFetch);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				username = rs.getString("username");
				profilePicture = rs.getString("profile_picture");
			}

			if (profilePicture == null || profilePicture.trim().isEmpty()) {
				profilePicture = "logo.png";
			}

			request.setAttribute("username", username);
			request.setAttribute("profilePicture", profilePicture);
			request.setAttribute("isOwnProfile", userIdToFetch == loggedInUserId);

			// 📌 Fetch user's posts
			String postSql = "SELECT id, image FROM post WHERE user_id = ?";
			PreparedStatement postStmt = conn.prepareStatement(postSql);
			postStmt.setInt(1, userIdToFetch);
			ResultSet postRs = postStmt.executeQuery();

			ArrayList<PostModel> posts = new ArrayList<>();
			while (postRs.next()) {
				PostModel post = new PostModel();
				post.setId(postRs.getInt("id"));
				post.setImage(postRs.getString("image"));
				posts.add(post);
			}
			request.setAttribute("posts", posts);

			boolean isFollowing = false;
			if (userIdToFetch != loggedInUserId) {
				String followSql = "SELECT 1 FROM follow WHERE follower_id = ? AND following_id = ?";
				PreparedStatement followStmt = conn.prepareStatement(followSql);
				followStmt.setInt(1, loggedInUserId);
				followStmt.setInt(2, userIdToFetch);
				ResultSet followRs = followStmt.executeQuery();
				isFollowing = followRs.next();
			}

			request.setAttribute("isFollowing", isFollowing);
			request.setAttribute("profileUserId", userIdToFetch);

			// Follower Count
			String followerCountSql = "SELECT COUNT(*) AS count FROM follow WHERE following_id = ?";
			PreparedStatement followerStmt = conn.prepareStatement(followerCountSql);
			followerStmt.setInt(1, userIdToFetch);
			ResultSet followerRs = followerStmt.executeQuery();
			int followerCount = 0;
			if (followerRs.next()) {
				followerCount = followerRs.getInt("count");
			}
			request.setAttribute("followerCount", followerCount);

			// Following Count
			String followingCountSql = "SELECT COUNT(*) AS count FROM follow WHERE follower_id = ?";
			PreparedStatement followingStmt = conn.prepareStatement(followingCountSql);
			followingStmt.setInt(1, userIdToFetch);
			ResultSet followingRs = followingStmt.executeQuery();
			int followingCount = 0;
			if (followingRs.next()) {
				followingCount = followingRs.getInt("count");
			}
			request.setAttribute("followingCount", followingCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
