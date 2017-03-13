<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/admin/include/header.jsp" %>
<br/><br/><br/><br/><br/><br/><br/>

<div class="container">
  <h2>회원 관리 테이블</h2>  
  <br/>		
		<!-- 검색  -->
		<div>
			 <form role="form" class="form-inline" action="/admin/users/list">			 	 
			 <div class="form-group">			 	 
			<select class="form-control col-sm-3 col-lg-3" name="searchType">
				<!-- n : 검색 조건 없음 -->
				<option value="n"
					<c:out value="${cri.searchType==null?'selected':''}"/>>
					---</option>

				<!-- t : 회원 ID로 검색 -->
				<option value="i"
					<c:out value="${cri.searchType eq 'i'?'selected':''}"/>>
					ID</option>
				<!--  이름으로 검색  -->
				<option value="n"
					<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>
					NAME</option>

				<!-- 이메일로 검색  -->
				<option value="e"
					<c:out value="${cri.searchType eq 'e'?'selected':''}"/>>
					EMAIL</option>

				<!-- 핸드폰으로 검색 -->
				<option value="p"
					<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
					PHONE</option>				
			</select> 
			</div>
			<div class="form-group">
			<input type="text" name='keyword' id="keywordInput" class="form-control"
				value="${cri.keyword}">
			<button id='searchBtn' class="btn btn-default btn-lg glyphicon glyphicon-search"></button>
			</div>
			</form>			
		</div> 
		<br/> 			

	<table class="table table-striped">
		<thead>
			<tr>
				<th>NO</th>
				<th>ID</th>
				<th>NAME</th>
				<th>EMAIL</th>
				<th>PHONE</th>
				<th>ADDRESS</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">검색 조건에 맞는 회원이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="memberVO" items="${list}" varStatus="status">
				<tr>
					<!-- <td>${status.index+1}</td>  -->
					<td>${memberVO.user_no}</td>
					<td><a
						href='/admin/users/details${pageMaker.makeSearch(pageMaker.cri.page) }&user_no=${memberVO.user_no}'>${memberVO.user_id}</a>
					</td>
					<td>${memberVO.user_name}</td>
					<td>${memberVO.user_email}</td>
					<td>${memberVO.user_phone}</td>
					<td>${memberVO.address}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="text-center">
  		<ul class="pagination">  			
			<!-- 페이징 -->	
			<!-- prev -->
			<c:if test="${pageMaker.prev}">
				<li> <a href="list${pageMaker.makeSearch(pageMaker.startPage-1) }">«</a></li>
			</c:if>
				
			<!-- page num -->			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page==idx? 'class =active':''}" /> >
					<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>
			
			<!-- next -->
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="list${pageMaker.makeSearch(pageMaker.endPage+1}">»</a></li>
			</c:if>
		</ul>
	</div>
</div>

<script>
	$(document).ready(function(){
		$('#searchBtn').on("click",function(event){
			self.location = "list"
							+'${pageMaker.makeQuery(1)}'
							+"&searchType="
							+$("select option:selected").val()
							+"&keyword="
							+$('#keywordInput').val();			
		});		
	});
</script>

</body>
</html>

<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>