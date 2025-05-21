package com.picverse.controller;

// importing necessary packages
import java.io.IOException;

import com.picverse.service.PostLikeService;
import com.picverse.service.PostLikeService.LikeResult;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* 
 *  PostLikeServlet handles the like/unlike functionality for posts.
 * It manages database interactions for liking and unliking posts.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/likepost" })
public class PostLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 * This method handles the POST request to toggle the like status of a post for
	 * a user.
	 * 
	 * @param request HttpServletRequest object
	 * 
	 * @param response HttpServletResponse object
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Get postId from request parameter and userId from session. If userId is null,
		 * set response status to UNAUTHORIZED.
		 * 
		 */
		try {
			int postId = Integer.parseInt(request.getParameter("postId"));
			Integer userId = (Integer) request.getSession().getAttribute("userId");

			if (userId == null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}

			// Call the PostLikeService to toggle like status
			PostLikeService service = new PostLikeService();
			LikeResult result = service.toggleLike(postId, userId);

			/*
			 * Set response content type to JSON and write the result as a JSON object. The
			 * JSON object contains the new like status and the updated like count.
			 * application/json is the MIME type for JSON data.
			 */
			response.setContentType("application/json");
			// Set response status to OK
			response.getWriter().write("{\"liked\":" + result.liked + ",\"likeCount\":" + result.likeCount + "}");

		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
}
