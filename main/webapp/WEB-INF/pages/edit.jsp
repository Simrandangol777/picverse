<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit your page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/edit.css">
</head>
<body>

	<!-- This is to create a new post.  -->
	<main class="edit-main">
		<h1 class="heading">Edit your post</h1>
		<br>

		<form action="edit" method="post" class="edit">
			<div class="edit-post">
				<!-- Hidden input to pass post ID -->
				<input type="hidden" name="id" value="${post.id}" />

				<!-- Caption textarea -->
				<textarea name="caption" class="edit-caption" rows="10" cols="30"
					placeholder="Enter caption or the detail of the post">${post.caption}</textarea>

				<img style="height: 250px; border-radius: 10px;"
					src="${pageContext.request.contextPath}/uploads/images/${post.image}"
					alt="Post Image" class="post-img" />

				<!-- File input (you can handle image editing later) -->
				<!-- <input type="file" class="post-img" accept="image/*" disabled>  -->
			</div>

			<button type="submit" class="submit-btn">Edit your post</button>
		</form>

	</main>

</body>
</html>