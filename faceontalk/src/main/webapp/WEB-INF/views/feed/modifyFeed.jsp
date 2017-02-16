<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="POST" action="/feed/modifyFeed">
	<input type="text" name="feed_no" value="${vo.feed_no}" readonly="readonly">
	<input type="text" name="user_id_fk" value="${vo.user_id_fk}" readonly="readonly">
	<input type="text" name="user_name_fk" value="${vo.user_name_fk}" readonly="readonly"> <br>
	<textarea name="content" rows="20" cols="20">${vo.content}</textarea>	
	<input type="text" name="file_name" value="${vo.file_name}" readonly="readonly">	
	<input type="submit" value="변경">
</form>



</body>
</html>