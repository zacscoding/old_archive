<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<style>
.span4 img{
    margin-right: 10px;
}
.span4 .img-left{
    float: left;
}
.span4 .img-right {
    float: right;
}
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;  
}
</style>

<div class="container">
	
	<!-- product_no hidden -->	
	<input type="hidden" name="product_no_fk" id="product_no_fk" value="1">
	
	<!-- //-------------리뷰 등록 시작-------------------- -->
	<!-- 리뷰 등록 -->
	<button class="btn btn-primary" data-toggle="modal" data-target="#reviewAddModal">
		리뷰 등록
	</button>
	<div class="modal fade" id="reviewAddModal" tabindex="-1" role="dialog"
		aria-labelledby="reviewAddModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<input type="hidden" name="review_image" id="review_image">  
				<div class="modal-content">
					<!-- 상단(타이틀 + x) -->
					<div class="form-group">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>	
							<h2 class="modal-title" id="reviewAddModalLabel">Regist Review</h2>
						</div><!-- .상단(타이틀 + x) 끝 -->
					</div>														
					<!-- 내용 -->
					<div class="form-group">
						<div class="modal-body">					
							<div class="block">
								<div class="row">
									<!-- 이미지 드랍 -->
									<div class="fileDrop" style="display:block;">&nbsp;<Strong>Drag and drop</Strong> image here</div>										
								</div>
								<div class="row">								
									<div class="span4" id="uploadedPic">
										<!-- 드랍 이미지 뷰 (466 x 320 px) -->
										<img class="img-left" id="viewImage"> 
									</div>	
								</div>
								<br/><br/>
								<div class="row">																				
										<!-- 제목 인풋 -->										
										<div class="row">
											<div class="col-sm-4  col-lg-4">																													
											<input type="text" class="form-control" name="review_title" id="review_title" placeholder="TITLE">
											</div>
										</div>
										<div class="row">																												
											<div class="col-md-9">
												<!-- content -->
	                							<textarea class="form-control" id="review_content" name="review_content" placeholder="Content" rows="8"></textarea>
	              							</div>
              							</div>										
									</div>
								</div>
								<br />
							</div>
							<div class="social-comment">
						</div> <!-- .내용 끝 -->						
					</div>
					<!-- 하단 버튼 -->
					<div class="form-group">					
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="reviewAddBtn">Regist</button>
						</div> <!-- .하단 버튼 끝 -->
					</div>
				</div> <!-- 모달 콘텐츠 -->
		</div> <!-- 모달 다이얼로그 -->		
	</div> <!-- 모달 전체 윈도우 -->  
	<!-- //-------------리뷰 등록 끝-------------------- -->	
	
	
	
	<!-- 리뷰 뷰 -->
	<!-- 
	<a href='#' class="list-group-item" onclick="$('#idx').attr('value','${reviewVO.review_no}'); getReview();">${reviewVO.review_title}</a>
	 -->
	 			
	<!-- 제목 리스트 -->
	<button type="button" class="btn btn-primary" id="displayReviewBtn">리뷰 보기</button>
	<input type="hidden" id="review_no">	
	
	<div class="list-group" id="reviewsDiv">
				
   	</div> <!-- //. 제목 리스트 끝 -->
   	
   	   	       
	<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">				
	</div>
	<!-- // 리뷰 뷰 끝-->           
</div>
<!-- 
{{review_no}}.&nbsp;&nbsp;&nbsp;&nbsp;
<a href='#' class='list-group-item' data-src='{{review_no}}' onclick='getReview();'> {{review_title}} </a>
 -->
<script id="listTemplate" type="text/x-handlebars-template">	
{{#each .}}
	 <a href="#" class="list-group-item" onclick='return false;'>
		{{review_no}} , {{review_title}}	
	</a>
{{/each}}
</script>




<script id="viewTemplate" type="text/x-handlebars-template">
<div class="modal-dialog">
			<div class="modal-content">
					<!-- 상단(타이틀 + x) -->
					<div class="form-group">
						<div class="modal-header">
							<div class="media">
							  <a class="pull-left" href="#">
							    <img class="media-object" src="/resources/bootstrap/imgs/mypet_logo.png" onclick="return false;" alt="...">
							    
							  </a>
							  <div class="media-body">
							  	<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
								</button>	
							    <h4 class="media-heading"> {{ review_title }} </h4>
							  </div>
							</div>
						</div><!-- .상단(타이틀 + x) 끝 -->
					</div>		
												
					<!-- 내용 -->
					<div class="form-group">
						<div class="modal-body">					
							<div class="block">
								<div class="row">
									<div class="span4">											
										<img class="img-left"
												src=" {{ review_image }}" />
										<br/><br/>																					
									</div>
								</div>
								<div class="row" id="content">
									{{ content }}
								</div>
								<br />
							</div>
						</div> <!-- .내용 끝 -->						
					</div>					
					<!-- 하단 버튼 -->
					<div class="form-group">					
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<!-- 
							<button type="submit" class="btn btn-primary" id="registerForm">Regist</button>
							 -->
						</div> <!-- .하단 버튼 끝 -->
					</div>
				</div> <!-- 모달 콘텐츠 -->
		</div> <!-- 모달 다이얼로그 -->	
</script>


<script>


/*	
 * 리뷰 등록
 */
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
		  url: '/reviews/uploadPic',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			 if(data=='notMatchedTypes') {
				 
			 } else {
				 str = "/displayReviews?&fileName="+data;				
				 
				 $('#review_image').attr('value',data); //file_name 속성	
				 $('.fileDrop').css('display','none'); // 드랍창 숨기기	
				 $('#viewImage').attr('src',str); //이미지 데이터 가져오기
				 $('.uploadPic').attr('data-src',data); //삭제 위해 data-src에 파일 이름 넣어줌
			}
		  }
	});	
});	

