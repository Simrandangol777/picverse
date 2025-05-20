package com.picverse.controller;

import com.picverse.model.PostModel;
import com.picverse.service.UpdateService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * Servlet responsible for handling post edit requests.
 */
@WebServlet("/edit")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UpdateService updateService;

    /**
     * Initializes the UpdateService instance.
     */
    @Override
    public void init() throws ServletException {
        updateService = new UpdateService();
    }

    /**
     * Displays the post edit form if user is authorized.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Edit post servlet called");

        HttpSession session = request.getSession(false);
        Integer userId = (session != null) ? (Integer) session.getAttribute("userId") : null;

        if (userId == null) {
            System.out.println("User is not logged in");
            response.sendRedirect("login");
            return;
        }

        try {
            int postId = Integer.parseInt(request.getParameter("id"));

            if (!updateService.isPostOwner(postId, userId)) {
                System.out.println("User is not the owner of the post");
                response.sendRedirect("view?id=" + postId);
                return;
            }

            PostModel post = updateService.getPostById(postId);
            if (post == null) {
                response.sendRedirect("home");
                return;
            }

            request.setAttribute("post", post);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edit.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("WEB-INF/pages/error.jsp");
        }
    }

    /**
     * Handles caption update submission for a post.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer userId = (session != null) ? (Integer) session.getAttribute("userId") : null;

        if (userId == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            int postId = Integer.parseInt(request.getParameter("id"));
            String caption = request.getParameter("caption");

            if (!updateService.isPostOwner(postId, userId)) {
                response.getWriter().write("Unauthorized access.");
                return;
            }

            updateService.updateCaption(postId, caption);
            response.sendRedirect("view?id=" + postId);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
