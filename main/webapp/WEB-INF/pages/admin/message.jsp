<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin/message.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">
</head>
<body>

	<%@ include file="adminSidebar.jsp"%>
	<%@ include file="searchBar.jsp"%>
	<div class="message-container">
		<h1 class="message-heading">Contact Messages</h1>
		<table class="message-table">
			<thead class="message-thead">
				<tr>
					<th class="message-th">ID</th>
					<th class="message-th">Name</th>
					<th class="message-th">Phone Number</th>
					<th class="message-th">Email</th>
					<th class="message-th">Subject</th>
					<th class="message-th">Message</th>
				</tr>
			</thead>
			<tbody class="message-tbody">
				<c:choose>
					<c:when test="${not empty contactList}">
						<c:forEach var="contact" items="${contactList}">
							<tr class="message-tr">
								<td class="message-td">${contact.id}</td>
								<td class="message-td">${contact.name}</td>
								<td class="message-td">${contact.phoneNumber}</td>
								<td class="message-td">${contact.email}</td>
								<td class="message-td">${contact.subject}</td>
								<td class="message-td">${contact.message}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="message-tr">
							<td class="message-td" colspan="6">No messages found.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

</body>
</html>