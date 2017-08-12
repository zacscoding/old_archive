<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="top.jsp" %>
	<div align="center">	
		<table border="1" style=width:50%;>
			<tr>
				<td colspan="2" align="center"><h4>사원정보</h4>
					<br>${message}
				</td>
			</tr>
			<tr>
				<td align="center">아이디</td>
				<td align="center">${loginUser.id}</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td align="center">${loginUser.password}</td>
			</tr>
			<tr>
				<td align="center">이름</td>
				<td align="center">${loginUser.name}</td>
			</tr>
			<tr>
				<td align="center">권한</td>
				
				<c:if test="${loginUser.lev=='A'}">						
				<td align="center">관리자</td>
				</c:if>
				
				<c:if test="${loginUser.lev=='B'}">
				<td align="center">일반회원</td>				
				</c:if>					
			</tr>
			<tr>
				<td align="center">성별</td>
				
				<c:if test="${loginUser.gender=='1'}">						
				<td align="center">남자</td>
				</c:if>				
				<c:if test="${loginUser.gender=='2'}">
				<td align="center">여자</td>				
				</c:if>					
			</tr>
			<tr>
				<td align="center">전화번호</td>
				<td align="center">${loginUser.phone}</td>			
			</tr>	
			<tr>
				<td colspan="2" align="center">
					<a href="main.jsp"><input type="button" value="메인 페이지로 이동"></a>
				</td>
			</tr>		
		</table>	
	</div>
<%@ include file="bottom.jsp"%>