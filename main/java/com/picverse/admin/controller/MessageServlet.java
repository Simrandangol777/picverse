package com.picverse.admin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.ContactModel;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/admin-message")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("MessageServlet doGet called");
		
		// Fetch user details from database
		System.out.println("Fetching user details from database");
		// Here you can add code to fetch user details from the database and set them in the request
		ArrayList<ContactModel> contactList = new ArrayList<>();
		
		try {
			Connection conn = DatabaseConfig.getDbConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM contact");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ContactModel contact = new ContactModel();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setPhoneNumber(rs.getLong("phone_number"));
				contact.setEmail(rs.getString("email"));
				contact.setSubject(rs.getString("subject"));
				contact.setMessage(rs.getString("message"));
				
				contactList.add(contact);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("contactList", contactList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/admin/message.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
