<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp"%>
	<div align="center">
		<table border="1" style=width:50%;>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Level</th>				
				<th>Gender</th>
				<th>Phone</th>
			</tr>
			
			<c:forEach var="emp" items="${empList}">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.name}</td>
				<c:if test="${emp.lev=='A'}">
					<td>관리자</td>
				</c:if>
				<c:if test="${emp.lev=='B'}">
					<td>일반회원</td>
				</c:if>
				
				<c:if test="${emp.gender=='1'}">
					<td>남자</td>
				</c:if>
				<c:if test="${emp.gender=='2'}">
					<td>여자</td>
				</c:if>
				<td>${emp.phone}</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" align="center">
					<a href="main.jsp"><input type="button" value="메인페이지로 이동"></a>
				</td>
			</tr>			
		</table>		
	</div>	
<%@ include file="bottom.jsp"%>