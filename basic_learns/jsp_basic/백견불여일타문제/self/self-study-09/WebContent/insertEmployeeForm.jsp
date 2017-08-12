<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:if test="${empty loginUser}">
		<jsp:forward page='login.do'/>
</c:if>
<%@ include file="top.jsp"%>
	<form action="custom.do" method="post">
		<div align="center">
			<table border="1" style=width:50%;>
				<tr>
					<td colspan="2" align="center">사 원 등 록</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>			
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>			
				</tr>
				<tr>
					<td>권한</td>					
					<td>
						<select name="lev">
							<option value="A">관리자</option>
							<option value="B">일반</option>
						</select>
					</td>									
				</tr>
				<tr>
					<td>성별</td>										
					<td>	
						<select name="gender">
							<option value="1">남자</option>
							<option value="2">여자</option>
						</select>
					</td>										
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone"></td>			
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="submit" value="등록">
						<input type="reset" value="취소">
						<a href="main.jsp"><input type="button" value="메인페이지로 이동"></a>
					</td>
				</tr>
			</table>
		</div>	
	</form>
<%@ include file="bottom.jsp"%>