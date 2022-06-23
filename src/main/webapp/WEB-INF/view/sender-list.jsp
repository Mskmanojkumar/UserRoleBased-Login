<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<meta charset="ISO-8859-1">
<title>Sender List</title>
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2> Article List</h2>
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
				access="hasAnyRole('MANAGER','ROLE_GUEST','GUEST')">
				
				<input type="button" value="Add Article"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button" />

				<table>
					<tr>
						<th>Receiver Name</th>
						<th>Title</th>
						<th>Cost</th>
						<th>Creation Time</th>
						<th>Delivery date</th>
						<th>Action</th>
					</tr>

						<!-- loop over and print our Article -->
						<c:forEach var="tempArticle" items="${senderArticles}">	
						
						<!-- construct an "update" link with Article id -->
						<c:url var="updateLink" value="/article/showFormForUpdate">
							<c:param name="articleId" value="${tempArticle.id}" />
						</c:url>
						
						<!-- construct an "delete" link with Article id -->
						<c:url var="deleteLink" value="/article/deleteArticle">
							<c:param name="articleId" value="${tempArticle.id}" />
						</c:url>
						<tr>
							<td>${tempArticle.recevier.userName}</td>
							<td>${tempArticle.title}</td>
							<td>${tempArticle.cost}</td>
							<td>${tempArticle.creationDate}</td>
							<td>${tempArticle.deliveredDate}</td>
							<td>
								<!-- display the update link --> <a href="${updateLink}">Update</a>
								| <a href="${deleteLink}"
								onclick="if (!(confirm('Are you sure you want to delete this Article?'))) return false">Delete</a>
							</td>

						</tr>

					</c:forEach>
			
					</table>
						<p>
				<a href="${pageContext.request.contextPath}/">Back to
				Home</a>
				</p>
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