<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
<table>
<c:forEach var="i" begin="1" end="10">	
	<!-- <button class="testBtn" type="button" data-src="${i}">${i}버튼</button>  -->
	<!-- 
	<div id="row${i}">
		<button class="testBtn" type="button" value="${i}">${i}버튼</button>
	</div>
	 -->
		<tr id="row${i}">
			<td><button class="testBtn" type="button" value="${i}"></button></td>	
		</tr>
</c:forEach>
</table>  


<script>
	$('.testBtn').on('click',function(event){
		var idx = $(this).attr('value');
		
		$('#row'+idx).remove();
	})
</script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>