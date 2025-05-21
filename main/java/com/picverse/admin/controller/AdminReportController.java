package com.picverse.admin.controller;

import com.picverse.admin.service.AdminReportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * AdminReportController
 *
 * This servlet handles the display of user reports in the admin panel.
 * It retrieves report data from the service layer and forwards it to the JSP page for rendering.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-report" })
public class AdminReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Service layer to handle business logic related to reports
    private AdminReportService reportService = new AdminReportService();

    /**
     * Handles GET requests for the admin report page.
     * Fetches user report data from the service and forwards it to the JSP for display.
     * If an error occurs during data fetching, an error message is set in the request scope.
     *
     * URL: /admin-report
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	Map<String, Object> reports = reportService.getAdminDashboardReports();
        	request.setAttribute("reports", reports);


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to fetch report data.");
        }

        // 3. Forward to JSP
        request.getRequestDispatcher("/WEB-INF/pages/admin/report.jsp").forward(request, response);
    }


    
    /**
     * Handles POST requests by delegating to the GET handler.
     * This allows form submissions (if any) to trigger the same logic as GET.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}