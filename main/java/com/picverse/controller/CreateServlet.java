package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.picverse.config.DatabaseConfig;

/**
 * Servlet implementation class CreateServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 10)
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    System.out.println("AddImage servlet called");
	    
	    Integer userId = (Integer) request.getSession().getAttribute("userId");
	    System.out.println("User ID from session: " + userId);
	    
	    // Get caption from form
	    String caption = request.getParameter("caption");
	    
	    // Get image file
	    
	    Part file = request.getPart("image");
	    String imageName = file.getSubmittedFileName();
	    System.out.println("File name: " + imageName);
	    System.out.println("Caption: " + caption);
	    
	 // To give a abosolute path so that image save in the upload image folder.
	 		// Give absolute path like "C:Advance Java/picverse/src/main/webapp/uploads/images/" of the project in the blank area
//	 		String uploadPath = "" + imageName;
//	    String uploadPath = "E:/20th/Advance Java/Restart/picverse/src/main/webapp/uploads/images/" + imageName;
	    String uploadPath = getServletContext().getRealPath("uploads/images/") + imageName;
	    System.out.println("Upload path: " + uploadPath);
	    
	    // Uploading the file to server
	    try {
	        FileOutputStream fos = new FileOutputStream(uploadPath);
	        InputStream is = file.getInputStream();
	        
	        byte[] data = new byte[is.available()];
	        is.read(data);
	        fos.write(data);
	        fos.close();
	        
	        System.out.println("File uploaded successfully");
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
	        return; // Return early if file upload fails
	    }
	    
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("MySQL driver loaded successfully in servlet context!");
	    } catch (ClassNotFoundException e) {
	        System.err.println("MySQL driver not found in servlet context: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    try {
	        // Get database connection
	        connection = DatabaseConfig.getDbConnection();
	        
	        // Modified query to include caption
	        String query = "INSERT INTO post (caption, image, user_id) VALUES (?, ?, ?)";
	        
	        // Prepare the SQL statement
	        stmt = connection.prepareStatement(query);
	        
	        // Set the caption and image filename
	        stmt.setString(1, caption);
	        stmt.setString(2, imageName);
	        stmt.setInt(3, userId);
	        
	        // Execute the query
	        int rows = stmt.executeUpdate();
	        
	        if (rows > 0) {
	            System.out.println("Post saved to database successfully");
//	            response.getWriter().write("Upload successful: " + imageName);
	            response.sendRedirect("home");
	        } else {
	            System.out.println("Failed to save post to database");
	            response.getWriter().write("Database save failed");
	        }
	    } catch (SQLException e) {
	        // Print SQL-specific errors
	        e.printStackTrace();
	        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
	    } catch (Exception e) {
	        // Handle any other types of exceptions
	        e.printStackTrace();
	        request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
	    } finally {
	        try {
	            // Close resources
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            // Handle closing errors
	        	request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
	            e.printStackTrace();
	        }
	    }
	}

}
