<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/Images/logomain.png">
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">
</head>
<body>

	<div class="sidebar">
		<div class="logo">
			<img
				src="${pageContext.request.contextPath}/resources/Images/logomain.png"
				alt="Logo">
		</div>

		<ul>
			<li><a href="${pageContext.request.contextPath}/admin"><i
					class="fa fa-chart-line"></i>Dashboard</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin-usermanagement"><i
					class="fa fa-users"></i>User Management</a></li>
			<li><a
				href="${pageContext.request.contextPath}/admin-postmanagement"><i
					class="fa fa-image"></i>Post Management</a></li>
			<li><a href="${pageContext.request.contextPath}/admin-report"><i
					class="fa fa-file-alt"></i>Reports</a></li>
			<li><a href="${pageContext.request.contextPath}/admin-message"><i
					class="fa-solid fa-message"></i>Message</a></li>

			<li><a href="${pageContext.request.contextPath}/admin-profile"><i
					class="fa fa-user"></i>Admin Profile</a></li>

			<li style="padding: 0;">
				<form action="${pageContext.request.contextPath}/logout"
					method="post" style="width: 100%;">
					<button type="submit" class="sidebar-button">
						<i class="fa-solid fa-sign-out-alt"></i>Logout
					</button>
				</form>
			</li>


		</ul>
	</div>

</body>
</html>