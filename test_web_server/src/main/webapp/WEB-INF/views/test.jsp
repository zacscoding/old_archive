<!-- 테스트 뷰 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h4>WAS : TOMCAT</h4>
	<h4>ORM : Mybatis</h4>
	<h4>DB : ${dbProfile}</h4>
	<h4><a href="${context}">HOME</a></h4>
	<hr>
	<h4>Result</h4>		
	<c:if test="${not empty message}">
		Result Message : ${message}
	</c:if>
	<hr>
	<form id="testForm" action="" method="post">
		<input type="hidden" name="count" id="count">
		<h4>Insert TEST</h4>
			<c:forEach var="dataType" items="${dataList}">				
				<input type="checkbox" name="datas" value="${dataType}"/>
				${dataType}
			</c:forEach>	
			<br/>		
			<button class="insert" value="1">1개 등록</button>
			<button class="insert" value="10">10개 등록</button>
			<button class="insert" value="100">100개 등록</button>							
		<hr>	
				
		<h4>Update TEST</h4>
			<button class="update">UPDATE</button>							
		<hr>
		
		<h4>Delete TEST</h4>
			<button class="delete">DELETE ALL</button>	
		<hr>	
		
		<h4>Select TEST</h4>
			<button class="select">SELECT ALL</button>	
		<hr>	
		
		<h4>CRUD Query TEST</h4>
			<input type="text" name="query">
			<button class="query">Execute</button>	
		<hr>	
	</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(function() {		
		function submit( action ) {
			$('#testForm').attr('action',action);
			$('#testForm').submit();
		}		
		$('.insert').on('click',function(e) {
			e.preventDefault();
			var cnt = $(this).val();			
			$('#count').val(cnt);
			submit('${context}/insert');
		});
		
		$('.update').on('click',function(e) {
			e.preventDefault();
			submit('${context}/updateAll');
		});
		
		$('.delete').on('click', function(e){
			e.preventDefault();
			submit('${context}/deleteAll');
		});
		
		$('.select').on('click', function(e){
			e.preventDefault();
			submit('${context}/selectAll');
		});
		
		$('.query').on('click', function(e){
			e.preventDefault();			
			submit('${context}/query');
		});
	});


</script>

</body>
</html>