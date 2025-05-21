<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>About Us</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/aboutUs.css" />
</head>
<body>
	<div class="container">
		<h1>
			<b>About Us</b>
		</h1>
		
			<h2>
				<b>Picverse</b>
			</h2>
				<p class="short-info">
				Picverse is a web application designed for sharing images and posts, similar to a social media platform. 
				Users can create an account, upload and view images, write captions, and interact with other users' content. 
				The app typically features user profiles, post creation, and basic error handling for actions like deleting posts. 
				It's often built using technologies like JSP, Servlets, HTML, CSS, and a backend database to manage users and posts.
				</p>
				
		
		<h1>
			<b>Members</b>
		</h1>

		<div class="team-member">
			<h2>
				<b>Nabin Bista</b>
			</h2>
			<div class="member-details">
			<div class="profile-image">
					<img
						src="${pageContext.request.contextPath}/resources/Images/nabin.JPG"
						alt="Nabin Bista" />
				</div>
				<p class="short-info">Nabin brings leadership and innovation to
					the team, with a strong background communication.</p>
			</div>
		</div>

		<div class="team-member">
			<h2>
				<b>Simran Dangol</b>
			</h2>
			<div class="member-details">
				<p class="short-info">Simran brings creativity and precision to
					front-end design, with a flair for responsive layouts and
					accessibility.</p>
				<div class="profile-image">
					<img src="${pageContext.request.contextPath}/resources/Images/simran.JPG" alt="Simran Dangol" />
				</div>
			</div>
		</div>

		<div class="team-member layout-1">
			<h2>
				<b>Prarthana Shahi</b>
			</h2>
			<div class="member-details">
			<div class="profile-image">
				<img src="${pageContext.request.contextPath}/resources/Images/prarthana.jpeg" alt="Prarthana Shahi" />
			</div>
				<p class="short-info">Prarthana excels in project coordination
					and quality assurance, ensuring that all team goals are met
					smoothly.</p>
			</div>
		</div>

		<div class="team-member layout-2">
			<h2>
				<b>Aakriti Chaudhary</b>
			</h2>
			<div class="member-details">
				<p class="short-info">Aakriti is known for her problem-solving
					skills in back-end development and API integration.</p>
				<div class="profile-image">
					<img src="${pageContext.request.contextPath}/resources/Images/aakriti.jpeg" alt="Aakriti Chaudhary" />
				</div>
			</div>
		</div>

		<div class="team-member layout-1">
			<h2>
				<b>Anushka Adhikari</b>
			</h2>
			<div class="member-details">
			<div class="profile-image">
				<img src="${pageContext.request.contextPath}/resources/Images/anuskha.jpeg" alt="Anushka Adhikari" />
			</div>
				<p class="short-info">Anushka specializes in UI design and
					brings visual harmony and user-centric design to every project.</p>
			</div>
		</div>

		<div class="team-member layout-2">
			<h2>
				<b>Santosh Singh Chad</b>
			</h2>
			<div class="member-details">
				<p class="short-info">Santosh is a passionate developer focused
					on innovative UI/UX solutions.</p>
				<div class="profile-image">
					<img src="${pageContext.request.contextPath}/resources/Images/satish.JPG" alt="Santosh Singh Chad" />
				</div>
			</div>
		</div>
	</div>


</body>
</html>
