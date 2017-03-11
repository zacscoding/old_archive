<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<button type="button" class="btn btn-primary btn-xs"
>DisplayALL</button>

<div class="display">

</div>

<script>
	
	$('.btn').on('click',function(){
			$.getJSON("/replies/all/16", function(data) {
				//display reply list 
				printData(data);
			});	
			
			var printData = function(replyArr) {
				var str = '';
				for(var i in replyArr) {
					str += (replyArr[i].rno + '<br/>');
				}
				$('.display').append(str);		
			};	
	});
	
		


</script>

<%@ include file="/WEB-INF/views/include/header.jsp" %>