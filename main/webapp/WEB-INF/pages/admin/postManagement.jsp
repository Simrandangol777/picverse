<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Management</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/postManagement.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>

	<%@ include file="adminSidebar.jsp"%>
	
	<div class="post-container">
		<h1>Manage Posts</h1>
	    <!-- Search Bar -->
	    <%@ include file="searchBar.jsp" %>

		<table class="posts-table">
			<thead>
				<tr>
					<th>Post ID</th>
					<th>Username</th>
					<th>Caption</th>
					<th>Image</th>
					<!-- <th>Date Posted</th>  -->
					<th>Actions</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="post" items="${posts}">
					<tr>
						<td>${post.postId}</td>
						<td>${post.username}</td>
						<td>${post.caption}</td>
						<td><img
							src="${pageContext.request.contextPath}/uploads/images/${post.image}"
							alt="Post" width="60"></td>
						
						<!-- <td>${post.createdAt}</td> -->


						<td class="action-buttons">
							
							<!-- Delete Button -->
							<form method="post"
								action="${pageContext.request.contextPath}/admin-postmanagement"
								onsubmit="return openPopup(this);"
								style="display: inline;">
								
								<input type="hidden" name="post_id" value="${post.postId}">
								<input type="hidden" name="action" value="delete">
								<button type="submit" class="delete">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>

			</tbody>

		</table>
	</div>
	
	<div id="customPopup" class="popup-overlay">
	  <div class="popup-box">
	    <p>Do you really want to delete this post?</p>
	    <div class="popup-buttons">
	      <button id="confirmDelete">Yes</button>
	      <button type="button" onclick="closePopup()">No</button>
	    </div>
	  </div>
	</div>
	
	<!-- Pop up for delete confirmation -->
	<%@ include file="confirmDeletePopup.jsp" %>
	

</body>
</html>



