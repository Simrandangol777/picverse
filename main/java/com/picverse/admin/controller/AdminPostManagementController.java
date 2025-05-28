package com.picverse.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.picverse.admin.service.AdminPostManagementService;

/**
 * AdminPostManagementController
 *
 * This servlet handles post management operations in the admin panel.
 * Supported features:
 * - Viewing all posts
 * - Deleting a specific post by post ID
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-postmanagement" })
public class AdminPostManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Service layer to handle business logic related to post management
    private AdminPostManagementService postService;
    
    /**
     * Initializes the servlet and the post management service.
     */
    @Override
    public void init() throws ServletException {
        postService = new AdminPostManagementService();
    }

    
    /**
     * Handles GET requests to display all posts.
     * Retrieves a list of posts from the service layer and forwards it to the JSP page.
     *
     * URL: /admin-postmanagement
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// Fetch all posts from the service
        List<Map<String, Object>> posts = postService.getAllPosts();
       
        // Set posts as a request attribute for the JSP to display
        request.setAttribute("posts", posts);

        // Forward the request to the post management JSP page
        request.getRequestDispatcher("/WEB-INF/pages/admin/postManagement.jsp").forward(request, response);
    }

    
    /**
     * Handles POST requests for post actions (e.g., deletion).
     * If the action is "delete", it deletes the post by ID and redirects back to the management page.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	// Retrieve the action parameter (e.g., delete)
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
        	
        	// Get the ID of the post to be deleted
            int postId = Integer.parseInt(request.getParameter("post_id"));
            
            // Call the service to delete the post
            postService.deletePostById(postId);
            
            // Redirect back to the post management page to reflect changes
            response.sendRedirect(request.getContextPath() + "/admin-postmanagement");
        }
    }
}
