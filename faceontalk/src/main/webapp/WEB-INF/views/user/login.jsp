<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login...</title>
</head>
<body>
	<h2>Temp Login Page...</h2><br/>
<form action="/user/loginPost" method="post">
	<table border="1">
		<tr>
			<th>ID:</th>
			<td><input type="text" name="user_id"></td>
		</tr>
		<tr>
			<th>Password:</th>
			<td><input type="password" name="password"></td>
		</tr>
	</table>
	<br/>
	<input type="submit" value="Login">
</form>
</body>
</html>