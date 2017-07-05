<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>	
	<h4>WAS : TOMCAT</h4>
	<h4>ORM : Mybatis</h4>
	<h4>DB : ${dbProfile}</h4>
	<c:if test="${not empty message}">
		Result Message : ${message}
	</c:if>
	<br/>	
	<h4>
		<a href="${context}/crudtest">
			Insert / Update / Delete / Select TEST
		</a>
	</h4>	
	<br/>


</body>
</html>