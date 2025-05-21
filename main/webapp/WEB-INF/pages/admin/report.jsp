<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard Reports</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/report.css">
</head>
<body>

	<%@ include file="adminSidebar.jsp" %>
	<div class="report-container">
		<h1>Admin Dashboard Report</h1>

		<div class="report-card">
			<h2>Total Users</h2>
			<p>${reports.totalUsers}</p>
		</div>

		<div class="report-card">
			<h2>Most Followed Users</h2>
			<c:forEach var="user" items="${reports.mostFollowedUsers}">
				<p>${user.username}- ${user.followers} followers</p>
			</c:forEach>
		</div>

		<div class="report-card">
			<h2>Most Liked Users</h2>
			<c:forEach var="user" items="${reports.mostLikedUsers}">
				<p>${user.username}- ${user.likesReceived} likes</p>
			</c:forEach>
		</div>

		<div class="report-card">
			<h2>Most Active Users</h2>
			<c:forEach var="user" items="${reports.mostActiveUsers}">
				<p>${user.username}- Posts: ${user.postCount}, Comments:
					${user.commentCount}</p>
			</c:forEach>
		</div>

		<div class="report-card">
			<h2>Users Saving the Most Posts</h2>
			<c:forEach var="user" items="${reports.mostSavingUsers}">
				<p>${user.username}- ${user.savedPosts} saved posts</p>
			</c:forEach>
		</div>

		<div class="report-card">
			<h2>Most Liked Post</h2>
			<c:set var="post" value="${reports.mostLikedPost}" />
			<p>Post ID: ${post.postId} by ${post.username} -
				${post.totalLikes} likes</p>
		</div>

		<div class="report-card">
			<h2>Most Commented Post</h2>
			<c:set var="post" value="${reports.mostCommentedPost}" />
			<p>Post ID: ${post.postId} by ${post.username} -
				${post.totalComments} comments</p>
		</div>
	</div>
</body>
</html>
