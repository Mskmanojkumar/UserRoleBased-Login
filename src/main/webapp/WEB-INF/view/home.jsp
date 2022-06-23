
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System User</title>
</head>
<body>


	welcome
	<hr>
	User:
	<security:authentication property="principal.username" />
	<br>
	<br> 
	Role:
	<security:authentication property="principal.authorities" />
	<security:authorize access="hasAnyRole('GUEST','MANAGER')">
	<hr>
	<a href="${pageContext.request.contextPath}/article/senderArticlesList">SenderArticleList</a><br>
	<a href="${pageContext.request.contextPath}/article/receivedArticlesList">ReceiverArticleList</a>
	</security:authorize>
	<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
		<!-- Add a link to point to /admin -->
		<hr>
		<p>

			<a href="${pageContext.request.contextPath}/admin">ADMIN</a>
		</p>
	</security:authorize>
	<hr>





	<!-- put new button:  Logout -->
	<div>
		<!-- Add logout button  -->
		<f:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="Logout" class="add-button">
		</f:form>
	</div>
</body>
</html>