<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>로그인</title>
</head>
<body>
<h1>Login Page...</h1>
<!-- authentication-failure-url 속성 값 : /user/loginform?error=true 에 대한 처리 -->

<c:if test="${param.error == 'true'}">
	<script>
		alert('아이디와 암호가 일치하지 않습니다.');
	</script>
</c:if>

<form name='f' action='/user/login' method='POST'>
    <label for="userid">사용자ID</label>:
    <input type="text" id="userid" name="userid" /> 
    <br/>
    
    <label for="password">암호</label>:
    <input type="password" name="password" /> 
    <br/>    
    <input type="submit" value="로그인" />
</form>

<a href=''>[회원 가입]</a>


</body>
</html>
