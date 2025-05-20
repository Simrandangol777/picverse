<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile Page</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/profile.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
	<div class="profile-container">
		<!-- Profile Header -->
		<div class="profile-header">
			<div class="profile-picture">
				<img
					src="${pageContext.request.contextPath}/uploads/images/${profilePicture}"
					alt="Profile Picture">
			</div>
			<div class="profile-details">
				<h2 class="profile-username">${username}</h2>
				<div class="profile-action-buttons">
					<c:choose>
						<c:when test="${isOwnProfile}">
							<!-- Don't show follow/unfollow button for yourself -->
						</c:when>
						<c:when test="${isFollowing}">
							<form method="post" action="follow">
								<input type="hidden" name="followingId" value="${profileUserId}" />
								<input type="hidden" name="action" value="unfollow" /> <input
									type="hidden" name="username" value="${username}" />
								<button class="profile-following">Unfollow</button>
							</form>
						</c:when>
						<c:otherwise>
							<form method="post" action="follow">
								<input type="hidden" name="followingId" value="${profileUserId}" />
								<input type="hidden" name="action" value="follow" /> <input
									type="hidden" name="username" value="${username}" />
								<button class="profile-followers">Follow</button>
							</form>
						</c:otherwise>
					</c:choose>


					<c:if test="${isOwnProfile}">
						<a href="${pageContext.request.contextPath}/edit-profile">
							<button class="profile-edit">
								<i class="fas fa-cog"></i>
							</button>
						</a>
						<a href="${pageContext.request.contextPath}/saved-posts">
							<button style="height: 43px;" class="profile-saved">
								Saved Posts</button>
						</a>
					</c:if>

				</div>
			</div>
		</div>

		<!-- Stats Bar -->
		<div class="profile-stats-bar">
			<div class="profile-stat-item">
				<span class="profile-stat-count">${fn:length(posts)}</span> <span
					class="profile-stat-label">Posts</span>
			</div>
			<div class="profile-stat-item">
				<span class="profile-stat-count">348</span> <span
					class="profile-stat-label">Followers</span>
			</div>
			<div class="profile-stat-item">
				<span class="profile-stat-count">215</span> <span
					class="profile-stat-label">Following</span>
			</div>
		</div>

		<!-- Gallery Grid -->
		<div class="profile-gallery">
			<c:forEach var="post" items="${posts}">
				<div class="profile-gallery-card">
					<div class="profile-image-wrapper">
						<a href="${pageContext.request.contextPath}/view?id=${post.id}">
							<img
							src="${pageContext.request.contextPath}/uploads/images/${post.image}"
							alt="Post Image" />
						</a>
						<div class="profile-image-overlay">
							<div class="profile-image-actions">
								<span><i class="fas fa-heart"></i></span>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>
</body>
</html>