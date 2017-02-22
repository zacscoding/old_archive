<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Details</title>
</head>
<body>

<div align="center">
<h2>	${memberVO.user_id}(${memberVO.user_name})님의 개 인 정 보</h2>
<hr>
<table border="1" width="90%">
	<tr>
		<th>NO</th> <th>ID</th> <th>NAME</th> <th>EMAIL</th> <th>PHONE</th> <th>ADDRESS</th>
	</tr>		
	<tr>
	<td>${memberVO.user_no}</td><td>${memberVO.user_id}</td> <td>${memberVO.user_name}</td> <td>${memberVO.user_email}</td> 
	<td>${memberVO.user_phone}</td> <td>${memberVO.address}</td>
	</tr>	
</table>
<h2>기간 별 구매 내역 ,리뷰? 등등..</h2>
</div>

<form action="/admin/users/list">	
	<button type="submit" id="goListBtn">GO LIST </button>
</form>


<!-- 
<script>
	//jQuery
	$("#goListBtn ").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/users/list");
		formObj.submit();
	});
</script>
 -->

</body>
</html>