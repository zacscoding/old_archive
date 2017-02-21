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
	
	<c:if test="${not empty msg}">
		<script>
			alert('${msg}');
		</script>
	</c:if>

	<form method="POST">
		<input type="hidden" name="user_no" value="${vo.user_no}">
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
			<td><input type="text" name="user_name" value="${vo.user_name}" readonly = 'readonly'></td>
		</tr>
		<tr>
			<td>email:</td>
			<td><input type="text" name="user_email" value="${vo.user_email}"></td>
		</tr>		
		<tr>
			<td>phone:</td>
			<td><input type="text" name="user_phone" value="${vo.user_phone}" readonly = 'readonly'></td>
		</tr>				
		<tr>
			<td>addr:</td>
			<td><input type="text" name="address" value="${vo.address}"></td>
		</tr>
		<tr>
			<td><input type="submit" value="변경"></td>
			<td><input type="reset" value="리셋"></td>
		</tr>
		</table>
	</form>
</body>
</html>