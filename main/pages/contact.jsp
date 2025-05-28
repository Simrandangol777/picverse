<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contact.css" />
</head>
<body>

	<section class="contact-section">
		<div class="contact-header">
			<h2>SEND US A MESSAGE</h2>
		</div>

		<div class="contact-form-wrapper">
			<form class="contact-form" action="contact" method="post">
				<div class="contact-row">
					<div class="contact-input-group">
						<label for="firstName">NAME</label> <input type="text"
							id="firstName" name="name">
					</div>
				</div>
				<div class="contact-row">
					<div class="contact-input-group">
						<label for="phone">PHONE</label> <input type="number" id="phone"
							name="phone">
					</div>
					<div class="contact-input-group">
						<label for="email">EMAIL</label> <input type="text" id="email"
							name="email">
					</div>
				</div>
				<div class="contact-input-group contact-full">
					<label for="subject">SUBJECT</label> <input type="text"
						id="subject" name="subject">
				</div>
				<div class="contact-input-group contact-full">
					<label for="message">MESSAGE</label>
					<textarea id="message" name="message"></textarea>
				</div>
				<button class="contact-submit-button" type="submit">SEND
					MESSAGE</button>
			</form>
		</div>
	</section>



</body>
</html>