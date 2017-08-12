<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty loginUser}">
		<jsp:forward page='login.do'/>
</c:if>
<%@ include file="top.jsp"%>
	<div align="center">
		<h4>${loginUser.name}님 환영합니다.</h4>
	</div>
<%@ include file="bottom.jsp"%>