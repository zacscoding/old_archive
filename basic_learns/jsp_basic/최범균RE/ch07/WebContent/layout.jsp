<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table  width="400" border="1" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2">
			<jsp:include page="top.jsp" flush="false"/>
		</td>
	</tr>
	<tr>
		<td width="300" valign="top">
			레이아웃1
			<br><br><br>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="bottom.jsp" flush="false"/>
		</td>
	</tr>
	

</table>

</body>
</html>