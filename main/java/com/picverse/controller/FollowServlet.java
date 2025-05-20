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
import java.sql.SQLException;
import java.util.ArrayList;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.UserModel;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FollowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			response.sendRedirect("login");
			return;
		}

		ArrayList<UserModel> followers = new ArrayList<>();
		ArrayList<UserModel> following = new ArrayList<>();
		ArrayList<UserModel> suggestions = new ArrayList<>();

		try (Connection conn = DatabaseConfig.getDbConnection()) {
			// Get FOLLOWING
			String sql1 = "SELECT u.id, u.username, u.profile_picture FROM user u INNER JOIN follow f ON u.id = f.following_id WHERE f.follower_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql1)) {
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					following.add(
							new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("profile_picture")));
				}
			}

			// Get FOLLOWERS
			String sql2 = "SELECT u.id, u.username, u.profile_picture FROM user u INNER JOIN follow f ON u.id = f.follower_id WHERE f.following_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
				stmt.setInt(1, userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					followers.add(
							new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("profile_picture")));
				}
			}

			// 🔽 Get SUGGESTED USERS
			String sql3 = "SELECT id, username, profile_picture FROM user " + "WHERE id != ? "
					+ "AND id NOT IN (SELECT following_id FROM follow WHERE follower_id = ?) "
					+ "AND id NOT IN (SELECT follower_id FROM follow WHERE following_id = ?) " + "LIMIT 20";
			try (PreparedStatement stmt = conn.prepareStatement(sql3)) {
				stmt.setInt(1, userId);
				stmt.setInt(2, userId);
				stmt.setInt(3, userId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					suggestions.add(
							new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("profile_picture")));
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("followingList", following);
		request.setAttribute("followersList", followers);
		request.setAttribute("suggestedUsers", suggestions); 
		request.getRequestDispatcher("WEB-INF/pages/friends.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int followerId = (int) request.getSession().getAttribute("userId");
		int followingId = Integer.parseInt(request.getParameter("followingId"));
		String action = request.getParameter("action");

		System.out.println("Follower ID: " + followerId);
		System.out.println("Following ID: " + followingId);
		System.out.println("Action: " + action);

		try (Connection conn = DatabaseConfig.getDbConnection()) {
			if ("follow".equals(action)) {
				String sql = "INSERT INTO follow (follower_id, following_id) VALUES (?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, followerId);
				ps.setInt(2, followingId);
				ps.executeUpdate();
			} else if ("unfollow".equals(action)) {
				String sql = "DELETE FROM follow WHERE follower_id = ? AND following_id = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, followerId);
				ps.setInt(2, followingId);
				ps.executeUpdate();
			}

			response.sendRedirect("profile?username=" + request.getParameter("username"));

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error");
		}
	}

}
