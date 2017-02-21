<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회 원 관 리</title>
</head>
<body>

<h3>회 원 목 록</h3>

<div align="center">
<table border="1">
	<tr>
		<th>NO</th> <th>ID</th> <th>NAME</th> <th>EMAIL</th> <th>PHONE</th> <th>ADDRESS</th>
	</tr>
		
	<c:forEach var="memberVO" items="${list}">
		<tr>
		<td>1</td> <td>${memberVO.user_id}</td> <td>${memberVO.user_name}</td> <td>${memberVO.user_email}</td> 
		<td>${memberVO.user_phone}</td> <td>${memberVO.address}</td>
		</tr>	
	</c:forEach>		
</table>
</div>

<div align="center">	
	<!-- 페이징 -->	
	<!-- prev -->
	<c:if test="${pageMaker.prev}">
		<a href="list${pageMaker.makeSearch(pageMaker.startPage-1}"> [prev] </a>
	</c:if>
		
	<!-- page num -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<a href="list${pageMaker.makeSearch(idx)}">[${idx}]</a>		
	</c:forEach>

	<!-- next -->
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		<a href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">[next]</a>
	</c:if>
</div>

</body>
</html>