<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!-- feed css -->
<link href="/resources/bootstrap/css/feed.css" rel="stylesheet" type="text/css">

<div class="container" align="center">
	<div class="col-md-7">
		
		<c:if test="${not empty msg}">
			<div class="alert alert-info">${msg}</div>
		</c:if>		
		<!-- 피드 박스 -->
		<c:forEach var="vo" items="${feedList}" varStatus="status">
			<input type="hidden" id="feed_no_fk">
			<div class="social-feed-box" id="feedbox${vo.feed_no}">
				<!-- 상단 : 프로필, 이름 , 등록 일 -->
				<div class="social-avatar">					
					<a href="/accounts/detail?user_id=${vo.user_id_fk}" class="pull-left">
					<c:choose>
						<c:when test="${empty vo.profile_pic}">					 
							<img class="img-circle img-responsive" src="/resources/bootstrap/images/default_profile.png">
						</c:when>
						<c:otherwise>
							<img class="img-circle img-responsive" src="/displayImage?type=p&fileName=${vo.profile_pic}">
						</c:otherwise>
					</c:choose>
					</a>
					<div class="media-body">
						<div class="pull-left">
							<a href="/accounts/detail?user_id=${vo.user_id_fk}" class="pull-left"><strong>${vo.user_id_fk}</strong></a><br>
							<small class="text-muted pull-left"> ${vo.displayTime } <!-- 
		                	<fmt:formatDate pattern="yy MM dd HH" value="${vo.regdate}"/>
		                	 -->
							</small>
						</div>
						
						<c:if test="${vo.user_id_fk == login.user_id}">
						<div class="pull-right">
							<button type="button" class="btn btn-primary btn-circle">
								<i class="glyphicon glyphicon-list"></i></button>
							<button type="button" class="btn btn-warning btn-circle feedDelBtn" value="${vo.feed_no}">
								<i class="glyphicon glyphicon-remove"></i></button>
						</div>
						</c:if>
					</div>
					
				</div>
				<!-- 상단 끝 -->

				<!-- content -->
				<div class="social-body">
					<!-- 이미지 -->
					<img src="/displayImage?type=f&fileName=${vo.file_name}"
						class="img-responsive"><br/>
					<!-- 컨텐츠 -->
					<h4>					
					${vo.content}
					</h4>
					
					
					<!-- 버튼 -->
					<c:if test="${vo.reply_count>3}">
						<button class="btn btn-white btn-xs pull-left" id="commentBtn${vo.feed_no}"							
							onclick="displayAllReply(${vo.feed_no});$(this).remove();">
							<i class="fa fa-comments"></i>&nbsp;${vo.reply_count}개의 댓글 모두 보기
						</button>
					</c:if>					
					<br />
				</div>
				<!-- content 끝 -->

				<div class="social-footer">
					<!-- 댓글 시작-->
					<div class="social-comment" id="commentDisplay${vo.feed_no}">
						<c:forEach var="replyVO" items="${vo.replyList}">
							<li class='pull-left' id="rno${replyVO.rno}">						
							
							<!-- 댓글 작성자 -->
							<a href="/accounts/detail?user_id=${replyVO.user_id_fk}">${replyVO.user_id_fk}</a>							
								&nbsp;&nbsp;&nbsp;&nbsp;
							
							<!-- 댓글 내용 --> 
							${replyVO.replytext} 
							
							<!-- 댓글 수정 ,삭제  --> 
							<c:if test="${replyVO.user_id_fk == login.user_id}">
								&nbsp;&nbsp;&nbsp;&nbsp;															
								<button type="button" class="btn btn-primary btn-xs"
									onclick="modifyReply(${vo.feed_no},${replyVO.rno},${replyVO.replytext})"
									value="${replyVO.rno}">Modify</button>																		
								<button type="button" class="btn btn-danger btn-xs replyDelBtn"
									onclick="setFeedNumer(${replyVO.feed_no_fk}); removeReply(${replyVO.rno});">Remove</button>									
							</c:if></li><br/><br/>
						</c:forEach>
					</div>
										
					<!-- 댓글쓰기 -->
					<!-- http://okky.kr/article/230588 -->
					<div class="input-group">
						<input type="hidden" name="feed_no_fk"
							id="feed_no_fk${status.index}" value="${vo.feed_no}"> <input
							type="text" class="form-control" name="replyText"
							id="replyText${status.index}" placeholder="comment"> <span
							class="input-group-btn"> <input type="button"
							class="btn btn-primary replyAddBtn" id='${status.index}'
							value="ADD"></span>
					</div>
					<!-- .댓글쓰기 끝 -->
				</div>
			</div>
			<!-- .피드 박스 끝 -->
		</c:forEach>

		<!-- 페이징 처리 -->
		<div class="text-center">
			<ul class="pagination">
				<c:if test="${pageMaker.prev}">
					<li><a
						href="list${pageMaker.makeQuery(pageMaker.startPage-1)}">«</a></li>
				</c:if>
				
				<c:if test="${pageMaker.startPage != 0}">				
					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
						var="idx">
						<li
							<c:out value="${pageMaker.cri.page==idx? 'class =active':''}" />>
							<a href="list${pageMaker.makeQuery(idx)}">${idx}</a>
						</li>
					</c:forEach>
				</c:if>

				<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
					<li><a href="list${pageMaker.makeQuery(pageMaker.endPage +1)}">»</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<!-- .//페이징 처리 끝-->


	<a class="btn btn-primary btn-xs" 
	    data-toggle="modal" data-target="#modifyModal">Modify</a>
		    
	<!-- modify 버튼 클릭 시 Modal 창-->  
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body" data-rno>
	        <p><input type="text" id="modifytext" class="form-control"></p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<script>
	/*	피드 관련 script	*/
	$(document).ready(function() {		
		//피드 삭제 하기 
		// ajax로 서버 전송 -> 그에 해당하는 <div> remove
		$(".feedDelBtn").on('click',function(event) {
			event.preventDefault();
			var feed_no = $(this).val();
			
			$.ajax({
				type : 'delete',
				url : '/feed/'+ feed_no,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				success : function(result) {
					if(result == 'SUCCESS') {
						$('#feedbox'+feed_no).remove();
					}
				}
			});			
		});
		
		//피드 수정하기
		//수정 폼으로 넘기기 ->
		
		
	}); 

