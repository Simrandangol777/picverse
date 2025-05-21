package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.picverse.model.UserModel;
import com.picverse.service.RegisterService;
import com.picverse.util.PasswordUtil;
import com.picverse.util.ValidationUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response) This method handles the registration of a new user. It
	 *      validates the input data, encrypts the password, and stores the user
	 *      information in the database. If the registration is successful, it
	 *      redirects to the login page with a success message. If there is an
	 *      error, it forwards the request back to the registration page with an
	 *      error message.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String validationMessage = validateRegistrationForm(req);

		// Debugging output
		System.out.println("name: " + req.getParameter("name"));
		System.out.println("RegisterServlet: " + validationMessage);

		if (validationMessage == null) {
			UserModel userModel = new UserModel();

			String name = req.getParameter("name");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			// Encrypt the password before setting it into the model
			String encryptedPassword = PasswordUtil.encrypt(username, password);

			
			/*
			 * Check if the username or email is already taken. If it is, set an error
			 * message and forward to the registration page.
			 */
			Boolean isTaken = RegisterService.isUsernameOrEmailTaken(username, email);
			if (isTaken) {
				handleError(req, resp, "Username or Email already taken.");
				return;
			}
			
			// Set the user details into the model
			userModel.setName(name);
			userModel.setUsername(username);
			userModel.setEmail(email);
			userModel.setPassword(encryptedPassword);
			;

			RegisterService registerService = new RegisterService();
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded != null && isAdded) {
				handleSuccess(req, resp, "Signup successful!", "/login");
			} else {
				handleError(req, resp, "Could not register your account. Please try again later!");
			}
		} else {
			handleError(req, resp, validationMessage);
		}
	}

	/**
	 * This method validates the registration form data. It checks if the name,
	 * username, email, and password fields are not empty, and if they meet the
	 * required format.
	 * 
	 * @param req HttpServletRequest object containing the request data
	 * @return String error message if validation fails, null if validation is
	 *         successful
	 */
	private String validateRegistrationForm(HttpServletRequest req) {
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		if (ValidationUtil.isNullOrEmpty(name))
			return "name is required.";
		if (ValidationUtil.isNullOrEmpty(username))
			return "username is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "email is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "password is required.";

		if (!ValidationUtil.isValidFullName(name))
			return "Enter full name with at least 2 words. And only letters are allowed.";
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username can only contain letters and numbers.";
		if (!ValidationUtil.isValidEmail(email))
			return "Email is not valid.";
		if(!ValidationUtil.isValidPassword(password))
			return "Password must be at least 4 characters long.";

		return null;
	}

	/**
	 * This method handles the success response after successful registration. It
	 * sets a success message and forwards the request to the specified page.
	 * 
	 * @param req          HttpServletRequest object containing the request data
	 * @param resp         HttpServletResponse object for sending the response
	 * @param message      Success message to be displayed
	 * @param redirectPage Page to redirect to after successful registration
	 */
	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

	/**
	 * This method handles the error response in case of registration failure. It
	 * sets an error message and forwards the request back to the registration page.
	 * 
	 * @param req     HttpServletRequest object containing the request data
	 * @param resp    HttpServletResponse object for sending the response
	 * @param message Error message to be displayed
	 */
	private void handleError(HttpServletRequest req, HttpServletResponse resp, String message)
			throws ServletException, IOException {
		req.setAttribute("error", message);
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("username", req.getParameter("username"));
		req.setAttribute("email", req.getParameter("email"));
		req.setAttribute("password", req.getParameter("password"));
		req.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(req, resp);
	}
}
