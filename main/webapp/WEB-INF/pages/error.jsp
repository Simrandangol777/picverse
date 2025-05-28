<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Occurred</title>
</head>
<body>

	<div
		style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); text-align: center; max-width: 500px;">
		<!-- Error image (GitHub style) -->
		<img
			src="${pageContext.request.contextPath}/resources/Images/error.png"
			alt="Error Image" width="350" height="350"
			style="margin-bottom: 20px;" />

		<h1 style="color: black; margin-bottom: 15px;">Oops! Something
			went wrong.</h1>

		<p style="color: black; margin-bottom: 10px;">An unexpected error
			occurred while processing your request.</p>
		<p style="color: black;">
			Please try again later or <a
				href="${pageContext.request.contextPath}/contact"
				style="color: black; text-decoration: underline;">contact
				support</a> if the issue persists.
		</p>
	</div>

</body>
</html>