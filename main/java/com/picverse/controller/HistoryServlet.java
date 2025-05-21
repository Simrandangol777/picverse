package com.picverse.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.PostModel;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("HistoryServlet doGet called");

		int loggedInUserId = (int) request.getSession().getAttribute("userId");
		if (loggedInUserId == 0) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		System.out.println("Logged in user ID: " + loggedInUserId);

		ArrayList<PostModel> likedPosts = new ArrayList<>();

		String sql = "SELECT p.id, p.image " + "FROM post_like pl " + "JOIN post p ON pl.post_id = p.id "
				+ "WHERE pl.user_id = ?";

		try (Connection conn = DatabaseConfig.getDbConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, loggedInUserId);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					PostModel post = new PostModel();
					post.setId(rs.getInt("id"));
					post.setImage(rs.getString("image"));
					likedPosts.add(post);
				}
			}

			request.setAttribute("likedPosts", likedPosts);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/history.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
		}
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
