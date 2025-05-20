package com.picverse.admin.controller;

import com.picverse.admin.service.DashboardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * DashboardController
 *
 * This servlet handles requests to the admin dashboard.
 * It retrieves and displays key metrics such as total users, total posts, and total likes.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin" })
public class DashboardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service class responsible for providing dashboard statistics
    private final DashboardService dashboardService = new DashboardService();

    /**
     * Handles GET requests for the admin dashboard.
     * Fetches dashboard metrics from the service and sets them as request attributes.
     * Forwards the request to the dashboard JSP for rendering.
     *
     * URL: /admin
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int totalUsers = dashboardService.getTotalUsers();
            int totalPosts = dashboardService.getTotalPosts();
            int totalLikes = dashboardService.getTotalLikes();

            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalPosts", totalPosts);
            request.setAttribute("totalLikes", totalLikes);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to retrieve dashboard data.");
        }

        // Forward to the admin dashboard JSP page
        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }

    
    /**
     * Handles POST requests by redirecting them to the GET handler.
     * This ensures consistent behavior regardless of the request method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
