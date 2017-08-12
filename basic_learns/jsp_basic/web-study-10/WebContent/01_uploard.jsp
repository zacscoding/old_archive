<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="upload.do" method="post" enctype="multipart/form-data">
<pre>
글쓴이 : <input type="text" name="name"><br>
제   목 : <input type="text" name="title"><br>
파   일 : <input type="file" name="uploadFile"><br>
</pre>
<input type="submit" value="전송">
</form>


</body>
</html>