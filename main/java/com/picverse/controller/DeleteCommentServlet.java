package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.picverse.service.CommentService; // Import the new service

/**
 * Servlet implementation class DeleteCommentServlet
 */
@WebServlet("/delete-comment")

/*
 * This servlet handles the deletion of comments.
 * It retrieves the comment ID from the request, deletes the comment using the CommentService,
 * and redirects back to the post view page.
 */

public class DeleteCommentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentService commentService; // Declare the service

    public DeleteCommentServlet() {
        super();
        this.commentService = new CommentService(); // Initialize the service
    }

    /*
     * This method handles the HTTP POST request to delete a comment.	
     */
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("DeleteCommentServlet doPost called");

        // Basic session check for user ID 
        int loggedInUserId = (int) request.getSession().getAttribute("userId"); // Assuming userId is stored in session
        if (loggedInUserId == 0) {
            response.sendRedirect(request.getContextPath() + "/login"); // Redirect to login if not logged in
            return;	
        }

        int commentId = Integer.parseInt(request.getParameter("commentId")); // Get comment ID from request
        int postId = Integer.parseInt(request.getParameter("postId")); // Need this to redirect back

        try {
            // Delete comment using the service
            commentService.deleteComment(commentId);

            System.out.println("Comment deleted successfully (ID: " + commentId + ")");
            response.sendRedirect(request.getContextPath() + "/view?id=" + postId); // Redirect back to the post view page

        } catch (SQLException | ClassNotFoundException e) { 
            System.out.println("Error in DeleteCommentServlet: " + e.getMessage());
            e.printStackTrace(); // Log the error
            // Redirect with an error message or to a generic error page
            response.sendRedirect(request.getContextPath() + "/view?id=" + postId + "&error=deleteFailed");
        }
    }
}