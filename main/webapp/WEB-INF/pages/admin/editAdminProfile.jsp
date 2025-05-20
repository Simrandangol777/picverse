<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>

<link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/admin/editAdminProfile.css">

</head>
<body>

    <div class="popup">
    	<span class="close-btn" onclick="window.close()"></span>
    	<h2>Edit Profile</h2>
    	<div class="form-group">
    		<label for="name">Name</label>
    		<input type="text" id="name" value="Aakriti Chaudhary">
    	</div>
    	
    	<div class="form-group">
    		<label for="email">Email</label>
    		<input type="email" id="email" value="aakriti.chaudhary@example.com">
    	</div>
    	
    	<div class="form-group">
    		<label for="role">Role</label>
    		<input type="text" id="role" value="Administrator" disabled>
    	</div>
    	
    	<div class="form-group">
    		<label for="member-since">Member Since</label>
    		<input type="text" id="member-since" value="January 15, 2023" disabled>
    	</div>
    	
    	<button class="save-btn">Save Changes</button>
    </div>

</body>
</html>