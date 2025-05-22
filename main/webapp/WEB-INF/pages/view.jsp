<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Post</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/view.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body>

	<main class="view-main">
		<div class="view-post">

			<!-- 1 Post Header -->
			<div class="view-post-header">
				<img
					src="${pageContext.request.contextPath}/uploads/images/${post.profilePicture}"
					alt="${post.username}'s Profile Picture" class="view-profile-pic" />
				<div class="view-user-content">
					<span class="view-username">${post.username}</span>
					<p>${post.timeAgo}</p>
				</div>

				<div class="view-dropdown">
					<button class="view-dropdown-toggle" type="button"
						id="postOptionsBtn${post.id}" aria-haspopup="true"
						aria-expanded="false">
						&#x22EE;
						<!-- vertical three dots -->
					</button>
					<div class="view-dropdown-menu"
						aria-labelledby="postOptionsBtn${post.id}">
						<form class="home-save-btn" action="saved-posts" method="post">
							<input type="hidden" name="postId" value="${post.id}" />
							<c:choose>
								<c:when test="${not post.saved}">
									<button class="view-dropdown-item">
										<i class="fa-regular fa-bookmark"></i> Save Post
									</button>
								</c:when>
								<c:when test="${post.saved}">
									<button class="view-dropdown-item">
										<i class="fa-solid fa-bookmark"></i> Saved
									</button>
								</c:when>
							</c:choose>
						</form>



						<c:if test="${loggedInUserId == post.userId}">
							<a href="${pageContext.request.contextPath}/edit?id=${post.id}"
								class="view-dropdown-item"> <i
								class="fa-solid fa-pen-to-square"></i> Edit
							</a>
							<form action="${pageContext.request.contextPath}/delete"
								method="get" style="margin: 0;">
								<input type="hidden" name="id" value="${post.id}" />
								<button type="submit" class="view-dropdown-item view-delete-btn"
									style="border: none; background: none; padding: 8px 15px; width: 100%; text-align: left; cursor: pointer;">
									<i class="fa-solid fa-trash-can"></i> Delete
								</button>
							</form>
						</c:if>
					</div>
				</div>
			</div>


			<!-- 2 Caption -->
			<div class="view-caption-container">
				<p class="view-caption">${post.caption}</p>
			</div>

			<!-- 3 Image -->
			<div class="view-post-image">
				<a
					href="${pageContext.request.contextPath}/uploads/images/${post.image}">
					<img
					src="${pageContext.request.contextPath}/uploads/images/${post.image}"
					alt="Post Image" />
				</a>
			</div>

			<!-- 4 Like/Comment Icons -->
			<div class="view-like">
				<i class="fa-solid fa-comment"><span
					style="font-size: 18px; margin-left: 5px">${fn:length(comments) }
				</span></i>
			</div>

			<!-- 5 Comment Input Form -->
			<div class="view-comment-section">
				<form action="${pageContext.request.contextPath}/view" method="post">
					<input type="hidden" name="postId" value="${post.id}" /> <input
						type="text" name="comment" placeholder="Add a comment" required />
					<button type="submit">
						<i class="fa fa-paper-plane"></i> Post
					</button>
				</form>
			</div>

			<!-- 6 Comments List -->
			<div class="view-comments">
				<c:choose>
					<c:when test="${not empty comments}">
						<c:forEach items="${comments}" var="comment">
							<div class="view-comment">
								<div class="view-image-username">
									<img
										src="${pageContext.request.contextPath}/uploads/images/${comment.commentProfilePicture}"
										alt="${comment.commentUserName}'s Profile Picture"
										class="view-comment-profile-pic" /> <span
										class="view-comment-username"
										style="position: relative; bottom: 11px"> <c:out
											value="@${comment.commentUserName}" />
									</span>
								</div>

								<c:if test="${loggedInUserId == comment.userId}">
									<form
										action="${pageContext.request.contextPath}/delete-comment"
										method="post">
										<input type="hidden" name="commentId" value="${comment.id}" />
										<input type="hidden" name="postId" value="${post.id}" />
										<button type="submit" class="btn-delete-comment">
											<i class="fa-solid fa-trash-can"></i>
										</button>
									</form>
								</c:if>
								<p class="view-comment-text">
									<c:out value="${comment.comment}" />
								</p>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="no-comments">No comments yet.</p>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</main>

	<script>
	document.querySelectorAll('.view-dropdown-toggle').forEach(button => {
		  button.addEventListener('click', () => {
		    const dropdown = button.parentElement;
		    dropdown.classList.toggle('show');
		  });
		});

		// Close dropdown if clicked outside
		window.addEventListener('click', (e) => {
		  document.querySelectorAll('.view-dropdown').forEach(dropdown => {
		    if (!dropdown.contains(e.target)) {
		      dropdown.classList.remove('show');
		    }
		  });
		});

	</script>

</body>
</html>
