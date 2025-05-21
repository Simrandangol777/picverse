<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Picverse</title>
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/Images/logomain.png">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/navbar.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
	integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300;400;600&family=Poppins:wght@300;400;500;600&display=swap"
	rel="stylesheet">
</head>

<body>
	<header class="top-navbar">

		<!-- Website logo -->
		<div class="logo">
			<a href="${pageContext.request.contextPath}/"> <img
				src="${pageContext.request.contextPath}/resources/Images/logo.png"
				alt="Logo"> <span>picverse</span>
			</a>
		</div>

		<!-- Search bar -->
		<div class="search-bar">
			<form action="${pageContext.request.contextPath}/search" method="GET">
				<input type="text" name="query" placeholder="Search here..." />
				<button type="submit">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>

			<!-- Results Section -->
			<c:if test="${not empty users}">
				<div class="navbar-results">
					<c:forEach var="user" items="${users}">
						<a
							href="${pageContext.request.contextPath}/profile?username=${user.username}"
							class="navbar-user-result"> <img
							src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
							alt="User" /> <span>${user.username}</span>
						</a>

					</c:forEach>
				</div>
			</c:if>

			<c:if test="${empty users && param.query != null}">
				<div class="navbar-results navbar-no-results">No users found</div>
			</c:if>



		</div>


		<!-- Profile dropdown -->
		<div class="profile-dropdown">
			<div class="profile-trigger">
				<c:choose>
					<c:when test="${empty sessionScope.profilePicture}">
						<img
							src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
							alt="Default User" />
					</c:when>
					<c:otherwise>
						<img
							src="${pageContext.request.contextPath}/uploads/images/${sessionScope.profilePicture}"
							alt="User Image" />
					</c:otherwise>
				</c:choose>

				<span> <%
 String username = (String) session.getAttribute("username");
 out.print((username != null) ? username : "Guest");
 %>
				</span> <i class="fa-solid fa-chevron-down"></i>

			</div>
			<div class="dropdown-menu">
				<a href="${pageContext.request.contextPath}/profile"><i
					class="fa-regular fa-user"></i> Profile</a> <a
					href="${pageContext.request.contextPath}/edit-profile"><i
					class="fa-solid fa-gear"></i> Edit Profile</a>
				<form action="${pageContext.request.contextPath}/logout"
					method="post">
					<i class="fa-solid fa-right-from-bracket"></i>
					<button>Logout</button>
				</form>
			</div>
		</div>

	</header>
</body>
</html>