</script>

<script>
	/*	댓글 관련 script	*/
	
	// feed_no를 담기 위한 함수
	function setFeedNumer(feed_no_fk) {
		$('#feed_no_fk').val(feed_no_fk);
	}
	
	//댓글보기 이벤트 처리 (서버로 부터 댓글 리스트 받음) 
	function displayAllReply(feed_no_fk) {		
		//get reply data from server
		$.getJSON("/replies/all/"+feed_no_fk, function(data) {
			//display reply list 
			printData(data ,$('#commentDisplay'+feed_no_fk) );
			
			$('#commentBtn'+feed_no_fk).css('display','none');
		});		
	}
	
	//서버로 받은 댓글 데이터 출력 
	var printData = function(replyArr, target) {
		var str = '';		
		for(var i in replyArr) {
			var feed_no = replyArr[i].feed_no_fk;
			var user_id = replyArr[i].user_id_fk
			var rno = replyArr[i].rno;
			var replytext = replyArr[i].replytext;
			
			str += "<li class='pull-left' id='rno"+ rno
					+ "'> <a href='/accounts/detail?user_id=" + user_id +"'>"
					+ user_id +"</a>&nbsp;&nbsp;&nbsp;&nbsp;"		
					+ replytext;
			if(user_id == '${login.user_id}') {				
				str += "&nbsp;&nbsp;&nbsp;&nbsp;" 
						+ "<button type='button' class='btn btn-primary btn-xs'"
						+ "onclick='modifyReply(" + feed_no + "," + rno + "," + replytext + ")'>Modify</button>" 
						+ "&nbsp;&nbsp;<button type='button' class='btn btn-danger btn-xs replyDelBtn'" //삭제 버튼
						+ "onclick='setFeedNumer(" + feed_no 
						+ "); removeReply(" + rno +");'>Remove</button>";
			}
			str += "</li><br/><br/>"			
		}
		
		// 기존 display 비우기
		target.empty(); 
		
		// 새로운 display 
		target.append(str);
	} 

	//댓글 등록의 이벤트처리
	$('.replyAddBtn').on('click', function(event) {
		event.preventDefault();
		var idx = $(this).attr('id');
		var feed_no_fk = $('#feed_no_fk' + idx).val();
		var user_id_fk = '${login.user_id}';
		var replytext = $('#replyText' + idx).val();
		if(replytext.length == 0)
			return;
		
		$.ajax({
			type : 'post',
			url : '/replies/',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				feed_no_fk : feed_no_fk,
				user_id_fk : user_id_fk,
				replytext : replytext
			}),
			success : function(result) { //성공시
				console.log("result: " + result);
				if (result == 'SUCCESS') {
					displayAllReply(feed_no_fk, idx); //댓글보여주기
					$('#replyText' + idx).val('');
				}
			}
		});
	});	
	
	//댓글 수정 이벤트 처리
	function modifyReply(index, rno) {
		var replytext = $("#replytext").val();
		$.ajax({
			type:'put',
			url:'/replies/'+rno,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "PUT" },
			data:JSON.stringify({replytext:replytext}), 
			dataType:'text', 
			success:function(result){
				console.log("result: " + result);
				if(result == 'SUCCESS'){
					alert("수정 되었습니다.");
				}
		}});		
	}
	
	//댓글 삭제 이벤트 처리)
	function removeReply(rno) {
		var feed_no_fk = $('#feed_no_fk').val();		
		$.ajax({
			type : 'delete',
			url : '/replies/'+ rno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : 'text',			
			success : function(result) { //성공시
				if (result == 'SUCCESS') 
					displayAllReply(feed_no_fk);				
			}});
	}
	
</script>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>