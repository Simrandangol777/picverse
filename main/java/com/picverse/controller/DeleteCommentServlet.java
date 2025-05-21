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

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/delete-comment")
public class DeleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		System.out.println("DeleteCommentServlet doPost called");

		int id = Integer.parseInt(request.getParameter("commentId"));
		int postId = Integer.parseInt(request.getParameter("postId"));
		
		try {
			Connection conn = DatabaseConfig.getDbConnection();
			String sql = "DELETE FROM comment WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();

			stmt.close();
			conn.close();

			System.out.println("Comment deleted successfully");
			int PostId = Integer.parseInt(request.getParameter("postId"));
			System.out.println(PostId);
			response.sendRedirect("view?id=" + postId);
//			response.sendRedirect("home");
		} catch (Exception e) {
			System.out.println("Error in DeleteCommentServlet");
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported");
			e.printStackTrace();
		}
	}



}
