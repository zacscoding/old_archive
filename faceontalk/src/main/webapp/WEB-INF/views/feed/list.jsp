<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>


<style>
/*
	url : 
	http://bootdey.com/snippets/view/Social-network-feed-list
*/
body{margin-top:20px;}

/* Social feed */
.social-feed-separated .social-feed-box {
  margin-left: 80px;
}
.social-feed-separated .social-avatar {
  float: left;
  padding: 0;
}
.social-feed-separated .social-avatar img {
  width: 55px;
  height: 55px;
  border: 1px solid #e7eaec;
}
.social-feed-separated .social-feed-box .social-avatar {
  padding: 15px 15px 0 15px;
  float: none;
}
.social-feed-box {
  /*padding: 15px;*/
  border: 1px solid #e7eaec;
  background: #fff;
  margin-bottom: 15px;
}
.article .social-feed-box {
  margin-bottom: 0;
  border-bottom: none;
}
.article .social-feed-box:last-child {
  margin-bottom: 0;
  border-bottom: 1px solid #e7eaec;
}
.article .social-feed-box p {
  font-size: 13px;
  line-height: 18px;
}
.social-action {
  margin: 15px;
}
.social-avatar {
  padding: 15px 15px 0 15px;
}
.social-comment .social-comment {
  margin-left: 45px;
}
.social-avatar img {
  height: 40px;
  width: 40px;
  margin-right: 10px;
}
.social-avatar .media-body a {
  font-size: 14px;
  display: block;
}
.social-body {
  padding: 15px;
}
.social-body img {
  margin-bottom: 10px;
}
.social-footer {
  border-top: 1px solid #e7eaec;
  padding: 10px 15px;
  background: #f9f9f9;
}
.social-footer .social-comment img {
  width: 32px;
  margin-right: 10px;
}
.social-comment:first-child {
  margin-top: 0;
}
.social-comment {
  margin-top: 15px;
}
.social-comment textarea {
  font-size: 12px;
}
.form-control, .single-line {
    background-color: #FFFFFF;
    background-image: none;
    border: 1px solid #e5e6e7;
    border-radius: 1px;
    color: inherit;
    display: block;
    padding: 6px 12px;
    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
    width: 100%;
    font-size: 14px;
}
.ibox {
  clear: both;
  margin-bottom: 25px;
  margin-top: 0;
  padding: 0;
}
.ibox.collapsed .ibox-content {
  display: none;
}
.ibox.collapsed .fa.fa-chevron-up:before {
  content: "\f078";
}
.ibox.collapsed .fa.fa-chevron-down:before {
  content: "\f077";
}
.ibox:after,
.ibox:before {
  display: table;
}
.ibox-title {
  -moz-border-bottom-colors: none;
  -moz-border-left-colors: none;
  -moz-border-right-colors: none;
  -moz-border-top-colors: none;
  background-color: #ffffff;
  border-color: #e7eaec;
  border-image: none;
  border-style: solid solid none;
  border-width: 3px 0 0;
  color: inherit;
  margin-bottom: 0;
  padding: 14px 15px 7px;
  min-height: 48px;
}
.ibox-content {
  background-color: #ffffff;
  color: inherit;
  padding: 15px 20px 20px 20px;
  border-color: #e7eaec;
  border-image: none;
  border-style: solid solid none;
  border-width: 1px 0;
}
.ibox-footer {
  color: inherit;
  border-top: 1px solid #e7eaec;
  font-size: 90%;
  background: #ffffff;
  padding: 10px 15px;
}
</style>


<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container" align="center">
<div class="col-md-7">   
   	<!-- 피드 박스 -->
   	<c:forEach var="vo" items="${feedList}" varStatus="status">   	
	    <div class="social-feed-box">
	    	
	    	<!-- 
	    	<c:if test="${login.user_id == vo.user_id_fk}">
	    		<div class="pull-right social-action dropdown">
            		<button data-toggle="dropdown" class="dropdown-toggle btn-default">
                		<i class="glyphicon glyphicon-option-vertical"></i>
            		</button>
	            	<ul class="dropdown-menu m-t-xs">
	                		<li><a href="#" onclick="return false;">
	                			<strong class="text-info">MODIFY</strong></a></li>
	                		<li><a href="#" onclick="return false;">	                			
	                			<strong class="text-info">REMOVE</strong></a></li>
	            	</ul>
        		</div>
	    	</c:if>	    
	    	 -->    
	    	<!-- 상단 : 프로필, 이름 , 등록 일 -->       
	        <div class="social-avatar">
	            <a href="#" class="pull-left" onclick ="return false;">
	            	<!-- 
	            	<c:choose>
            				<c:when test="${empty vo.profile_pic}">            				
	                		<img class="img-rounded img-responsive" src="/resources/bootstrap/images/default_profile.png">
	                		</c:when>
	                		<c:otherwise>
	                		<img class="img-rounded img-responsive" src="/displayImage?type=p&fileName=${vo.profile_pic}">	                		
	                		</c:otherwise>
	               	</c:choose>
	               	 -->
	               	 <img class="img-rounded img-responsive" src="http://dimg.donga.com/wps/SPORTS/IMAGE/2016/02/01/76251832.2.jpg">
	            </a>
	                                    
	            <div class="media-body">
	                <a href="#" class="pull-left"><strong>${vo.user_id_fk}</strong></a><br>
	                <small class="text-muted pull-left">${vo.regdate}</small>
	            </div>
	        </div> <!-- 상단 끝 -->
	        
	        <!-- content -->
	        <div class="social-body">
	        	<!-- 이미지 -->
	            <img src="/displayImage?type=f&fileName=${vo.file_name}" class="img-responsive">
	            	            
	            <!-- 컨텐츠 -->
	            <p>
	            	${vo.content}
	            </p>
	            
	            <!-- 버튼 -->			                        
	            <button class="btn btn-white btn-xs pull-left" id="comment"><i class="fa fa-comments"></i> Comment</button>
	            <br/>            
	        </div> <!-- content 끝 -->
	        
	        <div class="social-footer" style="display:none" id="commentDisplay">
	        	<!-- 댓글 시작 -->
	            <div class="social-comment">
	                <a href="" class="pull-left">
	                    <img alt="image" src="http://webapplayers.com/inspinia_admin-v2.5/img/a1.jpg">
	                </a>
	                <div class="media-body">
	                    <a href="#">
							Andrew Williams
	                    </a>
	                    Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words.
	                    <br>
	                </div>
	            </div>            
	        </div>
	        
	    </div><!-- .피드 박스 끝 -->
    </c:forEach>  
    
    <!-- 페이징 처리 -->
	<div class="text-center">
  		<ul class="pagination">  			
			<!-- 페이징 -->	
			
			<!-- prev -->
			<c:if test="${pageMaker.prev}">
				<li> <a href="list${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:if>
				
			<!-- page num -->			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page==idx? 'class =active':''}" /> >
					<a href="list${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>
			
			<!-- next -->
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="list${pageMaker.makeQuery(pageMaker.endPage+1}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
      
</div>
</div>

<script>
	$(function(){
		$('#comment').on('click',function() {
			var state = $('#commentDisplay').css('display');		
			var attr;
			if(state == 'none')
				attr = 'block';
			else 
				attr = 'none';			
			$('#commentDisplay').css('display',attr);
		});		
	});

</script>



<%@ include file="../include/footer.jsp" %>