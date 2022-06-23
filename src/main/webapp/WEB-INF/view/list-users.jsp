<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>

<html>

<head>
<title>List Users</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>RoleBase Login Form</h2>
		</div>
	</div>
	<!-- Displaying the user roles and Name who is logged in -->
	<p>
		User :
		<security:authentication property="principal.username" />
		<br> 
		Role
		<security:authentication property="principal.authorities" />
	</p>



	<div id="container">

		<div id="content">

			<security:authorize
				access="hasAnyRole('MANAGER','ROLE_ADMIN','ADMIN')">
				<!-- Special access to Managers -->
				<!-- put new button: Add Customer -->

				<input type="button" value="Add User"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button" />


				<!--  add our html table here -->

				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>UserName</th>
						<th>Address</th>
						<th>UserRole</th>
						<th>Action</th>
					</tr>

					<!-- loop over and print our customers -->
					<c:forEach var="tempUser" items="${users}">

						<!-- construct an "update" link with User id -->
						<c:url var="updateLink" value="/admin/showFormForUpdate">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>

						<!-- construct an "delete" link with user  id -->
						<c:url var="deleteLink" value="/admin/delete">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>
						<!-- construct an "passwordChange" link with User id -->
						<c:url var="passwordChangeLink" value="/admin/changePassword">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>
						<tr>
							<td>${tempUser.firstName}</td>
							<td>${tempUser.lastName}</td>
							<td>${tempUser.email}</td>
							<td>${tempUser.userName}</td>
							<td>${tempUser.address}</td>
							<td>${tempUser.role.name}</td>
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								| <a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this User?'))) return false">Delete</a>
								|<a href="${passwordChangeLink}">Change Password</a>
							</td>

						</tr>

					</c:forEach>

				</table>
			</security:authorize>
			<br> <br>
			<!-- put new button:  Logout -->
			<div>
				<!-- Add logout button  -->
				<f:form action="${pageContext.request.contextPath}/logout"
					method="POST">
					<input type="submit" value="Logout" class="add-button">
				</f:form>
			</div>

		</div>

	</div>


</body>

</html>









