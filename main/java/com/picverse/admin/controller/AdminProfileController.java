package com.picverse.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * AdminProfileController
 *
 * This servlet handles the display of the admin's profile page.
 * It processes GET requests to load the profile view and currently redirects POST requests to the same view.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-profile" })
public class AdminProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     */
    public AdminProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /**
     * Handles GET requests by forwarding the request to the admin profile JSP.
     * This method is responsible for displaying the admin's profile information.
     *
     * URL: /admin-profile
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Forward to the admin profile view
		request.getRequestDispatcher("/WEB-INF/pages/admin/profile.jsp").forward(request, response);
	}

	
	/**
     * Handles POST requests by delegating to doGet().
     * Can be extended in the future for handling profile-related form submissions or updates.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirect POST requests to the profile view (currently same as GET)
		doGet(request, response);
	}

}
