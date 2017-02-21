<%-- Temp Page --%>

<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
</head>
<body>

<c:if test="${not empty msg}">
		<script>
			alert('${msg}');
		</script>
</c:if>

<div align="center">

<h2> Join Us..</h2>
<form method="post">
	<table border="1">
		<tr>
			<td>ID:</td>
			<td><input type="text" name="user_id"></td>
		</tr>
		<tr>
			<td>PW:</td>
			<td><input type="password" name="user_password"></td>
		</tr>
		<tr>
			<td>NAME:</td>
			<td><input type="text" name="user_name"></td>
		</tr>
		<tr>
			<td>EMAIL</td>
			<td><input type=text name="user_email" value="test@test.com"></td>
		</tr>
		<tr>
			<td>PHONE:</td>
			<td><input type="text" name="user_phone" value="010-111-1111"></td>
		</tr>
		<tr>
			<td>POSTCODE</td>
			<td><input type="text" name="postcode_fk" value="111-111"></td>
		</tr>
		<tr>
			<td>ADDRESS</td>
			<td><input type="text" name="address" value="address"></td>
		</tr>
		<tr>
		<td colspan="2"><input value="JOIN" type="SUBMIT"></td>
		</tr>		
	</table>	
</form>
</div>
</body>
</html>