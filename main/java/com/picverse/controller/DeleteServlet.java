package com.picverse.controller;

import com.picverse.service.DeleteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet responsible for handling deletion of posts.
 * Maps to /delete endpoint and delegates logic to DeleteService.
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DeleteService deleteService;

    /**
     * Initializes the DeleteService when the servlet starts.
     */
    @Override
    public void init() throws ServletException {
        deleteService = new DeleteService();
    }

    /**
     * Handles the POST request to delete a post based on its ID.
     *
     * @param request  HttpServletRequest containing post ID to delete.
     * @param response HttpServletResponse to redirect or show error.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Parse post ID from the request
            int postId = Integer.parseInt(request.getParameter("id"));

            // Delegate deletion to the service class
            boolean isDeleted = deleteService.deletePostById(postId);

            if (isDeleted) {
                // Redirect to home page on success
                response.sendRedirect("home");
            } else {
                // Inform client if deletion failed
                response.getWriter().write("Post could not be deleted.");
            }

        } catch (NumberFormatException e) {
            // Handle invalid or missing post ID
            response.getWriter().write("Invalid post ID format.");
        } catch (Exception e) {
            // General exception logging
            e.printStackTrace();
            response.getWriter().write("Error deleting post: " + e.getMessage());
        }
    }
}
