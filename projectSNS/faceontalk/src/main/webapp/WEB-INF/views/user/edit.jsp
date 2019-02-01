<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
	.fileDrop {
	  width: 30%;
	  height: 150px;
	  border: 1px dotted blue;	    
	}	
	small {
		margin-left: 3px;
		height: 200px;
		border: 1px dotted blue;
	}
	img{
		width:100px;
		height:100px;
	}
</style>

<div class="container">	
	 <h3 class="text-primary">Edit profile</h3>
		<div class="row col-sm-4 col-lg-4">
			<h4>Profile Pic</h4>
			
			<input type="hidden" id="fileName" value="${vo.profile_pic}">
						
			<c:choose>
				<c:when test="${empty vo.profile_pic}">
					<div class="fileDrop" style="display:block">&nbsp;<Strong>Drag and drop</Strong> profile image here</div>
					<div class="uploadedPic" style="display:none"></div>		
				</c:when>				
				<c:otherwise>
					<div class="fileDrop" style="display:none">&nbsp;<Strong>Drag and drop</Strong> profile image here</div>
					<div class="uploadedPic">
						<img class='img-circle' src='/displayImage?type=p&fileName=${vo.profile_pic}'/>
						<small id='file_name' data-src='${vo.profile_pic}'>X</small>
					</div>
				</c:otherwise>
			</c:choose>			
						 
		</div>
		<div class="row col-sm-4 col-lg-4">	 	
		<form role="form" class="" action="/accounts/edit" method="post">
			<input type="hidden" name="user_no" value="${vo.user_no}">	
			<input type="hidden" name="profile_pic">		
			<div class="form-group">
					<label for="Name">user_id</label> <input type="text" name="user_id"
						class="form-control" value="${vo.user_id}">
			</div>
			
			<div class="form-group">
			<button class="btn btn-primary" data-toggle="modal" data-target="#pwModal" onclick="return false;">
						changepassword</button>
			</div>
			
			<div class="form-group">
				<label for="Name">Email</label> <input type="text" name="user_email"
					class="form-control" value="${vo.user_email}" readonly="readonly">
			</div>
			
			<div class="form-group">	
					<label for="Name">phone</label> <input type="text" name="phone"
						class="form-control" value="${vo.phone}">
			</div>
			<div class="form-group">		
				<button type="submit" class="btn btn-primary" id="editBtn">Submit</button>
				<button class="btn btn-primary">Cancel</button>
			</div>
		</form>
	</div>
	
	<!-- 메시지 -->
    <div class="row  col-sm-4 col-lg-4">
      	<!-- 비밀번호 변경 성공 메시지 -->
	      <div class="alert alert-success" style="display:none" id="successAlert">
				<div class="text primary">
				<strong>Success to change password.</strong><br/>					
				</div>
			</div>
	</div>	
	
	<!-- 아이디 중복 -->
	<c:if test="${duplicateId}">
		<div class="row  col-sm-4 col-lg-4">
			<div class="alert alert-warning" id="duplicateIdAlert">
				 		<strong>Duplicate id.</strong> Please write another id.
			</div>
		</div>	
	</c:if>
		
	<!--password change modal -->
	<div class="modal fade" id="pwModal" tabindex="-1" role="dialog" aria-labelledby="pwModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	        <h4 class="modal-title" id="pwModalLabel">ChangePassoword</h4>
	      </div>
	      <div class="modal-body">
	      	<input type="password" class="form-control" name="password" id="password" placeholder="password">
			<input type="password" class="form-control" name="password" id="confirm" placeholder="confirm password">
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="pwChangeBtn">Change</button>
	      </div>
	    </div> <!-- 모달 콘텐츠 -->
	  </div> <!-- 모달 다이얼로그 -->
	</div> <!-- 모달 전체 윈도우 -->	
	
</div>   
<script>
$(document).ready(function(){
	//비밀번호 변경
	$('#pwChangeBtn').on('click',function(event) {
		event.preventDefault();		
		var user_no = ${vo.user_no};
		var password = $('#password').val();
		var confirm = $('#confirm').val();
		
		if(password != confirm) {
			alert("Check password");
		} else {
			$.ajax({
				type:'post',
				url:'/accounts/editPassword',
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "POST" },
				dataType:'text',
				data: JSON.stringify({user_no:user_no, password:password}),
				success:function(result) {
					if(result == 'SUCCESS') {
						$('#successAlert').css('display','block');						
						$('#pwModal').hide();
						$('#password').val('');
						$('#confirm').val('');

					}				
				}			
			});		
		}
	});
	
	//프로필 업로드
	//이미지 타입인지 체크
	function checkImageType(fileName) {
		var pattern = /jpg|gif|png|jpeg/i; //i : 대소문자 구분 X	
		return fileName.match(pattern);	
	}
	
	//드래그진입,오버 이벤트 제거
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});
	
	//파일 드랍 -> 서버로 전송 -> 썸네일 생성 -> 현재 페이지에서 뷰   
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();	
		var files = event.originalEvent.dataTransfer.files;	
		var file = files[0];
			
		var formData = new FormData();
		formData.append("file", file);
		
		$.ajax({
			  url: '/accounts/uploadPic/${login.user_no}',
			  data: formData,
			  dataType:'text',
			  processData: false,
			  contentType: false,
			  type: 'POST',
			  success: function(data){
				 if(data=='notMatchedTypes') { 
					 
				 } else {
					 str="<img class='img-circle' src='/displayImage?type=p&fileName="+data+"'/>"
					 	+ "<small>X</small>";
					 	
					 $('#fileName').val(data); //파일 이름 hidden	
					 $('.fileDrop').css('display','none'); // 드랍창 숨기기				 
					 $('.uploadedPic').append(str); // 썸네일 이미지 보이기
					 $('.uploadedPic').css('display','block');					 
				 }
			  }
		});	
	});	
	
	
	//파일 이미지 삭제 
	//processData :false == 일반적인 query string으로 변환 false
	$('.uploadedPic').on('click','small',function(event) {
		var profile_pic =  $('#fileName').val();
		var user_no = '${login.user_no}';
		$.ajax({
			url: '/accounts/uploadPic',
			type: 'delete',
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "delete" },
			data: JSON.stringify({user_no:user_no,profile_pic:profile_pic}),						      
			dataType: 'text',
			success:function(result) {
				if(result == 'deleted') {
					$('.uploadedPic').empty(); //기존 사진 지우기
					$('.fileDrop').css('display','block'); //드랍창 보이기
					$('.uploadedPic').css('display','none'); //기존 이미지 창 숨기기
					$('#fileName').val(''); //파일 이름 hidden  지우기
				}
			}		
		});	
	});
});


</script>



   
<%@ include file="/WEB-INF/views/include/footer.jsp" %>