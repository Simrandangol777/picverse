package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.picverse.model.UserModel;
import com.picverse.service.EditProfileService;

/**
 * EditProfileServlet handles the editing of user profiles. It manages the
 * retrieval and updating of user information.
 * 
 * @multipartConfig is used to handle file uploads. It specifies the file size
 *                  limits for the uploaded files.
 * @webServlet is used to define the URL pattern for this servlet. It supports
 *             asynchronous requests. This servlet is responsible for displaying
 *             the edit profile page and processing the form submission for
 *             updating user information.
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet(asyncSupported = true, urlPatterns = { "/edit-profile" })
public class EditProfileServlet extends HttpServlet {
	// Generated serialVersionUID for serialization
	private static final long serialVersionUID = 1L;
	private final EditProfileService service = new EditProfileService();

	/**
	 * @see HttpServlet#HttpServlet()
	 * doGet method retrieves the user profile information and displays the edit
	 * profile page. It checks if the user is logged in by verifying the session.
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login");
			return;
		}

		try {
			// Retrieve userId from session and get user details
			int userId = (Integer) session.getAttribute("userId");
			UserModel user = service.getUserById(userId);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
	}
	
	/**
	 * doPost method processes the form submission for updating user profile
	 * information. It checks if the user is logged in by verifying the session.
	 * 
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if an error occurs during the request processing
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check if the user is logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Retrieve details from the request
		int userId = (Integer) session.getAttribute("userId");
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phoneStr = request.getParameter("phone");
		Long phone = (phoneStr != null && !phoneStr.isEmpty()) ? Long.parseLong(phoneStr) : null;
		String location = request.getParameter("location");
		String hobby = request.getParameter("website");
		String bio = request.getParameter("bio");
		String currentImage = request.getParameter("currentImage");
		String imageName = currentImage != null ? currentImage : "logo.png";

		Part file = request.getPart("profilePicture");

		// Validate the input
		try {
			if (file != null && file.getSize() > 0) {
				imageName = service.saveProfilePicture(file, getServletContext());
			}

			boolean updated = service.updateUserProfile(userId, name, phone, location, hobby, bio, imageName);
			if (updated) {
				response.sendRedirect("edit-profile");
			} else {
				request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
	}
}