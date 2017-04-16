<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
</head>
<body>
	<h4>회원가입</h4>
	${message}
	<form action="/users/register" method="POST">
	<table>
		<tr>
			<th>
			아이디
			</th>
			<td>
			<input type="text" name="userId" id="userId" value="${memberVO.userId}">
			</td>	
			<td>
			<button type="button" id="checkId">중복체크</button>
			</td>	
		</tr>
		
		<tr>
			<th>
			password
			</th>
			<td colspan="2">
			<input type="password" name="password">
			</td>		
		</tr>
		
		<tr>
			<th>
			이메일
			</th>
			<td>
			<input type="text" name="email" id="email" value="${memberVO.email}">
			</td>
			<td>
			<button type="button" id="checkEmail">중복체크</button>
			</td>		
		</tr>	
	</table>
	<input type="submit" value="가입">
	</form>

<script>

$(function() {
	//아이디 중복 체크
	$('#checkId').on('click',function(event) {
		var keyword = $('#userId').val();
		checkDuplicate('id',keyword);
	});
	
	//이메일 중복 체크
	$('#checkEmail').on('click',function(event) {
		var keyword = $('#email').val();
		checkDuplicate('email',keyword);		
	});
	
	//중복 체크 여부 확인 AJAX
	function checkDuplicate(type,value) {		
		$.ajax({
			url:'/users/confirmDuplicate',
			type:'post',
			headers: { //헤더 정보에 "application/json"이라고 명시
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "POST" },
			dataType:'text',
			data: JSON.stringify({
				searchType : type,
				keyword : value
			}),
			success : function(result) {
				alert(result);
			},
			error: function(){
				alert('서버와의 연결이 좋지 않습니다.');
			}			
		});
	};
	
	
	
});

</script>
</body>
</html>













