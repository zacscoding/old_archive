<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
.span4 img {
    margin-right: 10px;
}
.span4 .img-left {
    float: left;
}
.span4 .img-right {
    float: right;
}

.media-object {
	width : 50px;
	height : auto;
}
</style>

<div class="container">	

	<a href='#' onclick="$('#idx').attr('value','1'); getReview(); ">리뷰1</a>
	<a href='#' onclick="$('#idx').attr('value','2'); getReview(); ">리뷰2</a>
	
	<input type="hidden" id="idx">	
		        
	<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">				
	</div>         
</div>

<script id="template" type="text/x-handlebars-template">
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

//리뷰 제목 클릭시
function getReview() {
	var idx = $('#idx').val();	
	$.getJSON("/reviews/"+idx+"/1", function(data) {
		var source = $('#template').html();
		var template = Handlebars.compile(source);
		$('#reviewModal').html(template(data));
		$('#reviewModal').modal();
	});
}

//등록 submit
$('#registerForm').submit(function(event) {
	event.preventDefault();
	/* //이미지 업로드 안했으면 경고창 + 리턴
	if($('.fileDrop').css('display') == 'block') {
		$('#errorMessage').css('display','block');
		return;
	}	
	$(this).get(0).submit(); */
	alert('submit');
});

</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>






