<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String code = request.getParameter("code");
	String viewPageURI = null;	
	
	if(code.equals("A")) {
		viewPageURI = "/viewModule/a.jsp";
	} else if(code.equals("B")) {
		viewPageURI = "/viewModule/b.jsp";
	} else if(code.equals("C")) {
		viewPageURI = "/viewModule/c.jsp";
	}
%>
<jsp:forward page="<%=viewPageURI%>">
	<jsp:param name="first" value="a"/>
	<jsp:param name="last" value="z"/>
</jsp:forward>