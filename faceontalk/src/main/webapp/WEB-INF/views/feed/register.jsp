<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>
<style>
	.fileDrop {
	  width: 60%;
	  height: 100px;
	  border: 1px dotted blue;	    
	}	
	small {
		margin-left: 3px;
		height: 200px;
		border: 1px dotted blue;
	}
</style>

<div class="container">	
	<h1>Regist Feed</h1>
	<hr>
	<!-- profile pic , user_id -->
	<div class="row">	
		<div class="media">
            		<a class="pull-left">
            			<c:choose>
            				<c:when test="${empty login.profile_pic}">
            					<img class="media-object img-circle img-responsive" src="/resources/bootstrap/images/default_profile.png">	
            				</c:when>
            				<c:otherwise>
            					<img class="media-object img-circle img-responsive" src="/displayImage?type=p&fileName=${login.profile_pic}">
            				</c:otherwise>            				
            			</c:choose>            			
            		</a>
            		<div class="media-body">
            			<h3>${login.user_id}</h3>
            		</div>
        </div>
	</div>
	
	<!-- register form -->
	<form  role="form" id="registerForm" class="form-horizontal" action="/feed/register" method="POST">
	<input type="hidden" name="user_no_fk" value="${login.user_no}">	
	<input type="hidden" name="user_id_fk" value="${login.user_id}">
	<input type="hidden" name="file_name" id="file_name">				
            <!-- content -->
            <div class="form-group">
              <div class="col-md-9">
                <textarea class="form-control" id="content" name="content" placeholder="Content" rows="8"></textarea>
              </div>
            </div>            
                
            <!-- drop image & view image -->
            <div class="fileDrop" style="display:block">&nbsp;<Strong>Drag and drop</Strong> image here</div>
			<div class="uploadedPic" style="display:none"></div>
			<br/>
			<input type="submit" class="btn btn-primary" value="Regist" id="btnRegister">                
			<input type="reset" class="btn btn-warning" value="Clear">
	</form>	
	<br/><br/>
	<div class="alert alert-danger" id="errorMessage" style="display:none">
		<strong>Please</strong> &nbsp; upload one picture.
	</div>	
</div>





<script>
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
		  url: '/feed/uploadPic',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			 if(data=='notMatchedTypes') {
				 
			 } else {
				 str="<div><img src='/displayImage?type=f&fileName="+data+"'/>"
				 	+ "<small id='file_name' data-src="+data+">X</small></div>";
				 $('#file_name').attr('value',data); //file_name 속성	
				 $('.fileDrop').css('display','none'); // 드랍창 숨기기				 
				 $('.uploadedPic').append(str); // 썸네일 이미지 보이기
				 $('.uploadedPic').css('display','block');
				 $('#errorMessage').css('display','none'); //경고창 있어으면 가리기
			 }
		  }
	});	
});	

//파일 이미지 삭제 
$('.uploadedPic').on('click','small',function(event) {
	var that = $(this);
	$.ajax({
		url: '/feed/uploadPic',
		type: 'delete',
		headers: { 
		      "Content-Type": "application/json",
		      "X-HTTP-Method-Override": "delete" },
		data: {fileName:$(this).attr("data-src")},				      	
		dataType: 'text',
		success:function(result) {
			if(result == 'deleted') {				
				$('#file_name').attr('value',''); //file_name ''으로 바꾸기
				$('.fileDrop').css('display','block'); //드랍창 보이기
				$('.uploadedPic').css('display','none'); //기존 이미지 창 숨기기
				that.parent("div").remove();
			}
		}		
	});	
});

//등록 submit
$('#registerForm').submit(function(event){
	event.preventDefault();
	//이미지 업로드 안했으면 경고창 + 리턴
	if($('.fileDrop').css('display') == 'block') {
		$('#errorMessage').css('display','block');
		return;
	}
	
	$(this).get(0).submit();
});

	

</script>


<%@ include file="../include/footer.jsp" %>