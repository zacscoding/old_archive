<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입 완료</title>
</head>
<body>

회원 가입을 완료했습니다.
이메일 인증을 확인해주시기 바랍니다.

<a href=''>${emailAddr}</a>

<br/>


<a href="<c:url value='/index'/>">[/index로 가기]</a>

</body>
</html>