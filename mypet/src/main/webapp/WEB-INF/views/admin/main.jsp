<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> Admin Main..</h1>


<c:url var="logoutUrl" value="/user/logout"/>
<sec:authorize access="isAuthenticated()">
	<li><a href="${logoutUrl}">로그아웃</a></li>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
</sec:authorize>

</body>
</html>