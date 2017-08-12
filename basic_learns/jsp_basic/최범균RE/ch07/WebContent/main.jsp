<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie = new Cookie("hi","hi");	
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
</head>
<body>

main.jsp에서 생성한 내용
<%
	request.setAttribute("hi","hi");
%>
<jsp:include page="sub.jsp" flush="false"/>

include 이후 내용
</body>
</html>