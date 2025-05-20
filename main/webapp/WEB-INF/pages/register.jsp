<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/register.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital,wght@0,100..700;1,100..700&display=swap"
	rel="stylesheet">
</head>
<body>

	<section class="register-main">


		<h1 class="register-heading-main">
			Share your memories to your <br>friend and family
		</h1>

		<!-- this is the icon navbar of the in the top.  -->
		<div class="icon-navbar">
			<a href="home.jsp" class="register-logo"> <img
				src="${pageContext.request.contextPath}/resources/Images/logo.png"
				alt="logo">
			</a>

			<!-- This is the form of the register.  -->
			<form class="register-form" action="register" method="post">

				<!-- This is for the heading paragraph.  -->
				<div class="register-heading">
					<h1 class="register-heading2">Signup To PicVerse</h1>
				</div>

				<!-- Error Message -->
				<div style="text-align: center; position: relative; right: 80px; bottom: 20px;">
					<c:if test="${not empty error}">
						<p style="color: red; font-weight: bold;">${error}</p>
					</c:if>

				</div>


				<!-- This is the label and input for the name -->
				<div class="register-name register-input">
					<label for="name">Name</label> <input type="text" id="name"
						name="name" placeholder="Enter your name">
				</div>
				<br> <br>

				<!-- This is the label and input for the username -->
				<div class="register-username register-input">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" placeholder="Enter a username">
				</div>
				<br> <br>

				<!-- This is the label and input for the email -->
				<div class="register-email register-input">
					<label for="email">Email</label> <input type="text" id="email"
						name="email" placeholder="Enter a email">
				</div>
				<br> <br>

				<!-- This is the label and input for the password -->
				<div class="register-password register-input">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="Enter a password">
				</div>
				<br> <br>

				<!-- This is for the redirection.  -->
				<a href="${pageContext.request.contextPath}/login"
					class="login-link">Already have a account</a>
				<!-- This is for the submit button.  -->
				<button class="register-button" type="submit">Register</button>
			</form>
		</div>

	</section>

</body>
</html>