<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>

<head>
<title>Save User</title>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-user-style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Send Article </h2>
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
		<h3>Save Article</h3>

		<form:form class="form-group" action="saveArticle" modelAttribute="article" method="POST">

			<!-- need to associate this data with user id -->
			<form:hidden path="id" />

			<table>
				<tbody>
			
					<form:hidden path="sender" value="${user.id}"/>
					<tr>
						<td><label>Sender Name:</label></td>
						<td><input value="${user.userName}" required="required" autofocus="autofocus" disabled="disabled"/></td>
					</tr>
					
					<tr>
						<td><label>Receiver name:</label></td>
						
						<td><form:select class="form-control"  path="recevier" required="required" autofocus="autofocus">
						
							<c:if test="${article.id!=null }">
									<option value="<c:out value='${article.recevier.id}'/>">
										<c:out value='${article.recevier.userName}' /></option>
								</c:if>
								
								<c:if test="${article.id==null }">
									<option value="none" selected disabled hidden="true">Select
										an Option</option>
								</c:if>

								<c:forEach var="notLogin" items="${notLoginUser}">
									<c:if test="${article.recevier.id!=notLogin.id}">
										<form:option value="${notLogin.id}"><c:out value='${notLogin.userName}' /></form:option>

									</c:if>
							</c:forEach>

						</form:select><br>
					</tr>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path="title" required="required" autofocus="autofocus"/></td>
					</tr>
					
					<tr>
						<td><label>Cost:</label></td>
						<td><form:input path="cost" required="required" autofocus="autofocus"/></td>
					</tr>
					<tr>
					<tr>
						<td><label>Delivery Date:</label></td>
						<td><form:input type="date" path="deliveredDate" required="required" autofocus="autofocus"/></td>
					</tr>


					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
					

				</tbody>
			</table>


		</form:form>

		<p>
			<a href="${pageContext.request.contextPath}/article/senderArticlesList">Back to
				List</a>
		</p>

	</div>

</body>
</html>