<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="context" value="${pageContext.request.contextPath}" />    
<script>
	document.location.href = "${context}";
</script>    
