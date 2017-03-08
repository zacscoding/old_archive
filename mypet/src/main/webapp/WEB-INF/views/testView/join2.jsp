<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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