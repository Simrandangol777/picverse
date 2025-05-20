package com.picverse.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.picverse.admin.service.AdminUserManagementService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * AdminUserManagementController
 * 
 * This servlet handles the user management functionality in the admin panel.
 * It allows viewing a list of all users and deleting specific users based on admin actions.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-usermanagement" })
public class AdminUserManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service layer for handling user-related business logic
    private final AdminUserManagementService userService = new AdminUserManagementService();

    
    /**
     * Default constructor.
     */
    public AdminUserManagementController() {
        super();
    }

    
    /**
     * Handles GET requests to fetch and display all users.
     * Retrieves user data from the service and forwards it to the user management JSP view.
     * In case of errors, sends an HTTP 500 error with a message.
     *
     * URL: /admin-usermanagement
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Map<String, Object>> users = userService.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/pages/admin/userManagement.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve user data.");
        }
    }

    
    /**
     * Handles POST requests for user management actions such as deletion.
     * Currently supports the "delete" action.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteUser(request, response);
        }
    }

    
    
    /**
     * Deletes a user based on the user ID provided in the request.
     * Redirects back to the user management page after successful deletion.
     * Sends error responses in case of invalid input or database failure.
     *
     * @param request  HTTP request containing user_id parameter
     * @param response HTTP response to send redirects or error messages
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("user_id"));
            userService.deleteUserById(userId);
            response.sendRedirect(request.getContextPath() + "/admin-usermanagement");
       
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid user ID format.");
        
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to delete user.");
       
        }
    }
}
