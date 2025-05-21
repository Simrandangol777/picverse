<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>

	<main class="home-main">

		<c:forEach var="post" items="${posts}">
			<div class="home-post">
				<div class="home-post-header">
					<img
						src="${pageContext.request.contextPath}/uploads/images/${post.profilePicture}"
						class="home-profile-pic" />

					<div class="home-user-content">
						<span class="home-username">${post.username}</span>
						<!-- For the time stamp -->
						<p>${post.timeAgo}</p>
					</div>
				</div>

				<p style="margin: 10px">${post.caption}</p>

				<div class="home-post-image">
					<!-- Clicking this will redirect to the view page for this specific post -->
					<a href="${pageContext.request.contextPath}/view?id=${post.id}">
						<img
						src="${pageContext.request.contextPath}/uploads/images/${post.image}"
						alt="Post Image" />
					</a>
				</div>

				<div class="home-post-footer">
					<div class="home-actions">
						<!-- Like button -->

						<div class="home-like-btn">
							<input type="hidden" name="postId" value="${post.id}" />
							<button class="home-likes-btn" id="like-btn-${post.id}"
								data-post-id="${post.id}" data-liked="${post.liked}">
								<i
									class="${post.liked ? 'fa-solid fa-heart liked' : 'fa-regular fa-heart'}"></i>
								<span class="like-count">${post.likeCount}</span> likes
							</button>

						</div>

						<a href="${pageContext.request.contextPath}/view?id=${post.id}"
							class="home-comment-btn" style="text-decoration: none;">
							<button class="home-comment-btn">
								<i class="fa-regular fa-comment"></i>
							</button>
						</a>
					</div>
				</div>
			</div>
			<hr style="width: 100%" />
			<br>
		</c:forEach>



		<!-- This is for the every single post.  -->
		<div class="home-post">
			<div class="home-post-header">
				<img
					src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
					alt="Profile Picture" class="home-profile-pic" />
				<div class="home-user-content">
					<span class="home-username">Tom</span>
				</div>
			</div>
			<p style="margin: 10px">This is the text that i like so much and
				i am happy to see it.</p>
			<!-- This is the post image.  -->
			<div class="home-post-image">
				<a href="${pageContext.request.contextPath}/view"> <img
					src="${pageContext.request.contextPath}/resources/Images/flower.jpg"
					alt="Post Image" />
				</a>
			</div>

			<!-- This is just a demo. -->
			<div class="home-post-image">
				<a href="${pageContext.request.contextPath}/view"> </a>
			</div>


			<!-- Post footer - Contains interaction buttons and likes count -->
			<div class="home-post-footer">
				<!-- Action buttons for post interaction -->
				<div class="home-actions">
					<div class="home-like-btn">
						<i class="fa-regular fa-heart"></i>
						<p class="likes">100 likes</p>
					</div>
					<a href="${pageContext.request.contextPath}/view}"
						class="home-comment-btn">
						<button class="home-comment-btn">
							<i class="fa-regular fa-comment"></i>
						</button>
					</a>
				</div>
			</div>
		</div>

		<!-- This is for the every single post.  -->
		<div class="home-post">
			<div class="home-post-header">
				<img
					src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
					alt="Profile Picture" class="home-profile-pic" />
				<div class="home-user-content">
					<span class="home-username">Tom</span>
				</div>
			</div>

			<!-- This is the post image.  -->
			<div class="home-post-image">
				<a href="view.jsp"> <img
					src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
					alt="Post Image" />
				</a>
			</div>

			<!-- Post footer - Contains interaction buttons and likes count -->
			<div class="home-post-footer">
				<!-- Action buttons for post interaction -->
				<div class="home-actions">
					<div class="home-like-btn">
						<i class="fa-regular fa-heart"></i>
						<p class="likes">100 likes</p>
					</div>
					<a href="view.html" class="home-comment-btn">
						<button class="home-comment-btn">
							<i class="fa-regular fa-comment"></i>
						</button>
					</a>
				</div>
			</div>
		</div>

	</main>

	<%@ include file="footer.jsp"%>

	<!-- Include axios -->
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

	<script>
	    // This is for the like button
		document.addEventListener("DOMContentLoaded", function() {
            // Add event listeners to all like buttons
		    document.querySelectorAll('.home-likes-btn').forEach(function(btn) {
                // Add click event listener to each button
		        btn.addEventListener('click', function(e) {
		            e.preventDefault();
		
		            const postId = btn.getAttribute('data-post-id');
		            // Check if the post is already liked
		            const liked = btn.getAttribute('data-liked') === 'true';
		
		            axios.post('${pageContext.request.contextPath}/likepost', 'postId=' + postId)
		                .then(function(response) {
		                    // Toggle heart icon and like count
		                    let icon = btn.querySelector('i');
		                    let countSpan = btn.querySelector('.like-count');
		                    let count = parseInt(countSpan.textContent);
		
		                    // Update the like status
		                    if (liked) {
		                        icon.className = 'fa-regular fa-heart';
		                        btn.setAttribute('data-liked', 'false');
		                        countSpan.textContent = count - 1;
		                    } else {
		                        icon.className = 'fa-solid fa-heart liked';
		                        btn.setAttribute('data-liked', 'true');
		                        countSpan.textContent = count + 1;
		                    }
		                })
		                .catch(function(error) {
		                    alert("Error liking post. Please try again.");
		            });
		        });
		    });
		});
	</script>




</body>
</html>