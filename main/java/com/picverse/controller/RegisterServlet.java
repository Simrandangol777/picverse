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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String validationMessage = validateRegistrationForm(req);
		
		System.out.println("name: " + req.getParameter("name"));
		System.out.println("username: " + req.getParameter("username"));
		System.out.println("email: " + req.getParameter("email"));
		System.out.println("password: " + req.getParameter("password"));
		System.out.println("RegisterServlet: " + validationMessage);


		if (validationMessage == null) {
			UserModel userModel = new UserModel();

			String name = req.getParameter("name");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			// Encrypt the password before setting it into the model
			String encryptedPassword = PasswordUtil.encrypt(username, password);

			userModel.setName(name);
			userModel.setUsername(username);
			userModel.setEmail(email);
			userModel.setPassword(encryptedPassword);;

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
			return "Name can only contain letters or enter full name.";
		if (!ValidationUtil.isAlphanumericStartingWithLetter(username))
			return "Username can only contain letters and numbers.";
		if (!ValidationUtil.isValidEmail(email))
			return "Email is not valid.";

		return null;
	}

	private void handleSuccess(HttpServletRequest req, HttpServletResponse resp, String message, String redirectPage)
			throws ServletException, IOException {
		req.setAttribute("success", message);
		req.getRequestDispatcher(redirectPage).forward(req, resp);
	}

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
