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
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 10 // 10MB
)
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
	 *      response)
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
				String phoneNumber = rs.getString("phone_number");
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
				UserModel user = new UserModel(name, username, email, phoneNumber, location, hobby, bio, profilePicture);
				request.setAttribute("user", user);

				System.out.println("User details fetched successfully");
				System.out.println("Name: " + name);
				System.out.println("Username: " + username);
				
				
				System.out.println("Email: " + email);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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
			String phoneNumber = request.getParameter("phone");
			String location = request.getParameter("location");
			String hobby = request.getParameter("website");
			String bio = request.getParameter("bio");

			System.out.println("name: " + name);
			System.out.println("username" + username);
			System.out.println("email" + email);
			System.out.println("phoneNumber" + phoneNumber);
			System.out.println("location" + location);
			System.out.println("hobby" + hobby);
			System.out.println("bio" + bio);


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
				String sql = "UPDATE user SET name=?, username=?, email=?, phone_number=?, location=?, hobby=?, bio=?, profile_picture=? WHERE id=?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, username);
				stmt.setString(3, email);
				stmt.setString(4, phoneNumber);
				stmt.setString(5, location);
				stmt.setString(6, hobby);
				stmt.setString(7, bio);
				stmt.setString(8, imageName);
				stmt.setInt(9, userId);

				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("User profile updated successfully");
					response.sendRedirect("edit-profile");
				} else {
					System.out.println("Failed to update user profile");
					response.sendRedirect("edit-profile");
				}

				stmt.close();
				conn.close();
//				response.sendRedirect("edit-profile");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/editProfile.jsp");
				rd.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Session is null");
			response.sendRedirect("login");
		}
	}

}
