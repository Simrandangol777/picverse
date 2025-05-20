<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/dashboard.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/adminSidebar.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<style>
    .pie-chart-container {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100%;
      }
      .pie-chart-canvas {
        width: 400px !important; /* Make it responsive */
        height: 400px !important;
      }
</style>


</head>
<body>
	
	<%@ include file="adminSidebar.jsp" %>
	
	<div class="dashboard-main">
		<div class="dashboard-topbar">
			<div class="search-bar">
				<input type="text" placeholder="Search...">
			</div>
			
			<div class="user-info">
				<a href="${pageContext.request.contextPath}/admin-profile">
					<i class="fa fa-user-circle" style="font-size: 30px; color: white;"></i>
					<div>Admin Profile</div>
					
				</a>
			</div>
		</div>
		
		<div class="dashboard-section">
			<h2>Dashboard</h2>
			<div class="cards">
				<div class="card">
					<h3><i class="fa fa-users"></i>Total Users</h3>
					<p><c:out value="${totalUsers}" /></p>
				</div>
				
				<div class="card">
					<h3><i class="fa fa-image"></i>Total Posts</h3>
					<p><c:out value="${totalPosts}" /></p>
				</div>
				
				<div class="card">
					<h3><i class="fa fa-heart"></i>Total Likes</h3>
					<p><c:out value="${totalLikes}" /></p>
				</div>
			</div>
		</div>
		
		<div class="pie-section">
			<h2>Pie Chart</h2> 
			
			<div class="pie-chart-container">
              <canvas id="userPostPieChart" class = "pie-chart-canvas"></canvas>
            </div>
		</div>
	</div>
	
	
	<script>
        // Get the data from JSP attributes (sent by the servlet)
        const totalUsers = parseInt("${totalUsers}");
        const totalPosts = parseInt("${totalPosts}");
        const totalLikes = parseInt("${totalLikes}");

        // --- Pie Chart ---
        const pieCtx = document.getElementById('userPostPieChart').getContext('2d');
        const userPostPieChart = new Chart(pieCtx, {
            type: 'pie',
            data: {
                labels: ['Users', 'Posts', 'Likes'],
                datasets: [{
                    label: 'Total',
                    data: [totalUsers, totalPosts, totalLikes],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',  // User color
                        'rgba(54, 162, 235, 0.6)',    // Post color
                        'rgba(255, 206, 86, 0.6)'     // Likes color
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                  title: {
                    display: true,
                    text: 'Users vs Posts vs Likes',
                    font: {
                      size: 16
                    }
                  },
                  legend: {
                    position: 'bottom'
                  }
                }
            }
        });
    </script>

</body>
</html>




