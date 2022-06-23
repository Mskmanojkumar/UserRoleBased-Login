<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<h2>RoleBase Login Form</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save User</h3>

		<form:form class="form-group" action="saveUser" modelAttribute="user" method="POST">

			<!-- need to associate this data with user id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" required="required" autofocus="autofocus"/></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" required="required" autofocus="autofocus"/></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><form:input path="email" required="required" autofocus="autofocus"/></td>
					</tr>
					
					<tr>
						<td><label>UserName:</label></td>
						<td><form:input path="userName"  autofocus="autofocus" required="required"/></td>
					</tr>
					<c:if test="${user.id==null }">
					<tr>
						<td><label>PassWord:</label></td>
						<td><form:input type="password" path="password" required="required" autofocus="autofocus"/></td>
					</tr>	
					</c:if>		
					<c:if test="${user.id!=null }">
					<tr>
						<td><form:hidden path="password" required="required" autofocus="autofocus"/></td>
					</tr>	
					</c:if>	
					<tr>
						<td><label>Address:</label></td>
						<td><form:input type="address" path="address" required="required" autofocus="autofocus"/></td>
					</tr>
					<tr>
						<td><label>Role:</label></td>

						<td><select class="form-control" id="testOder" name="role" required="required" autofocus="autofocus">
								<c:if test="${user!=null }">
									<option value="<c:out value='${user.role.id}'/>">
										<c:out value='${user.role.name}' /></option>
								</c:if>

								<c:if test="${user.id==null }">
									<option value="none" selected disabled hidden="true">Select
										an Option</option>
								</c:if>

								<c:forEach var="role" items="${role}">
									<c:if test="${user.role.id!=role.id}">
										<option value="${role.id}">
											<c:out value='${role.name}' /></option>

									</c:if>
								</c:forEach>

						</select><br>
						<c:if test="${user.id==null }">
						<input type="button" value="Add Role"
						onclick="window.location.href='addrole'; return false;"
						class="add-button" />
						</c:if>	
						</td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>


				</tbody>
			</table>


		</form:form>
		<p>
			<a href="${pageContext.request.contextPath}/admin/list">Back to
				List</a>
		</p>

	</div>

</body>

</html>










