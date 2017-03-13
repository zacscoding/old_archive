<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- include header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<style>
	img {
		width:80px;
		height:80px;
	}
</style>

<div class="container">

<h1>Search User Lists</h1>

	<c:if test="${not empty msg}">		
			<div class="alert alert-info"> 
					${msg}
			</div>
		</c:if>	
	
	<c:forEach var="vo" items="${memberList}">
		<div class="media">
			<c:choose>
				<c:when test="${not empty vo.profile_pic}">
					<a class="pull-left" href="/accounts/detail?user_id=${vo.user_id}">
						<img class="media-object img-circle img-responsive" src="/displayImage?type=p&fileName=${vo.profile_pic}">
					</a>
				</c:when>				
				<c:otherwise>
					<a class="pull-left" href="/accounts/detail?user_id=${vo.user_id}">
						<img class="media-object img-circle img-responsive" src="/resources/boostrap/images/default_profile.png">
					</a>
				</c:otherwise>
			</c:choose>
			<div class="media-body">
				<h4 class="media-heading"><a href="/accounts/detail?user_id=${vo.user_id}">${vo.user_id }</a></h4>
				팔로워 : ${vo.follower_cnt}명 &nbsp;&nbsp; 팔로잉 : ${vo.following_cnt}명				
			</div>
		</div>
	</c:forEach>	
</div>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>