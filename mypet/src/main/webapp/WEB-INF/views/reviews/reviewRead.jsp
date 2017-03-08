<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

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
</style>

<div class="container">
	<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
		리뷰 보기
	</button>
	        
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
					<!-- 상단(타이틀 + x) -->
					<div class="form-group">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>	
							<h2 class="modal-title" id="myModalLabel">TITLE</h2>
						</div><!-- .상단(타이틀 + x) 끝 -->
					</div>		
												
					<!-- 내용 -->
					<div class="form-group">
						<div class="modal-body">					
							<div class="block">
								<div class="row">
									<div class="span4">											
										<img class="img-left"
												src="http://assets.barcroftmedia.com.s3-website-eu-west-1.amazonaws.com/assets/images/recent-images-11.jpg" />
										<br/><br/>																					
									</div>
								</div>
								<div class="row">
									<!-- content -->
	                				<p>Content...</p>
	                				<p>Content...</p>
	                				<p>Content...</p>
	                				<p>Content...</p>
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
	</div> <!-- 모달 전체 윈도우 -->        
</div>

<script>

//등록 submit
$('#registerForm').submit(function(event){
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

<%@ include file="../include/footer.jsp" %>

