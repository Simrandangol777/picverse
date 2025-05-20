<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>login Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100..700;1,100..700&display=swap"
	rel="stylesheet">
<body>

	<section class="login-main">

		<h1 class="login-heading-main">
			Share your memories to your <br>friend and family
		</h1>

		<!-- this is the icon navbar of the in the top.  -->
		<div class="icon-navbar">
			<a href="home.html" class="login-logo"> <img
				src="${pageContext.request.contextPath}/resources/Images/logo.png"
				alt="logo">
			</a>

			<!-- This is the form of the login.  -->
			<form class="login-form" action="login" method="POST">

				<!-- This is for the heading paragraph.  -->
				<div class="login-heading">
					<h1 class="login-heading2">Login To PicVerse</h1>
				</div>

				<!-- Error Message -->
				<div
					style="text-align: center; position: relative; right: 0px; bottom: 20px;">
					<c:if test="${not empty error}">
						<p style="color: red; font-weight: bold;">${error}</p>
					</c:if>

				</div>

				<!-- This is the label and input for the username and email -->
				<div class="login-username login-input">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" placeholder="Enter a username">
				</div>
				<br> <br>

				<!-- This is the label and input for the password -->
				<div class="login-password login-input">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="Enter a password">
				</div>
				<br> <br>



				<!-- This is for the redirection.  -->
				<a href="${pageContext.request.contextPath}/register"
					class="register-link">New User</a>
				<!-- This is for the submit button.  -->
				<button class="login-button">Login</button>
			</form>
		</div>

	</section>

</body>
</html>