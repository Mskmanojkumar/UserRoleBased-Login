<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-user-style.css">
	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>User Password Change</h2>
		</div>
	</div>
	
	<div id="container">
	<div class="container col-md-5">
		<h3>Change Password</h3>
		<c:if test="${error=='error'}">

					<div class="alert alert-info">New password and confirm password should be same
						</div>
			</c:if>

		<form:form class="form-group" action="changePassword" modelAttribute="user" method="POST">

			<!-- need to associate this data with user id -->
			<form:hidden path="id" />

			<table>
				<tbody>
				<tr>
						<td><label>UserName:</label></td>
						<td><input value="${user.userName}" autofocus="autofocus" required="required" disabled="disabled"/></td>
				</tr>
		
				<tr>
						<td><label>Enter New password :</label></td>
						<td><input  type="password" name="newPwd" autofocus="autofocus" required="required" /></td>
					
				</tr>
				<tr>
						<td><label>Confirm New Password :</label></td>
						<td><input  type="password" name="confirmPwd" autofocus="autofocus" required="required" /></td>
				</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" />||<a href="${pageContext.request.contextPath}/admin/list">Cancel</a></td>
					</tr>
					<tr>
					
				</tr>
				</tbody>
			</table>


		</form:form>
		
	</div>
</div>

</body>
</html>