<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/editProfile.css">
</head>
<body>
	<div class="profiles-container">
		<div class="profiles-header">
			<h1>Edit Profile</h1>
		</div>

		<div class="profiles-edit-section">


			<form class="profiles-form" action="edit-profile" method="post"
				enctype="multipart/form-data">
				<div class="profiles-picture-section">
					<div class="profiles-picture">
						<input type="hidden" name="currentImage"
							value="${user.profilePicture}" /> 
						<img id="profile-preview"
							src="${pageContext.request.contextPath}/uploads/images/${user.profilePicture}"
							alt="User Profile Picture">
						<div class="profiles-picture-overlay">
							<i class="fas fa-camera"></i>
						</div>
					</div>


					<input type="file" id="profile-upload" class="profiles-upload"
						accept="image/*" name="profilePicture"> <label
						for="profile-upload" class="profiles-upload-btn"> Change
						Photo </label>
				</div>

				<input type="hidden" name="userId" value="${user.id}">
				<div class="profiles-form-row">
					<div class="profiles-form-group">
						<label for="name">Full Name</label> <input type="text"
							id="fullName" name="name" value="${user.name}"
							placeholder="Enter your full name" class="profiles-input">
					</div>

					<div class="profiles-form-group">
						<label for="username">Username</label> <input type="text"
							id="username" name="username" value="${user.username}"
							class="profiles-input" disabled>
					</div>
				</div>

				<div class="profiles-form-row">
					<div class="profiles-form-group">
						<label for="email">Email</label> <input type="email" id="email"
							name="email" value="${user.email}" class="profiles-input" disabled>
					</div>
					<div class="profiles-form-group">
						<label for="phone">Phone Number</label> <input type="number"
							id="phone" name="phone" value="${user.phone}"
							class="profiles-input">
					</div>
				</div>

				<div class="profiles-form-row">
					<div class="profiles-form-group">
						<label for="location">Location</label> <input type="text"
							id="location" name="location" value="${user.location}"
							class="profiles-input">
					</div>
					<div class="profiles-form-group">
						<label for="hobby">Hobby</label> <input type="text" id="website"
							name="website" value="${user.hobby}" class="profiles-input">
					</div>
				</div>

				<div class="profiles-form-row full-width">
					<div class="profiles-form-group">
						<label for="bio">Bio</label>
						<textarea id="bio" name="bio" class="profiles-textarea">${user.bio}</textarea>
					</div>
				</div>

				<div class="profiles-form-actions">
					<button type="button" class="profiles-btn profiles-btn-cancel">Cancel</button>
					<button type="submit" class="profiles-btn profiles-btn-save">Save
						Changes</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>