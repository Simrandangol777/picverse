<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/leftNavbar.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
	integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	<!-- This is for the footer of icon and the element. -->
	<main class="left-navbar">
		<div class="left-nav-item">
			<!-- Home icon  -->
			<a href="${pageContext.request.contextPath}/home"
				class="left-nav-item-common"> <i class="fa-solid fa-igloo"></i>
				<h2>Home</h2>
			</a>

			<!-- Create Icon.  -->
			<a href="${pageContext.request.contextPath}/create"
				class="left-nav-item-common"> <i class="fa-solid fa-circle-plus"></i>
				<h2>Create</h2>
			</a>

			<!-- Setting option  -->
			<a href="${pageContext.request.contextPath}/follow"
				class="left-nav-item-common"> <i class="fa-solid fa-user"></i>
				<h2>Friends</h2>
			</a>

			<!-- .History option  -->
			<a href="${pageContext.request.contextPath}/contact"
				class="left-nav-item-common"> <i class="fa-solid fa-phone"></i>
				<h2>Contact</h2>
			</a>

			<!-- .History option  -->
			<a href="${pageContext.request.contextPath}/history"
				class="left-nav-item-common"> <i
				class="fa-solid fa-clock-rotate-left"></i>
				<h2>History</h2>
			</a>

			<!-- .History option  -->
			<a href="${pageContext.request.contextPath}/about-us"
				class="left-nav-item-common"> <i class="fa-solid fa-circle-info"></i>
				<h2>About us</h2>
			</a>

			<div class="left-nav-increase-space"></div>

			<!-- .Logout  option  -->
			<!--  
			<a href="${pageContext.request.contextPath}/logout"
				class="left-nav-item-common"> 
                <i class="fa-solid fa-right-from-bracket"></i>
				<h2>Log out</h2>
			
			<!-- .Logout  option  -->
			<form class="left-nav-item-common"
				action="${pageContext.request.contextPath}/logout" method="post">
				<i class="fa-solid fa-right-from-bracket"></i>
				<button>
					<h2 style="font-size: 16px">Log out</h2>
				</button>
			</form>

			<!-- Profile  -->
			<a href="${pageContext.request.contextPath}/profile"
				class="left-nav-item-common"> 
				<c:choose>
				<c:when test="${empty sessionScope.profilePicture}">
					<img
						src="${pageContext.request.contextPath}/resources/Images/lion.jpg"
						alt="Default User" class="profile-img" />
				</c:when>
				<c:otherwise>
					<img
						src="${pageContext.request.contextPath}/uploads/images/${sessionScope.profilePicture}"
						alt="User Image" class="profile-img" />
				</c:otherwise>
			</c:choose>
				
				<h2>Profile</h2>
			</a>
			
		</div>
	</main>

</body>
</html>