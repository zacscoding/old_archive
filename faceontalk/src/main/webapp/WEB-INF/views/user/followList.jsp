<!-- bootstrap source by : http://bootsnipp.com/snippets/featured/portfolio-gallery-with-filtering-category -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!-- user detail css -->
<link href="/resources/bootstrap/css/userdetail.css" rel="stylesheet" type="text/css">
<style>
.profile_img img{
	  width: 50px;
	  height: 50px;
	  border: 1px solid #e7eaec;
	  margin-right: 15px;
	  margin-bottom : 10px;
}

.modal-header {
	height : 80px;
}

.modal-backdrop {
	height : 1000px;
}

</style>

<!-- container -->
<div class="container">
	<!-- 상단 profile -->
	<div class="row">
		<div class="profile-teaser-left">
			<div class="profile-img">
				<c:choose>
						<c:when test="${empty memberVO.profile_pic}">					 
							<img class="img-circle img-responsive" src="/resources/bootstrap/images/default_profile.png">
						</c:when>
						<c:otherwise>
							<img class="img-circle img-responsive" src="/displayImage?type=p&fileName=${vo.profile_pic}">
						</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="profile-teaser-main">
			<!-- 유저 아이디 -->
			<h2 class="profile-name">
				${memberVO.user_id}
				<!-- 팔로우 하기 버튼 -->
				<c:choose>
					<c:when test="${isFollow == true}">
						<button type="button" class="btn btn-success followBtn" value="-1">Following</button>		
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-default followBtn" value="1">Follow</button>	
					</c:otherwise>
				</c:choose>				
			</h2>			
			<div class="profile-info">
				<div class="info">
					<h4>
					<a href="#" onclick="return false;">
						게시글&nbsp;${feedList.size()}
					</a>
					</h4>
				</div>
				<div class="info">
					<h4>
					<a href="#" onclick="return false;" id="follower_cnt">
						팔로워&nbsp;${follower_cnt}명
					</a>
					</h4>
				</div>
				<div class="info" id="following_info">
					<h4>
						<a href="#" onclick="return false;" id="following_cnt">
						팔로잉&nbsp;${following_cnt} 명
						</a>
					</h4>
				</div>
			</div>
		</div>
	</div> <!-- .// 상단 profile 끝-->
	
	
	
		
		
	
	
	
	
	
	

</div> <!-- .// 컨테이너 끝 -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>	
	
	