<!-- bootstrap source by : http://bootsnipp.com/snippets/featured/portfolio-gallery-with-filtering-category -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!-- user detail css -->
<link href="/resources/bootstrap/css/userdetail.css" rel="stylesheet" type="text/css">

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
						<img class="img-circle img-responsive" src="/displayImage?type=p&fileName=${memberVO.profile_pic}">
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<div class="profile-teaser-main">
			<!-- 유저 아이디 -->
			<h2 class="profile-name">
				${memberVO.user_id}
				<!-- 팔로우 하기 버튼 -->
				<button type="button" class="btn btn-primary" id="editBtn">Edit Profile</button>
				<button type="button" class="btn btn-success" id="logoutBtn">Logout</button>
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
						팔로워&nbsp;${memberVO.follower_cnt}명
					</a>
					</h4>
				</div>
				<div class="info" id="following_info">
					<h4>
						<a href="#" onclick="return false;" id="following_cnt">
						팔로잉&nbsp;${memberVO.following_cnt}명
						</a>
					</h4>
				</div>
			</div>
		</div>
	</div> <!-- .// 상단 profile 끝-->

	<!-- image list  -->
	<div class="row">
		<c:forEach var="vo" items="${feedList}">
			<div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
			<a href="#" onclick="displayFeed(${vo.feed_no}); return false;">
			<img
				src="/displayImage?type=f&fileName=${vo.file_name}"
			class="img-responsive">
			</a>
		</div>	
		</c:forEach>
	</div>
</div>	<!-- .//container 끝-->

<script>

//팔로우 상태 출력
/* var printFollowCnt = function() {
	var user_no = '${memberVO.user_no}';
	$.getJSON('/follow/'+user_no, function(data) {
		var follower_cnt = data.follower_cnt;
		var following_cnt = data.following_cnt;
		$('#follower_cnt').empty();
		$('#follower_cnt').html('팔로워&nbsp;'+follower_cnt+'명');
		$('#following_cnt').empty();
		$('#following_cnt').html('팔로잉&nbsp;'+following_cnt+'명');
	});		
}; */

//피드 상세 보여주기	
function displayFeed(feed_no) {
	
	//아작스로 feed detail 얻어옴
	//받은 데이터로 모달창에 표시	
	alert(feed_no);
}	

//edit 버튼 이벤트 처리
$('#editBtn').on('click',function(event){
	self.location="/accounts/edit";
});

//로그아웃 버튼 이벤트 처리
$('#logoutBtn').on('click',function(event){
	self.location="/user/logout";
});
    
</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>



