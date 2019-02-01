<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
.profile_img img{
	  width: 50px;
	  height: 50px;
	  border: 1px solid #e7eaec;
	  margin-right: 15px;
}

.modal-header {
	height : 80px;
}
</style>
<div class="container">
	<div class="row">
		<button class="btn btn-primary" id="modalBtn">
						changepassword</button>
	</div>
		
	<input type="hidden" name="feed_no" id="feed_no" value="28">
				
	<!-- feed modal -->	
	<div class="row">	
		<div class='modal fade' id='feedModal'>
    	</div> <!-- / modal -->    
	</div> <!-- .// feed modal 끝 -->	
</div> <!-- / container -->

<script>
//모달 창 클릭
$('#modalBtn').on('click', function(event) {
	var feed_no = $('#feed_no').val();

	$.getJSON("/feed/" + feed_no, function(data) {			
		printModal(data);
		printReply(data.feed_no);
		$('#feedModal').modal();
	});			
});

	//댓글 등록 버튼 클릭
	$('.replyAddBtn').on('click', function(event) {	
		alert('click');
		event.preventDefault();
		
		var feed_no_fk = $('#feed_no').val();
		var user_id_fk = '${login.user_id}';
		var replytext = $('#replytext').val();
		alert(replytext);
		
		if(replytext.length == 0)
			return;		
		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				feed_no_fk : feed_no_fk,
				user_id_fk : user_id_fk,
				replytext : replytext
			}),
			success : function(result) { //성공시
				if (result == 'SUCCESS') {
					printReply(feed_no_fk);
				}
			}
		});
	});	
	

//피드 출력
var printModal = function(feed) {
	var str = '';
		str	+= "<div class='modal-dialog'><div class='modal-content'><div class='modal-header'>"
			+"<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button><h4 class='modal-title'>"                                                
        	+"<a href='/accounts/detail?user_id=" + feed.user_id_fk + "' class='pull-left profile_img'>"
        	+"<img class='img-circle img-responsive' src='" + "http://dimg.donga.com/wps/SPORTS/IMAGE/2016/02/01/76251832.2.jpg'>"
        	+"</a><a href='/accounts/detail?user_id="+ feed.user_id_fk +"'>"
        	+"<strong>" + feed.user_id_fk + "</strong></a><br/><small class='text-muted'>"+ feed.displayTime +"</small></h4></div>"
        	
        	+"<div class='modal-body'> <img class='img-responsive pull-left' src='displayImage?fileName=" + feed.file_name +"&type=f'>"
        	+ "<h4>" + feed.content + "</h4><hr><div class='comment'></div></div>"
        	+ "<div class='modal-footer'><div class='input-group'> <input type='text' class='form-control' id='replytext' placeholder='comment'>"
        	+ "<span class='input-group-btn'> <button type='button' class='btn btn-primary replyAddBtn'>ADD</button></span>"
        	+ "</div></div></div></div>";
        	
    $('#feedModal').empty();
	$('#feedModal').append(str);
}		

//댓글 출력
var printReply = function(feed_no) {	
	$.getJSON("/replies/all/"+feed_no , function(data) {
		var str = '';
		for(var i in data) {
			var feed_no = data[i].feed_no_fk;
			var user_id = data[i].user_id_fk
			var rno = data[i].rno;
			var replytext = data[i].replytext;
			
			str += "<li class='pull-left' id='rno"+ rno
			+ "'> <a href='/accounts/detail?user_id=" + user_id +"'>"
			+ user_id +"</a>&nbsp;&nbsp;&nbsp;&nbsp;"		
			+ replytext;
			if(user_id == '${login.user_id}') {				
				str += "&nbsp;&nbsp;&nbsp;&nbsp;" 
					+ "<button type='button' class='btn btn-primary btn-xs'"
					+ "onclick='modifyReply(" + feed_no + "," + rno + "," + replytext + ")'>Modify</button>" 
					+ "&nbsp;&nbsp;<button type='button' class='btn btn-danger btn-xs replyDelBtn'" //삭제 버튼
					+ "onclick='removeReply(" + rno +");'>Remove</button>";
			}
			str += "</li><br/><br/>"
		}
		$('.comment').empty();
		$('.comment').append(str);
	});
}

//댓글 등록의 이벤트처리


//댓글 삭제 
function removeReply(rno) {
	var feed_no = $('#feed_no').val();
	
	$.ajax({
		type : 'delete',
		url : '/replies/'+ rno,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "DELETE"
		},
		dataType : 'text',			
		success : function(result) { //성공시
			if (result == 'SUCCESS') 
				printReply(feed_no);				
		}});
}


</script>
            
<%@ include file="/WEB-INF/views/include/header.jsp" %>