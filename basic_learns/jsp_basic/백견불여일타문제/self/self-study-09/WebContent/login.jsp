<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Face On Talk</title>
</head>
<body>
<form action="login.do" method="post">
	<div align="center">
		<table border="1" width="50%">
			<tr>
				<th colspan="2">로그인</th>
			</tr>
			<tr>
				<td align="center">아이디</td>
				<td><input type="text" name="id"></td>				
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="password"></td>				
			</tr>
			<tr>
				<td align="center">레벨</td>
				<td>
				<select name="lev">
					<option value="B">일반회원</option>
					<option value="A">관리자</option>					
				</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인"> &nbsp;
					<input type="reset" value="취소">
				</td>				
			</tr>
			<c:if test="${not empty message}">
			<tr>
				<td colspan="2" align="center">${message }</td>
			</tr>		
			</c:if>
		</table>
	
	</div>
</form>

<%@ include file="bottom.jsp" %>