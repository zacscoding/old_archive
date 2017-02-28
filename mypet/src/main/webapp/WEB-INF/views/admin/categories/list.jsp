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

<h3>Animal List..</h3>
<table border="1">
<tr>
	<th>NO</th> <th>NAME</th>
</tr>	
<c:if test="${empty animalList}">
	<td colspan="2">등록된 동물이 없습니다.</td>
</c:if>
<c:forEach var="animalVO" items="${animalList}">
	<tr>
		<td>${animalVO.animal_no}</td>
		<td>${animalVO.animal_name}</td>
	</tr>
</c:forEach>
</table>

<br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/>


<h3>Product Category List..</h3>
<table border="1">
<tr>
	<th>NO</th> <th>NAME</th> <th>animal_no</th> <th>animal_name</th>
</tr>	
<c:if test="${empty categoryList}">
	<td colspan="4">등록된 카테고리가 없습니다.</td>
</c:if>
<c:forEach var="categoryVO" items="${categoryList}">
	<tr>
		<td>${categoryVO.category_no}</td>
		<td>${categoryVO.cate_name}</td>
		<td>${categoryVO.animal_no_fk}</td>
		<td>${categoryVO.animal_name_fk}</td>
	</tr>
</c:forEach>
</table>

</body>
</html>