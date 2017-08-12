<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Face On Talk</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>	
	<div id="wrap" align="center">
	<form action="/login.do" method="post">	
	<table>
		<tr>
			<th width="50%" colspan="4" rowspan="3" align="center">
				<h2>FaceOnTalk</h2>
			</th>
		</tr>
		<tr>
			<th>이메일or휴대폰</th>
			<th>비밀번호</th>
			<th>&nbsp;</th>
		</tr>
		<tr>
			<th><input type="text" name="email" size="15" maxlength="20"></th>
			<th><input type="text" name="password" size="15" maxlength="20"></th>
			<th>로그인</th>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
	
	
	
	