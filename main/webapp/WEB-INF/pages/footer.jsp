<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<footer class="footer-main">
		<div class="friend-heading">
			<h2>Your Friends</h2>
			<a href="${pageContext.request.contextPath}/follow">See All</a>
		</div>

		<div class="friend-list">
			<c:choose>
				<c:when test="${not empty sessionScope.userId}">
					<c:choose>
						<c:when test="${not empty followingList}">
							<c:forEach var="friend" items="${followingList}">
								<a
									href="${pageContext.request.contextPath}/profile?username=${friend.username}"
									class="friend-link"
									style="text-decoration: none; color: inherit;">
									<div class="friend">
										<img class="friend-img"
											src="${pageContext.request.contextPath}/uploads/images/${friend.profilePicture}"
											alt="${friend.username}" />
										<h3>${friend.username}</h3>
									</div>
								</a>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="friend">
								<h3>No Friends</h3>
							</div>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<div class="friend">
						<h3>No Friends</h3>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- This is for the footer topic.  -->
		<div class="footer-text">
			<p>
				<span>About</span> . &nbsp;
			</p>
			<p>
				<span>Help</span> .&nbsp;
			</p>
			<p>
				<span>Press</span> .&nbsp;
			</p>
			<p>
				<span>API</span> .&nbsp;
			</p>
			<p>
				<span>Jobs</span> .&nbsp;
			</p>
			<p>
				<span>Privacy</span> .&nbsp;
			</p>
			<p>
				<span>Terms</span> .&nbsp;
			</p>
			<p>
				<span>Locations</span> .&nbsp;
			</p>
			<p>
				<span>Language</span> .&nbsp;
			</p>
			<p>
				<span>picVerse Verified</span> .&nbsp;
			</p>
		</div>

		<!-- This is the last footer of the picverse.  -->
		<p class="footer-last">&copy; 2025 picVerse</p>
	</footer>
</body>
</html>
