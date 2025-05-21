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

			// Fetch comments for the post
			String sqlComments = "SELECT c.id, c.comment, c.post_id, c.user_id AS comment_user_id, u.username AS comment_user_name, u.profile_picture AS comment_profile_picture "
					+ "FROM comment c JOIN user u ON c.user_id = u.id " + "WHERE c.post_id = ?"; // This WHERE clause is
																									// essential
			PreparedStatement stmtComments = conn.prepareStatement(sqlComments);
			stmtComments.setInt(1, postId); // Setting the post ID parameter
			ResultSet rsComments = stmtComments.executeQuery();
			ArrayList<CommentModel> comments = new ArrayList<>();

			while (rsComments.next()) {
				CommentModel comment = new CommentModel(rsComments.getInt("id"), rsComments.getString("comment"),
						rsComments.getInt("post_id"), rsComments.getString("comment_user_id"),
						rsComments.getString("comment_user_name"), rsComments.getString("comment_profile_picture"));
				comments.add(comment);
			}

			if (post != null) {
				String saveCheckSql = "SELECT is_saved FROM saved_post WHERE post_id = ? AND user_id = ? LIMIT 1";
				PreparedStatement saveCheckStmt = conn.prepareStatement(saveCheckSql);
				saveCheckStmt.setInt(1, post.getId());
				saveCheckStmt.setInt(2, loggedInUserId);
				ResultSet saveCheckRs = saveCheckStmt.executeQuery();

				if (saveCheckRs.next()) {
					post.setSaved(saveCheckRs.getBoolean("is_saved")); 
				} else {
					post.setSaved(false);
				}
			}

//			System.out.println("Post ID: " + postId);
//			System.out.println("Post: " + post);
//			System.out.println("Comments: " + comments);

			// Set post attribute to the request
			request.setAttribute("post", post);
			request.setAttribute("comments", comments);
			request.setAttribute("loggedInUserId", loggedInUserId);

			// Forward to view.jsp to display the post
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/view.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace(); // Log the error
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get parameters
		int loggedInUserId = (int) request.getSession().getAttribute("userId");
		if (loggedInUserId == 0) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		System.out.println("ViewServlet doPost called");
		int postId = Integer.parseInt(request.getParameter("postId"));
		String commentText = request.getParameter("comment"); // Assuming user is logged in

		try {
			Connection conn = DatabaseConfig.getDbConnection();
			// Insert comment
			String sql = "INSERT INTO comment (comment, post_id, user_id) VALUES (?, ?, ?)";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, commentText);
				stmt.setInt(2, postId);
				stmt.setInt(3, loggedInUserId);
				stmt.executeUpdate();
			}

			// Redirect back to view page
			response.sendRedirect(request.getContextPath() + "/view?id=" + postId);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error inserting comment: " + e.getMessage());
			response.sendRedirect(request.getContextPath() + "/view?id=" + postId);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
