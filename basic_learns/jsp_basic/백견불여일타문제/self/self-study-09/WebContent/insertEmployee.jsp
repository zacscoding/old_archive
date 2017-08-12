<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:if test="${empty loginUser}">
		<jsp:forward page='login.do'/>
</c:if>
<%@ include file="top.jsp"%>	
	<div align="center">
		<table border="1" style=width:50%;>
			<tr>
				<td colspan="2" align="center"><h3>사 원 정보</h3><br>회원 등록에 성공하였습니다.</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${employee.id}</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>${employee.password}</td>			
			</tr>
				<tr>
				<td>이름</td>
				<td>${employee.name}</td>			
			</tr>
			<tr>
				<td>권한</td>					
				<c:if test="${employee.lev=='A'}">
				<td>관리자</td>		
				</c:if>
				<c:if test="${employee.lev=='B'}">
				<td>일반회원</td>		
				</c:if>													
			</tr>
			<tr>
				<td>성별</td>										
				<c:if test="${employee.gender=='1'}">
				<td>남자</td>		
				</c:if>
				<c:if test="${employee.gender=='2'}">				
				<td>여자</td>	
				</c:if>
			<tr>
			<td>전화번호</td>
				<td>${employee.phone}</td>			
			</tr>
			<tr align="center">
				<td colspan="2">					
					<a href="main.jsp"><input type="button" value="메인페이지로 이동"></a>
				</td>
			</tr>
		</table>
		</div>	
<%@ include file="bottom.jsp"%>