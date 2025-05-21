package com.picverse.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.UserModel;

/**
 * Servlet implementation class EditProfileServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) Fetches user details from the database and forwards to
	 *      editProfile.jsp Checks if the user is logged in by checking the session
	 *      If the user is logged in, fetches user details from the database If the
	 *      user is not logged in, redirects to login page Send the user details to
	 *      the editProfile.jsp page
	 * @param request  the servlet request
	 * @param response the servlet response
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Fetch user details from database
		System.out.println("EditProfileServlet doGet called");

		HttpSession session = request.getSession(false);
		if (session != null) {
			Integer userId = (Integer) session.getAttribute("userId");

			System.out.println("User ID from session: " + userId);
		}

		// Fetching user details from database.
		try {
			Integer userId = (Integer) session.getAttribute("userId");

			System.out.println("User ID from session: " + userId);
			Connection conn = DatabaseConfig.getDbConnection();
			String sql = "SELECT * FROM user WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String username = rs.getString("username");
				String email = rs.getString("email");
				Long phoneNumber = rs.getLong("phone_number");
				String location = rs.getString("location");
				String hobby = rs.getString("hobby");
				String bio = rs.getString("bio");
				String profilePicture = rs.getString("profile_picture");

				if (profilePicture == null || profilePicture.isEmpty()) {
					profilePicture = "logo.png";
					System.out.println("profilePicture is empty, setting to logo.png");
				} else {
					System.out.println("profilePicture: " + profilePicture);
				}

				// Set user details as request attributes
				UserModel user = new UserModel(name, username, email, phoneNumber, location, hobby, bio,
						profilePicture);
				request.setAttribute("user", user);

				System.out.println("User details fetched successfully");
				System.out.println("Name: " + name);
				System.out.println("Username: " + username);

				System.out.println("Email: " + email);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}

		request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) Handles the form submission for editing user profile Checks if
	 *      the user is logged in by checking the session If the user is logged in,
	 *      updates the user details in the database If the user is not logged in,
	 *      redirects to login page
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get user ID from session
		System.out.println("EditProfileServlet doPost called");
		HttpSession session = request.getSession(false);
		if (session != null) {
			Integer userId = (Integer) session.getAttribute("userId");

			System.out.println("User ID from session: " + userId);

			// Get updated user details from request parameters
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String email = request.getParameter("email");

			String phoneNumberStr = request.getParameter("phone");
			Long phoneNumber = null; // Initialize to null

			// Check if the string is not null and not empty before trying to parse
			if (phoneNumberStr != null && !phoneNumberStr.trim().isEmpty()) {
				phoneNumber = Long.parseLong(phoneNumberStr);
			}

			String location = request.getParameter("location");
			String hobby = request.getParameter("website");
			String bio = request.getParameter("bio");

			System.out.println("name: " + name);
			System.out.println("username" + username);
			System.out.println("email" + email);
			System.out.println("phoneNumber" + phoneNumber);

			Part file = request.getPart("profilePicture");
			String imageName = null;

			// Get the current image from the hidden input
			String currentImage = request.getParameter("currentImage");

			if (file != null && file.getSize() > 0) {
				imageName = file.getSubmittedFileName();
				System.out.println("New file name: " + imageName);

				String uploadPath = getServletContext().getRealPath("uploads/images/") + imageName;
				System.out.println("Upload path: " + uploadPath);

				try (FileOutputStream fos = new FileOutputStream(uploadPath); InputStream is = file.getInputStream()) {
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					System.out.println("File uploaded successfully");
				} catch (IOException e) {
					e.printStackTrace();
					response.getWriter().write("Upload failed: " + e.getMessage());
					return;
				}
			} else {
				// Use the current image if available, else use default
				imageName = (currentImage != null && !currentImage.trim().isEmpty()) ? currentImage : "logo.png";
				System.out.println("Using existing/default image: " + imageName);
			}

			try {
				Connection conn = DatabaseConfig.getDbConnection();
				String sql = "UPDATE user SET name=?, phone_number=?, location=?, hobby=?, bio=?, profile_picture=? WHERE id=?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				if (phoneNumber != null) {
					stmt.setLong(2, phoneNumber);
				} else {
					stmt.setNull(2, java.sql.Types.BIGINT);
				}
				stmt.setString(3, location);
				stmt.setString(4, hobby);
				stmt.setString(5, bio);
				stmt.setString(6, imageName);
				stmt.setInt(7, userId);

				int rowsUpdated = stmt.executeUpdate();
				stmt.close();
				conn.close();
				if (rowsUpdated > 0) {
					System.out.println("User profile updated successfully");
					response.sendRedirect("edit-profile");
					return;
				} else {
					System.out.println("Failed to update user profile");
					response.sendRedirect("edit-profile");
					return;
				}
//				response.sendRedirect("edit-profile");
//				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp");
//				rd.forward(request, response);

			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
				e.printStackTrace();
			}
		} else {
			System.out.println("Session is null");
			request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
		}
	}

}
