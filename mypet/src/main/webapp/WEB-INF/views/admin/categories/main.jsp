<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Manage Categories..</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	
	<div align="center">
		<h1>동물 카테고리 등록하기</h1>
		동물 이름 : 
		<input type="text" name="animal_name" id="animal_name">
		<button type="button" id="registerAnimal">Add</button>
	</div>
	<br/><br/><br/>	
	
	<!-- <input type="hidden" name="aninal_no_fk" value="${vo.animal_no}">  -->						
	<div align="center">
		<h2>상품 카테고리 등록하기</h2>
		<select name="animal_name_fk">
				<c:forEach var="vo" items="${animalList}">
						<option value="${vo.animal_no},${vo.animal_name}">${vo.animal_name}</option>
				</c:forEach>		
		</select>		
		<input type="text" name="cate_name" id="cate_name">
		<button type="button" id="registerCategory">Add</button>				
	</div>
	<br/><br/><br/>
	<div align="center">
		<h3>Animal List..</h3>
		<table border="1" width="50%">
		<tr>
			<th>No</th><th>Name</th>
		</tr>
		<c:forEach var="animalVO" items="${animalList}">
			<tr>
			<td>${animalVO.animal_no}</td><td>${animalVO.animal_name}</td>
			</tr>
		</c:forEach>		
		</table>	
	</div>
	<br/><br/><br/>
	<div align="center">
		<h3>Products List..</h3>
		<table border="1" width="50%">
		<tr>
			<th>no</th><th>name</th> <th>animal_no</th> <th>animal_name</th>
		</tr>
		<c:forEach var="categoryVO" items="${categoryList}">
			<tr>
			<td>${categoryVO.category_no}</td><td>${categoryVO.cate_name}</td>
			<td>${categoryVO.animal_no_fk}</td><td>${categoryVO.animal_name_fk}</td>
			</tr>			
		</c:forEach>		
		</table>	
	</div>		
	
	
	<script>	
		$(document).ready(function(){
			/*		동물 카테고리 등록	*/
			$('#registerAnimal').on('click',function() {
				var animal_name = $('#animal_name').val();
			
				$.ajax({
					type: 'post',
					url: '/admin/categories/register/'+ animal_name,
					headers: {
					      "Content-Type": "application/json",
					      "X-HTTP-Method-Override": "POST" },
					dataType: 'text',					
					success:function(result) {
						if(result=='SUCCESS') {
							alert('성공적으로 등록되었습니다.');
							self.location='/admin/categories/main';
						}
					}					
				});				
			});
			
			/*		상품 카테고리 등록	*/
			$('#registerCategory').click(function(){
				var noAndName = $('select option:selected').val().split(',');
				var animal_no = noAndName[0];				
				var animal_name = noAndName[1];
				var cate_name = $('#cate_name').val();
				//alert(animal_no + '\n'+animal_name+'\n'+cate_name);
				
				$.ajax({
					type: 'post',
					url: '/admin/categories/register',
					headers: {
					      "Content-Type": "application/json",
					      "X-HTTP-Method-Override": "POST" },
					dataType: 'text',					
					data:JSON.stringify({animal_no_fk:animal_no,animal_name_fk:animal_name,cate_name:cate_name}),					
					success:function(result) {
						if(result=='SUCCESS') {
							alert('성공적으로 등록되었습니다.');
							self.location='/admin/categories/main';
						}
					}
				});
			});
			
			
		});
	
	</script>

</body>
</html>