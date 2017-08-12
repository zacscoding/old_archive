<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--상품 삭제 페이지 --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>

<div id="wrap" align="center">
<h1>상품 삭제 - 관리자 페이지</h1>
<hr id="wrap" color="blue" size="5" width="940">	

<form method="post" action="productDelete.do">
	<input type="hidden" name="code" value="${product.code}">
	<input type="hidden" name="nonmakeImg" value="${product.pictureUrl}">
	<table>
	<tr>
		<td>
			<c:choose>
				<c:when test="${empty product.pictureUrl}">
					<img src="upload/noimage.gif">
				</c:when>
				<c:otherwise>
					<img src="upload/${product.pictureUrl}">
				</c:otherwise>		
			</c:choose>
		</td>
		<td>
			<table>
				<tr>
					<th style="width:150px">상품명</th>	
					<td>${product.name}</td>
				</tr>	
				<tr>
					<th>가격</th>
					<td>${product.price}원</td>
				</tr>				
				<tr>
					<th>설 명</th>
					<td>${product.description}</td>
				</tr>
			</table>
		</td>
	</table>
	<br>
	<input type="hidden" name="code" value="${product.code}">
	<input type="submit" value="삭제">	
	<input type="button" value="목록" onclick="location.href='productList.do'">	
</form>
</div>
