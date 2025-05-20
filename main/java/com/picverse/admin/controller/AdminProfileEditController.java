package com.picverse.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * AdminProfileEditController
 *
 * This servlet handles the display of the edit admin profile form.
 * It forwards GET requests to the JSP page where the admin can update their profile.
 * Currently, POST requests are redirected to the same form view, awaiting further implementation.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/edit-admin-profile" })
public class AdminProfileEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor.
     */
    public AdminProfileEditController() {
        super();
    }

	
    /**
     * Handles GET requests by forwarding to the edit profile JSP.
     * This method is responsible for rendering the profile editing form for the admin.
     *
     * URL: /edit-admin-profile
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward request to the JSP page for editing admin profile
		request.getRequestDispatcher("/WEB-INF/pages/admin/editAdminProfile.jsp").forward(request, response);
	}

	
	/**
     * Handles POST requests. Currently redirects to doGet.
     * Intended for handling form submissions in future enhancements.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
