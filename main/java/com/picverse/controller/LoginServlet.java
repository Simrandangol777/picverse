package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.picverse.model.UserModel;
import com.picverse.service.LoginService;
import com.picverse.util.CookieUtil;
import com.picverse.util.SessionUtil;

/**
 * LoginController is responsible for handling login requests. It interacts with
 * the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final LoginService loginService;

	/**
	 * Constructor initializes the LoginService.
	 */
	public LoginServlet() {
		this.loginService = new LoginService();
	}

	/**
	 * Handles GET requests to the login page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for user login.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println("username: " + username);

		UserModel userModel = new UserModel(username, password);

		// Attempt to login the user
		Integer userId = loginService.loginUser(userModel);

		if (userId != null) {
			// Login successful
			HttpSession session = req.getSession();
			session.setAttribute("userId", userId);
			SessionUtil.setAttribute(req, "username", username);
			UserModel user = loginService.getUserById(userId);
			session.setAttribute("profilePicture", user.getProfilePicture());

			

			// Set cookies for role and redirect based on role
			if (username.equals("admin")) {
				CookieUtil.addCookie(resp, "role", "admin", 60 * 60);
				resp.sendRedirect(req.getContextPath() + "/admin");
			} else {
				CookieUtil.addCookie(resp, "role", "user", 60 * 60);
				resp.sendRedirect(req.getContextPath() + "/home");
			}

		} else {
			// ❌ Login failed
			handleLoginFailure(req, resp, false);
		}
	}

	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "Login failed. Please check your username and password.";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	}

}