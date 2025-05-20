<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Followers and Following</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/friends.css" />
</head>
<body>
	<div class="friends-main">

		<!-- FOLLOWING -->
		<div class="friends-column">
			<h2>Following</h2>
			<c:forEach var="user" items="${followingList}">
				<div class="friends-card">
					<img
						src="${pageContext.request.contextPath}/uploads/images/${user.profilePicture}"
						class="friends-profile-pic" />
					<div class="friends-info">
						<span class="friends-username">${user.username}</span> <span
							class="friends-status">You follow</span>
					</div>
					<a href="${pageContext.request.contextPath}/profile?username=${user.username}" class="friends-link">View
						Profile</a>
				</div>
			</c:forEach>
			<c:if test="${empty followingList}">
				<p>You are not following anyone yet.</p>
			</c:if>
		</div>

		<!-- FOLLOWERS -->
		<div class="friends-column">
			<h2>Followers</h2>
			<c:forEach var="user" items="${followersList}">
				<div class="friends-card">
					<img
						src="${pageContext.request.contextPath}/uploads/images/${user.profilePicture}"
						class="friends-profile-pic" />
					<div class="friends-info">
						<span class="friends-username">${user.username}</span> <span
							class="friends-status">Follows you</span>
					</div>
					<a href="${pageContext.request.contextPath}/profile?username=${user.username}" class="friends-link">View
						Profile</a>
				</div>
			</c:forEach>
			<c:if test="${empty followersList}">
				<p>No one is following you yet.</p>
			</c:if>
		</div>

		<!-- SUGGESTIONS (Optional) -->
		<div class="friends-column">
			<h2>Suggestions</h2>

			<c:forEach var="user" items="${suggestedUsers}">
				<div class="friends-card">
					<img
						src="${pageContext.request.contextPath}/uploads/images/${user.profilePicture}"
						class="friends-profile-pic" />
					<div class="friends-info">
						<span class="friends-username">${user.username}</span> <span
							class="friends-status">Suggested for you</span>
					</div>
					<a href="${pageContext.request.contextPath}/profile?username=${user.username}" class="friends-link">View
						Profile</a>
				</div>
			</c:forEach>

			<c:if test="${empty suggestedUsers}">
				<p>No suggestions available right now.</p>
			</c:if>
		</div>


	</div>


</body>
</html>