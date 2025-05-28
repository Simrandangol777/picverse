package com.picverse.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.picverse.model.PostModel;
import com.picverse.service.SavePostService;

@WebServlet("/saved-posts")
public class SavePostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SavePostService savePostService;

    public SavePostServlet() {
        super();
        savePostService = new SavePostService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            Integer loggedInUserId = (Integer) session.getAttribute("userId");
            ArrayList<PostModel> savedPosts = savePostService.getSavedPosts(loggedInUserId);
            request.setAttribute("savedPosts", savedPosts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/pages/savedposts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SavePost servlet called");

        try {
            Integer loggedInUserId = (Integer) request.getSession().getAttribute("userId");
            if (loggedInUserId == null) {
                response.sendRedirect("login");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            savePostService.toggleSavedPost(loggedInUserId, postId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("view?id=" + request.getParameter("postId"));
    }
}
