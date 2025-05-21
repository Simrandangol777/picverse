<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Liked Posts Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/history.css" />

</head>
<body>
	<div class="history-container">
		<!-- Liked Posts Heading -->
		<section class="history-liked-section">
			<h2 class="history-title">Liked Posts</h2>
			<div class="history-gallery">
				<c:forEach var="post" items="${likedPosts}" varStatus="status">
					<div class="history-gallery-card">
						<div class="history-image-wrapper">
							<a href="${pageContext.request.contextPath}/view?id=${post.id}">
								<img
								src="${pageContext.request.contextPath}/uploads/images/${post.image}"
								alt="Post Image" />
							</a>
							<div class="history-image-overlay">
								<div class="history-image-actions">
									<span><i class="fas fa-heart"></i></span> <span
										class="post-user">${likedUsers[status.index]}</span>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>


			</div>
		</section>
	</div>

</body>
</html>
