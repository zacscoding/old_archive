<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>INDEX</title>
</head>
<body>

<c:if test="${not empty msg}">
	<script>
		alert('${msg}');
	</script>
</c:if>


<div align="center">
<h2>INDEX PAGE</h2>
<a href='/admin/main'>/admin/adminMain</a> <br/>	
<a href='/user/main'>/user/main</a> <br/>
</div>
	

</body>
</html>