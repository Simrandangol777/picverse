package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

import com.picverse.service.CreateService;

/**
 * Servlet controller responsible for handling image upload requests from the
 * user and delegating processing to the CreateService class.
 */
@WebServlet("/create")
@MultipartConfig
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CreateService createService;

	/**
	 * Initializes the CreateService instance when the servlet starts.
	 */
	@Override
	public void init() throws ServletException {
		createService = new CreateService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/create.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to upload an image with caption and save the post to
	 * the database.
	 *
	 * @param request  The HttpServletRequest object that contains client request
	 *                 data.
	 * @param response The HttpServletResponse object used to return the response.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieve userId from session
		Integer userId = (Integer) request.getSession().getAttribute("userId");

		if (userId == null) {
			// Redirect to login page if user is not authenticated
			response.sendRedirect("login.jsp");
			return;
		}

		// Extract form data
		String caption = request.getParameter("caption");
		Part imagePart = request.getPart("image");

		try {
			// Delegate post saving logic to the service class
			boolean isSaved = createService.savePostWithImage(caption, imagePart, userId, getServletContext());

			if (isSaved) {
				// Redirect to homepage if successful
				response.sendRedirect("home");
			} else {
				// Inform user if saving failed
				response.getWriter().write("Failed to save the post.");
			}

		} catch (Exception e) {
			// Log and report any errors to the user
			e.printStackTrace();
			response.getWriter().write("Error while processing post: " + e.getMessage());
		}
	}
}