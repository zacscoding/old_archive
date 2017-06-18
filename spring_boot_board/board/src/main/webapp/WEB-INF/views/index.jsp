<!-- temporary front page -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="content-wrapper">	
	<section class="content">

<h4>${msg}</h4>


TEMP INDEX PAGE !!!! <br>


login id : ${login.userId} <br>

<a href="/articles/create">새글쓰기</a>


</section>

</div>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>