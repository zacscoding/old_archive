<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Face On Talk</title>
</head>
<body>


<c:if test="${not empty msg}">
	<script>
		alert('${msg}');
	</script>
</c:if>


<form action="/accounts/join" method="POST">
	<table border="1">
		<tr>
			<th>ID : </th>
			<td><input type="text" name="user_id"></td>
		</tr>
		<tr>
			<th>Password : </th>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<th>Email : </th>
			<td><input type="text" name="user_email"></td>
		</tr>
		<tr>
			<th>PHONE : </th>
			<td><input type="text" name="phone"></td>
		</tr>
	</table>
	<br/>
	<input type="submit" value="JoinUs">
</form>

</body>
</html>