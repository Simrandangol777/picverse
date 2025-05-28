<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Post</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/savedposts.css">
</head>
<body>

	<div class="save-container">
		<!-- Saved Posts Heading -->
		<section class="save-liked-section">
			<h2 class="save-title">Saved Posts</h2>
			
			<div class="save-gallery">
				<c:forEach var="post" items="${savedPosts}" varStatus="status">
				
					<div class="save-gallery-card">
						<div class="save-image-wrapper">
							<a href="${pageContext.request.contextPath}/view?id=${post.id}">
								<img
								src="${pageContext.request.contextPath}/uploads/images/${post.image}"
								alt="Post Image" />
							</a>
							
							<div class="save-image-overlay">
								<div class="save-image-actions">
									<span><i class="fas fa-bookmark"></i></span> <span
										class="post-user">${savedUsers[status.index]}</span>
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