<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />      
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Crud Test</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

	<c:forEach var="dto" items="${dtoList}">
		${dto.name}
		<input type="checkbox" name="${dto.name}" value="${dto.name}"/>
		<c:forEach var="column" items="${column.}">
		${column }
		</c:forEach>
	</c:forEach>
	<button class="btnInsert" data-cnt="1">1개 등록</button>
		

<script>
$(function() {
	$('.btnInsert').on('click', function(e) {
		
		
		
	});
	
	
	
	
	
});

</script>	
	
</body>
</html>