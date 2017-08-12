<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 게시글의 비밀번호가 일치할 경우 처리를 위한 JSP 페이지 --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<script type="text/javascript">
	if (window.name == 'update') {
		window.opener.parent.location.href = 
			"BoardServlet?command=board_update_form&num=${param.num}";
	} else if(window.name == 'delete') {
		alert('삭제 되었습니다.')	
		window.opener.parent.location.href =
			"BoardServlet?command=board_delete?num=${param.num}";
	}
	window.close()
</script>
</body>
</html>