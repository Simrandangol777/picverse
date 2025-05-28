<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Management Page</title>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/userManagement.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
	integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>

	<div class="user-container">

		<%@ include file="adminSidebar.jsp"%>


		<main class="user-main-content">
			<header class="user-main-header">
				<h1>Users</h1>
				<div class="search-bar">
					<input type="text" placeholder="Search...">
				</div>
			</header>
			<section class="user-list">
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<!-- <th>Status</th>  -->
							<th>Email</th>
							<th>Actions</th>
						</tr>
					</thead>
					
					<tbody>
					  <c:forEach var="user" items="${users}">
					    <tr onclick="showUserInfo('${user.username}', '${user.email}')">
					      <td>${user.username}</td>
					      <!-- <td>${user.status}</td> --> 
					      <td>${user.email}</td>
					      
					      
					      <td class="action-buttons">
					        <form method="post" action="${pageContext.request.contextPath}/admin-usermanagement" 
					        onsubmit="return openPopup(this);"
					        style="display:inline;">
					          <input type="hidden" name="user_id" value="${user.userId}">
					          <input type="hidden" name="action" value="delete">
					          <button type="submit" class="delete">Delete</button>
					        </form>
					      </td>
					    </tr>
					  </c:forEach>
					</tbody>


				</table>
			</section>
		</main>
		
	</div>
	
	<aside class="user-info">
		<div class="user-info-header">
			<span>User Information</span> <span class="active-status">Active</span>
		</div>
		<div class="user-details">
			<div class="profile-image">
				<div class="placeholder-icon">
					<i class="fa-solid fa-user fa-3x"></i>
				</div>
			</div>
			<h2 class="user-name">User_Name</h2>
			<div class="info-item">
				<i class="fa-regular fa-envelope"></i> <span>Email:
					User@gmail.com</span>
			</div>
			<div class="info-item">
				<i class="fa-regular fa-clock"></i> <span>Last visit: 4th
					April, 2025</span>
			</div>
			<button class="update-button">
				<i class="fa-solid fa-pen-to-square"></i> Update info
			</button>
		</div>
	</aside>
	
	<script>
	  function showUserInfo(name, email) {
	    document.querySelector('.user-name').innerText = name;
	    document.querySelector('.info-item span').innerText = "Email: " + email;
	  }
	</script>
	
	
	
	<!-- for deletion of user -->
	<div id="customPopup" class="popup-overlay">
	  <div class="popup-box">
	    <p>Do you really want to delete this user?</p>
	    <div class="popup-buttons">
	      <button id="confirmDelete">Yes</button>
	      <button type="button" onclick="closePopup()">No</button>
	    </div>
	  </div>
	</div>
	
	<%@ include file="confirmDeletePopup.jsp" %>
	

</body>
</html>