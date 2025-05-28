package com.picverse.controller;

import com.picverse.model.PostModel;
import com.picverse.model.UserModel;
import com.picverse.service.HomeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class HomeServlet asyncSupported = true enables
 * asynchronous processing for this servlet.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/", "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HomeService homeService;

	/**
	 * Initializes the servlet and creates an instance of HomeService.
	 */
	public void init() {
		homeService = new HomeService();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initializes the servlet and creates an instance of HomeService.
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response) doGet method handles GET requests to retrieve all posts from
	 *      the and database. homeService and forwards the request to the home.jsp
	 *      page.
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 */

	// TODO Auto-generated method stub
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get userId from session; if not logged in, set to -1
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if (userId == null) {
			userId = -1;
		}

		// Fetch posts based on userId (logged in or not)
		ArrayList<PostModel> posts = homeService.getAllPosts(userId);
		request.setAttribute("posts", posts);

		// Optionally fetch following list for footer if user logged in
		if (userId != -1) {
			ArrayList<UserModel> followingList = null;
			try {
				followingList = homeService.getFollowingUsers(userId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("followingList", followingList);
		}

		// Forward to home.jsp to render the page
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
