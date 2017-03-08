<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/include/header.jsp" %>
<br/><br/><br/><br/>


			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST PAGING</h3>
				</div>
				
				<div class='box-body'>

					<select name="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							Category</option>
						<option value="p"
							<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
							상품명</option>
						<option value="b"
							<c:out value="${cri.searchType eq 'b'?'selected':''}"/>>
							상품구분(y/n)</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
					<button id='searchBtn'>Search</button>
					<button id='newBtn'>New Board</button>

				</div>
				
				
				<div class="box-body">
					<table border="1">
						<tr>
							<th style="width: 10px">BNO</th>
							<th>상품코드</th>
							<th>상품명</th>
							<th>이미지</th>
							<th>원가</th>
							<th>판매가</th>
							<th>차익</th>
							<th>수량</th>
							<th>조회수</th>
							<th>베스트상품</th>
							<th>등록일</th>
						</tr>

						<c:forEach items="${productList}" var="productVO" varStatus="status">

							<tr>
								<!-- <td>${productVO.product_no}</td> -->
								<td>${status.index+1 }</td>
								<td>${productVO.category_no_fk }</td>
								<td><a href='/admin/goods/productRead${pageMaker.makeSearch(pageMaker.cri.page)
								}&product_no=${productVO.product_no }'>${productVO.product_name }</a></td>
								<td><img src="/goods/displayFile?fileName=${ productVO.filesMain}"></td>
								<td>${productVO.cost_price }</td>
								<td>${productVO.selling_price }</td>
								<td>${productVO.profit }</td>
								<td>${productVO.qty }</td>
								<td><span class="badge bg-red">${productVO.hit }</span></td>
								<td>${productVO.is_best }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${productVO.reg_date}" /></td>
							</tr>

						</c:forEach>

					</table>
				</div>
			</div>
				<!-- /.box-body -->
	<div class="text-center">
  		<ul class="pagination">  			
			<!-- 페이징 -->	
			
			<!-- prev -->
			<c:if test="${pageMaker.prev}">
				<li> <a href="productList${pageMaker.makeQuery(pageMaker.startPage-1) }">&laquo;</a></li>
			</c:if>
				
			<!-- page num -->			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
				<li <c:out value="${pageMaker.cri.page==idx? 'class =active':''}" /> >
					<a href="productList${pageMaker.makeQuery(idx)}">${idx}</a>
				</li>
			</c:forEach>
			
			<!-- next -->
			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a href="productList${pageMaker.makeQuery(pageMaker.endPage+1}">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</body>


<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "productList"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {

					self.location = "productRegister";

				});

			});
</script>

<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>
