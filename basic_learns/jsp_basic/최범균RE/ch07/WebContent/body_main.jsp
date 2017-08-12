<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

include 전 name 파라미터 값
<ul>
<%
	String[] names = request.getParameterValues("name");	
	for(String name : names) {
%>
		<li><%=name%></li>
<%}%>
</ul>
<hr>
<jsp:include page="body_sub.jsp" flush="false">
	<jsp:param name="name" value="zac"/>
	<jsp:param name="name" value="coding"/>
</jsp:include>
<hr>

include 후 name 파라 미터 값
<ul>
<%
	String[] names2 = request.getParameterValues("name");
	for(String name : names2) {
%>
		<li><%=name%></li>
<%}%>
</ul>
</body>
</html>