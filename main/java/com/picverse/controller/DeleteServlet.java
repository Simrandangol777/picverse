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

import com.picverse.config.DatabaseConfig;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try {
			Connection conn = DatabaseConfig.getDbConnection();

			// Delete comments
			String deleteCommentsSql = "DELETE FROM comment WHERE post_id=?";
			PreparedStatement deleteCommentsStmt = conn.prepareStatement(deleteCommentsSql);
			deleteCommentsStmt.setInt(1, id);
			deleteCommentsStmt.executeUpdate();
			deleteCommentsStmt.close();
			
			// Delete saved posts related to this post
			String deleteSavedPostsSql = "DELETE FROM saved_post WHERE post_id=?";
			PreparedStatement deleteSavedPostsStmt = conn.prepareStatement(deleteSavedPostsSql);
			deleteSavedPostsStmt.setInt(1, id);
			deleteSavedPostsStmt.executeUpdate();

			// Delete post_likes related to this post
			String deleteLikesSql = "DELETE FROM post_like WHERE post_id=?";
			PreparedStatement deleteLikesStmt = conn.prepareStatement(deleteLikesSql);
			deleteLikesStmt.setInt(1, id);
			deleteLikesStmt.executeUpdate();
			deleteLikesStmt.close();

			// Now delete post
			String deletePostSql = "DELETE FROM post WHERE id=?";
			PreparedStatement deletePostStmt = conn.prepareStatement(deletePostSql);
			deletePostStmt.setInt(1, id);
			deletePostStmt.executeUpdate();
			deletePostStmt.close();

			conn.close();

			response.sendRedirect("home");
		} catch (Exception e) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/error.jsp");
			rd.forward(request, response);
			e.printStackTrace();
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