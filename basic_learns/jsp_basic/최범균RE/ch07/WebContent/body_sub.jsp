<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
body_sub에서 name 파라 미터 값 : 
<%=request.getParameter("name")%>
<br>
name 파라미터 값 목록 : 
<ul>
<%
	String[] names = request.getParameterValues("name");
	for(String name : names) {
%>
		<li><%=name%></li>
<%}%>
</ul>
