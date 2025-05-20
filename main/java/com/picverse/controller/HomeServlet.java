package com.picverse.controller;

import com.picverse.model.PostModel;
import com.picverse.service.HomeService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class HomeServlet
 * asyncSupported = true enables asynchronous processing for this servlet.
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
	 *      response)
	 * doGet method handles GET requests to retrieve all posts from the
	 * and database.
	 * homeService and forwards the request to the home.jsp page.
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 */

	// TODO Auto-generated method stub
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// Check if the user is logged in
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        // If userId is null, set it to -1 (indicating no user is logged in)
        ArrayList<PostModel> posts = homeService.getAllPosts(userId);

        request.setAttribute("posts", posts);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/home.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
