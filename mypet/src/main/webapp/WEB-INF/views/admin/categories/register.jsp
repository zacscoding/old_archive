<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>카테 고리 등록</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

	<form method="post" action="/admin/categories/register/animal">
		동물 이름 : 
		<input type="text" name="animal_name">
		<input type="submit" value="등록">	
	</form>
	
	<br/><br/><br/>	
	<form method="post" action="/admin/categories/register">
			<select name="animal_name_fk">
				<c:forEach var="vo" items="${animalList}">
						<option value="${vo.animal_name}">${vo.animal_name}</option>
						<input type="hidden" name="aninal_no_fk" value="${vo.animal_no}">						
				</c:forEach>		
			</select>		
		<input type="text" name="cate_name">
		<input type="submit" value="등록">	
	</form>	
	
	<script>
	
	
	</script>
	
</body>
</html>