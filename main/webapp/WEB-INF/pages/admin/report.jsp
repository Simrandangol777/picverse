<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Report</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/report.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
	integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>

	<div class="report-container">

		<%@ include file="adminSidebar.jsp"%>

		<div class="report-main">
			<div class="report-topbar">
				<div class="search-bar">
					<input type="text" placeholder="Search Customer...">
				</div>

				<div class="user-info">
					<a href="${pageContext.request.contextPath}/admin-profile"> <i
						class="fa fa-user-circle"></i> <span>Admin Profile</span>
					</a>
				</div>
			</div>

			<main class="main-content">
				<h1 class="report-heading">Customer Insights Dashboard</h1>

				<div class="report-section">
					<c:forEach var="user" items="${userReports}">
						<div class="report-card">
							<div class="profile-icon-container">
								<i class="fa-solid fa-user"></i>
							</div>

							<div class="customer-details">
								<p>
									<strong>Customer:</strong> ${user.username}
								</p>
								<p>
									<strong>Posts:</strong> ${user.postCount}
								</p>
								<p>
									<strong>Comments:</strong> ${user.commentCount}
								</p>
								<p>
									<strong>Likes:</strong> 5,000
								</p>
								<p>
									<strong>Followers:</strong> 20,000
								</p>
								<p>
									<strong>Following:</strong> 169
								</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</main>
		</div>
	</div>

</body>
</html>