//파일 이미지 삭제 
$('.uploadedPic').on('click','small',function(event) {
	var that = $(this);	
	$.ajax({
		url: '/reviews/deleteImage',
		type: 'post',
		data: {fileName:$(this).attr("data-src")},
		dataType: 'text',
		success:function(result) {
			if(result == 'deleted') {				
				$('#file_name').attr('value',''); //file_name ''으로 바꾸기
				$('.fileDrop').css('display','block'); //드랍창 보이기
				$('#viewImage').attr('src','');
				$('.uploadPic').attr('data-src','');
			}
		}		
	});	
});

// 리뷰 등록
$('#reviewAddBtn').on('click',function() {
	
	var product_no_fk = $('#product_no_fk').val();
	var review_title = $('#review_title').val();
	var review_content = $('#review_content').val();
	var review_image = $('#review_image').val();
	
	$.ajax({
		type : 'post',
		url : '/reviews/',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "POST"
		},
		dataType : 'text',
		data : JSON.stringify({
			product_no_fk : product_no_fk,
			review_title : review_title,
			review_content : review_content,
			review_image : review_image
		}),
		success : function(result) {
			console.log("result: " + result);
			if (result == 'SUCCESS') {
				alert("등록 되었습니다.");
				$('#reviewModal').hide();
				$('#product_no_fk').val("");
				$('#review_title').val("");
				$('#review_content').val("");
				$('#review_image').val("");
				
				/*
				replyPage = 1;
				getPage("/replies/" + bno + "/" + replyPage);				
				*/
			}
		}
	});
});




/*	
 *	리뷰 리스트		 
 */

//리뷰 보기 버튼 클릭
$('#displayReviewBtn').on('click',function(){	
	var product_no_fk = $('#product_no_fk').val();	
	getReviewList("/reviews/" + product_no_fk + "/1" );	
});
 
//리뷰 리스트 -  가져오기
function getReviewList(pageInfo) {
	$.getJSON(pageInfo, function(data) {
		alert(data.reviewList.length);
		printData(data.reviewList, $('#reviewsDiv'), $('#listTemplate'));
		//printPaging(data.pageMaker,$('.reviewPagination'));
	});	
}

// 리뷰리스트 -  no,title 출력
var printData = function(reviewArr, target, templateObject) { //리뷰리스트,html코드추가할 타깃, 템플릿 
	
	
	var template = Handlebars.compile(templateObject.html());
	
	var html = template(reviewArr);
	
	$('#reviewsDiv').remove();
	
	target.after(html);
}

//리뷰 리스트 - 페이징 출력
var printPaging = function(pageMaker,target) {
	var str="";
	if(pageMaker.prev) { //이전 페이지가 존재하면
		str += "<li><a href='" + (pageMaker.startPage -1)
				+"'> << </a></li>";
	}
	
	for(var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
		var strClass = pageMaker.cri.page == i ? 'class=active' : ''; //현재 페이지 엑티브 효과
		str += "<li " + strClass + "><a href='" + i + "'>" + i + "</a></li>";
	}
	
	
	if(pageMaker.next) { //다음 페이지가 존재하면
		str += "<li><a href='" + (pageMaker.endPage -1)
				+"'> >> </a></li>";
	}
	target.html(str);
}



/*
 * 
 * 리뷰 보기
 *
 */
 
//리뷰 제목 클릭시 /reviews/{product_no_fk}/{review_no}로 요청
function getReview() {
	var review_no = $('#review_no').val();
	var product_no_fk = $('#product_no').val();
	$.getJSON("/reviews/"+product_no_fk+"/"+review_no, function(data) {
		var source = $('#viewTemplate').html();
		var template = Handlebars.compile(source);
		$('#reviewModal').html(template(data));
		$('#reviewModal').modal();
	});
}

</script>

<%@ include file="include/footer.jsp" %>

