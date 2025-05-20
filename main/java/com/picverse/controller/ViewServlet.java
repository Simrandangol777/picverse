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
import com.picverse.model.CommentModel;
import com.picverse.model.PostModel;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("ViewServlet doGet called");
			// Get post ID from request parameter
			int loggedInUserId = (int) request.getSession().getAttribute("userId");
			if (loggedInUserId == 0) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
			int postId = Integer.parseInt(request.getParameter("id"));

			System.out.println("Logged in user ID: " + loggedInUserId);

			// Initialize post variable
			PostModel post = null;

			// Fetch post details from database
			Connection conn = DatabaseConfig.getDbConnection();
			String sql = "SELECT p.id, p.caption, p.image, u.username, u.profile_picture, p.user_id "
					+ "FROM post p JOIN user u ON p.user_id = u.id " + "WHERE p.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				post = new PostModel(rs.getInt("id"), rs.getString("caption"), rs.getString("image"),
						rs.getString("username"), rs.getString("profile_picture"), rs.getInt("user_id"));
			}

			
			request.setAttribute("post", post);
			request.setAttribute("loggedInUserId", loggedInUserId);

			// Forward to view.jsp to display the post
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/view.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(); // Log the error
		}
	}

	

}
