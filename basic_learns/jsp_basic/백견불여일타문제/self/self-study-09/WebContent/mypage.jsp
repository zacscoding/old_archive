<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty loginUser}">
		<jsp:forward page='login.do'/>
</c:if>
<%@ include file="top.jsp" %>
<div align="center">
	<form method="post">
		<table border="1" style=width:50%;>
			<tr>
				<td colspan="2" align="center">My Page</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${loginUser.id}" readonly></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" value="${loginUser.password}" readonly></td>			
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${loginUser.name}" readonly></td>			
			</tr>
			<tr>
				<td>권한</td>
				<c:if test="${loginUser.lev=='A'}">						
				<td>	
					<select name="lev">
						<option value="A">관리자</option>
						<option value="B">일반</option>
					</select>
				</td>
				</c:if>
				<c:if test="${loginUser.gender=='B'}">
				<td>	
					<select name="gender">
						<option value="B">일반</option>
						<option value="A">관리자</option>					
					</select>
				</td>
				</c:if>					
			</tr>
			<tr>
				<td>성별</td>
				<c:if test="${loginUser.gender==1}">						
				<td>	
					<select name="gender">
						<option value="1">남자</option>
						<option value="2">여자</option>
					</select>
				</td>
				</c:if>
				<c:if test="${loginUser.gender==2}">
				<td>	
					<select name="gender">
						<option value="2">여자</option>
						<option value="1">남자</option>					
					</select>
				</td>
				</c:if>						
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" value="${loginUser.phone}"></td>			
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
	<c:if test="${message}">
		<script type="text/javascript">
			alert(${message})		
		</script>
	</c:if>
</div>
<%@ include file="bottom.jsp"%>