<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Role</title>
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
	
	<form:form class="form-group" action="saveRole" modelAttribute="role"
		method="POST">
			
		<table>
			<tbody>
			
				<tr>					
				<td><label>Role</label></td>
					<td><form:input path="name" placeholder="Enter the role"
							required="required" autofocus="autofocus" />Upper case only Example: ROLE_ROLENAME</td>
				</tr>
				<tr>
					<td><label></label></td>
					<td><input type="submit" value="Save" class="save" /></td>
				</tr>


			</tbody>
		</table>


	</form:form>

	<a href="${pageContext.request.contextPath}/admin/showFormForAdd">Back
		to List</a>

</body>
</html>