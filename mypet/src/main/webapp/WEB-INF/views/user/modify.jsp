<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<form method="POST">
		<table border="1">		
		<tr>
			<td>ID:</td>
			<td><input type="text" name="user_id" value="${vo.user_id}" readonly = 'readonly'></td>
		</tr>
		<tr>
			<td>PASSWORD:</td>
			<td><input type="button" value="비밀번호변경">
		</tr>	
		
		<tr>
			<td>name</td>
			<td>${vo.user_name}</td>
		</tr>
		<tr>
			<td>email:</td>
			<td>${vo.user_email}</td>
		</tr>		
		<tr>
			<td>phone:</td>
			<td>${vo.user_phone}</td>
		</tr>
		<tr>
			<td>ID:</td>
			<td>${vo.user_id}</td>
		</tr>		
		<tr>
			<td>addr:</td>
			<td>${vo.address}</td>
		</tr>
		</table>
	</form>
</body>
</html>