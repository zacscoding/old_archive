<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

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

.panel-info {
	width:60%;
}

</style>


<div class="container">
	<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#reviewModal">
		리뷰 보기
	</button>
	
	<br/><br/><br/><br/>
	<h3>Reviews</h3>
	<input type="hidden" name="idx">
	<div class="panel-group" id="accordion">
	
		<!-- 리뷰1		-->
        <div class="panel panel-info">
          <div class="panel-heading">
          	<!-- 타이틀  -->
            <h4 class="panel-title">            
              <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" onclick="$('#idx').attr('value','1'); displayReview(); return false;">
                여기를 클릭해 보세요. #1 
              </a>
            </h4>
          </div>          
          <div id="collapseOne" class="panel-collapse collapse in">
          	<!-- 리뷰 바디 -->
            <div class="panel-body" id="reviewContent">
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
								<div class="row" id="content">
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
            </div> <!-- // 리뷰 바디 끝 .-->
          </div>
        </div> <!-- //리뷰1 끝 -->
        
        <!-- 리뷰2		-->
        <div class="panel panel-info">
          <div class="panel-heading">
          	<!-- 타이틀  -->
            <h4 class="panel-title">            
              <a data-toggle="collapse" data-parent="#accordion" href="#collapse2" onclick="$('#idx').attr('value','2'); displayReview();">
                여기를 클릭해 보세요. #1 
              </a>
            </h4>
          </div>          
          <div id="collapse2" class="panel-collapse collapse in">
          	<!-- 리뷰 바디 -->
            <div class="panel-body" id="reviewContent">
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
								<div class="row" id="content">
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
            </div> <!-- // 리뷰 바디 끝 .-->
          </div>
        </div> <!-- //리뷰2 끝 -->
        
    </div>
</div>


<script>

$(function () { $('.panel-collapse').collapse({
    toggle: false
})});

function displayReview() {
	var idx = $('#idx').val();
	alert(idx);	
	
}
	
	

</script>
