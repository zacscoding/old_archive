<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Face On Talk</title>
<script type="text/javascript" src="script/employee.js"></script>
</head>
<body>
<table class="mainmenu" align="center" border="1">
	<c:if test="${empty loginUser}">	
		<tr>
			<td style="width:20%"></td>
			<td style="width:20%"></td>					
			<td class="login" style="width:20%"><a name="login" href="login.do" style="text-decoration: none;">로그인</a></td>
			<td style="width:25%; align:right; " >사원 등록<br>
				<span style="color:red">(관리자로 로그인 후 사용 가능)</span>
			</td>
			<td style="width:25%">마이페이지<br>
				<span style="color:red">(로그인 후 사용 가능)</span>
			</td>
		</tr>	
	</c:if>
	<c:if test="${!empty loginUser }">
		<tr>
			<td style="width:15%"> ${loginUser.name}</td>
			<td style="width:15%"> Lev : ${loginUser.lev}</td>
			<td class="login" style="width:20%"> <a href="logout.do" style="text-decoration: none;">로그아웃</a></td>			
			<c:choose>
				<c:when test="${loginUser.lev=='A'}">
				 <td class="login" style="width:15%"><a href="custom.do" style="text-decoration: none;">사원 등록</a></td>
				 <td class="login" style="width:15%"><a href="list.do" style="text-decoration:none;">사원리스트</a></td>
				</c:when>
				<c:when test="${loginUser.lev=='B'}">
				 <td style="width:20%">사원 등록<br>
				 <span style="color:red">(관리자로 로그인 후 사용 가능)</span></td>
				</c:when>
			</c:choose>
			
			<td class="login">
			  <a href="mypage.do?id=${loginUser.id}" style="text-decoration: none;">마이페이지</a>
			</td>
		</tr>		
	</c:if>
	</table>
	<br><br><br><br><br><br><br><br>