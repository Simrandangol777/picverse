<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/profile.css">
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
	integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>
<body>

	<div class="admin-container">

		<%@ include file="adminSidebar.jsp"%>

		<main class="main-content">
			<header class="main-header">
				<h1>Admin Profile</h1>
			</header>
			
			<section class="profile-section">
				<div class="profile-details-card">
					<div class="profile-image">
						<i class="fa-solid fa-user-circle fa-5x"></i>
					</div>
					
					<div class="profile-info">
						 <h2>Admin Name</h2>
	                   	 <p>
	                     	<i class="fa-solid fa-envelope" style="color: white;"></i>
	                     	admin@gmail.com
	                     </p>
						
						<p>
							<i class="fa-solid fa-briefcase" style="color: white;"></i>
							Administrator
						</p>
						
						<p>
							<i class="fa-solid fa-calendar-alt" style="color: white;"></i>
							Member Since: January 15, 2023
						</p>
					</div>
				</div>

				<div class="admin-actions-grid">
					<div class="action-card">
						<a href="${pageContext.request.contextPath}/admin-usermanagement"><i
							class="fa-solid fa-users-cog fa-2x"></i> <span>Manage
								Users</span></a>
					</div>
					
					<div class="action-card">
						<a href="${pageContext.request.contextPath}/admin-postmanagement"><i
							class="fa-solid fa-file-alt fa-2x"></i> <span>Manage Posts</span></a>
					</div>
					
					<div class="action-card">
						<a href="${pageContext.request.contextPath}/admin"><i
							class="fa-solid fa-chart-line fa-2x"></i> <span>View
								Analytics</span></a>
					</div>
					
					<div class="action-card">
						<a href="#" id="settingsLink"><i class="fa-solid fa-cog fa-2x"></i>
							<span>Settings</span></a>
					</div>
					
					<div class="action-card logout-card">
						<form class="logout-form"
							action="${pageContext.request.contextPath}/logout" method="post">
							<button type="submit" class="logout-button">
								<i class="fa-solid fa-sign-out-alt fa-2x"></i> <br> <h2>Logout</h2>
							</button>
						</form>
					</div>

				</div>
			</section>
		</main>

	</div>


	<div id="settingsPopup" class="popup">
		<div class="popup-content">
			<span class="close-button">&times;</span>
			<h3>Settings</h3>
			<ul>
				<li><a href="#">Edit Profile</a></li>
				<li><a href="#">Manage Account</a></li>
				<li><a href="#">Notifications</a></li>
				<li><a href="#">Privacy</a></li>
				<li><a href="#">Appearance</a></li>
			</ul>
		</div>
	</div>

	<script>
        const settingsLinks = document.getElementById('settingsLink');
        const settingsPopup = document.getElementById('settingsPopup');
        const closeButton = document.querySelector('.close-button');

        settingsPopup.style.display = 'none';

        closeButton.addEventListener("click", () => {
            settingsPopup.style.display = "none";
        })

        // Close the modal if the user clicks outside of it
        settingsLinks.addEventListener('click', (event) => {
            
            if (settingsPopup.style.display === "none") {
                settingsPopup.style.display = "block";
            } 
        });
        
    </script>


</body>
</html>