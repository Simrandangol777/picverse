<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="leftNavbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/create.css">
</head>
<body>

	<!-- This is to create a new post.  -->
	<main class="create-main">
		<h1 class="create-heading">Create a new post</h1>
		<br>

		<form class="create" action="create" method="post" enctype="multipart/form-data" required>
			<div class="create-post">
				<!-- Caption for the post.  -->
				<textarea name="caption" id="" class="create-caption" rows="10"
					cols="30" placeholder="Enter caption or the detail of the post"></textarea>

				<!-- Picture for the post.  -->
				<input type="file" name="image" class="post-img" accept="image/*" required>
			</div>


			<button class="submit-btn">Create a new post</button>
		</form>
	</main>


</body>
</html>