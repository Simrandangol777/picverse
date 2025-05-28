package com.picverse.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.picverse.config.DatabaseConfig;
import com.picverse.model.UserModel;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String query = request.getParameter("query");
        ArrayList<UserModel> users = new ArrayList<>();

        if (query != null && !query.trim().isEmpty()) {
            try (Connection conn = DatabaseConfig.getDbConnection()) {
                String sql = "SELECT username FROM user WHERE username LIKE ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "%" + query + "%");
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    UserModel user = new UserModel();
                    user.setUsername(rs.getString("username"));
//                    user.setProfilePicture(rs.getString("profile_picture"));
                    users.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("users", users);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/home.jsp");
		rd.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
