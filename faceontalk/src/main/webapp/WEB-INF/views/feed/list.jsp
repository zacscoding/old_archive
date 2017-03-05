<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/header.jsp" %>

<style>
@import url("http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css");
.panel-image {
    position: relative;   
}

.panel-image > h4, p {
    padding: 5px 10px 0px; 
}

.panel-image img.panel-image-preview {
    width: 100%;
	border-radius: 4px 4px 0px 0px;
}
.panel-default {
	padding : 20px;
}

.panel-image label {
    display: block;
    position: absolute;
    top: 0px;
    left: 0px;
    height: 80%;
    width: 80%;
}

.panel-heading > span {
    background: #ccc;
    padding: 4px;
    margin-right: 10px;
}

.panel-heading ~ .panel-image img.panel-image-preview {
	border-radius: 0px;
}

.panel-footer span {
    cursor: pointer;
}

.panel-image ~ input[type=checkbox] {
    position:absolute;
    top:- 30px;
    z-index: -1;   
}

/*
.panel-image ~ input[type=checkbox] ~ .panel-body {
	height: 0px;
	padding: 0px;
}
.panel-image ~ input[type=checkbox]:checked ~ .panel-body {
    height: auto;
	padding: 15px;
}
*/

.panel-image ~ .panel-footer a {
    padding: 0px 5px;
	font-size: 14px;
	/*color: rgb(100, 100, 100);*/
}
.btn-hover {
  font-weight: normal;
  color: #333333;
  cursor: pointer;
  background-color: inherit;
  border-color: transparent;
}

.btn-hover-alt {
  font-weight: normal;
  color: #ffffff;
  cursor: pointer;
  background-color: inherit;
  border-color: transparent;
}

/**** comment system ****/

</style>

<div class="container" style="margin-top: 50px;">
	
	<c:forEach var="vo" items="${feedList}" varStatus="status">
	<!-- 피드 리스트 시작 -->
	<div class="row form-group">
        <div class="col-xs-12 col-md-offset-2 col-lg-offset-2 col-md-8 col-lg-8">
            <div class="panel panel-default">
            	<!-- 유저 프로필 사진 + 아이디 -->
                <div class="panel-heading">
                	<div class="media">
                	 <a class="pull-left" href="#" onclick='return false;'>
                	 	<c:choose>
            				<c:when test="${empty vo.profile_pic}">
            					<img class="media-object" src="/resources/bootstrap/images/default_profile.png">	
            				</c:when>
            				<c:otherwise>
            					<img class="media-object" src="/displayImage?type=p&fileName=${vo.profile_pic}">            					
            				</c:otherwise>            				
            			</c:choose> 
  					</a>
  					<div class="media-body">
  						<h3><strong>${vo.user_id_fk}</strong></h3>
  					</div>			  					
  					</div>  					
                </div>
                
                <!-- image -->
                <div class="panel-image">
                	<img src="/displayImage?type=f&fileName=${vo.file_name}"  class="panel-image-preview"/>
                </div>                
               	<!-- 내용 -->
               	<p>${vo.content}</p>
               	
                <!-- 댓글 리스트 -->
                
				<!-- 댓글 달기 -->				
				<div class="input-group">
					<input type="text" class="form-control" placeholder="comment">
					<span class="input-group-btn">
						<button type="button" class="btn btn-primary" id="replyAddBtn">ADD</button>
					</span>
				</div>				
            </div>
        </div>
	</div>
	</c:forEach> <!-- feedList 끝 -->
	
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

<script>

$(function() {
	$('#replyAddBtn').on('click',function() {
		
	});
	
	
	
	
		
});


</script>



<%@ include file="../include/footer.jsp" %>