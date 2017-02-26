<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회 원 관 리</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

<div align="center">
	<h3>회 원 목 록</h3>

		<!-- 검색  -->
		<div>
			<form action="/admin/users/list">
			<select name="searchType">
				<!-- n : 검색 조건 없음 -->
				<option value="n"
					<c:out value="${cri.searchType==null?'selected':''}"/>>
					---</option>

				<!-- t : 회원 ID로 검색 -->
				<option value="i"
					<c:out value="${cri.searchType eq 'i'?'selected':''}"/>>
					ID</option>

				<!--  이름으로 검색  -->
				<option value="n"
					<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
					NAME</option>

				<!-- 이메일로 검색  -->
				<option value="e"
					<c:out value="${cri.searchType eq 'e'?'selected':''}"/>>
					EMAIL</option>

				<!-- 핸드폰으로 검색 -->
				<option value="p"
					<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
					PHONE</option>				
			</select> <input type="text" name='keyword' id="keywordInput"
				value="${cri.keyword}">
			<button id='searchBtn'>Search</button>
			</form>			
		</div>


<table border="1" width="70%">
	<tr>
		<th>NO</th> <th>ID</th> <th>NAME</th> <th>EMAIL</th> <th>PHONE</th> <th>ADDRESS</th>
	</tr>
	<c:if test="${empty list}">
		<tr>
			<td colspan="6">검색 조건에 맞는 회원이 존재하지 않습니다. </td>
		</tr>
	</c:if>
			
	<c:forEach var="memberVO" items="${list}" varStatus="status">
		<tr>
		<!-- <td>${status.index+1}</td>  -->
		<td>${memberVO.user_no}</td>
		<td> <a 
			href='/admin/users/details${pageMaker.makeSearch(pageMaker.cri.page) }&user_no=${memberVO.user_no}'>${memberVO.user_id}</a> 
		</td>
		<td>${memberVO.user_name}</td> <td>${memberVO.user_email}</td> <td>${memberVO.user_phone}</td> <td>${memberVO.address}</td>
		</tr>	
	</c:forEach>		
</table>
</div>


<div align="center">	
	<!-- 페이징 -->	
	<!-- prev -->
	<c:if test="${pageMaker.prev}">
		<a href="list${pageMaker.makeSearch(pageMaker.startPage-1) }"> [prev] </a>
	</c:if>
		
	<!-- page num -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<a href="list${pageMaker.makeSearch(idx)}">[${idx}]</a>		
	</c:forEach>

	<!-- next -->
	<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
		<a href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">[next]</a>
	</c:if>
</div>

<a href="/admin/main">[메인으로]</a>

<script>	
	$(document).ready(function(){
		$('#searchBtn').on("click",function(event){
			self.location = "list"
							+'${pageMaker.makeQuery(1)}'
							+"&searchType="
							+$("select option:selected").val()
							+"&keyword="
							+$('#keywordInput').val();			
		});		
	});	
</script>



</body>
</html>