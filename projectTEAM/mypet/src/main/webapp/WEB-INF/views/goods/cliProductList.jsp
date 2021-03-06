<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ include file="../include/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
<div align="center">

<table width="85%">
	<tr>	
		<%@ include file="cliCategory.jsp" %>
		
		<td align="center" width="80%">
				<div class='box-body'>
					<select name="searchType">
						<option value="n"
							<c:out value="${cri.searchType == null?'selected':''}"/>>
							---</option>
						<option value="p"
							<c:out value="${cri.searchType eq 'p'?'selected':''}"/>>
							상품명</option>
						<option value="b"
							<c:out value="${cri.searchType eq 'b'?'selected':''}"/>>
							상품구분(y/n)</option>
					</select> <input type="text" name='keyword' id="keywordInput"
						value='${cri.keyword }'>
					<button id='searchBtn'>Search</button>
				</div>
				<br>
				<br>
		</td>
	</tr>
	<tr>
		<td>
			<div class="col-sm-12"  align="center">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title" align="center"> < 베스트 상품 > </h3>
					</div>
				</div>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div class="box-footer" align="right">
				<button id="moreBtn" class="btn btn-default">Best 더보기</button>
				<br>
				<br>
			</div>	
		</td>
	</tr>
	<tr>
		<td>	
				<c:forEach items="${bestCliProductList}" var="productVO" begin="0" end="3">
					<div class="col-xs-6 col-sm-3"> 
				        <div class="thumbnail">
				           <a href="/goods/cliProductRead${pageMaker.makeProductSearch(pageMaker.cri.page)
									}&product_no=${productVO.product_no }">
				                <img src="/goods/productDisplayFile?fileName=${ productVO.filesMain}">
				                <div class="caption">
				                  <ul class="list-inline">
				                    <c:if test="${-1 < productVO.hit }">
				                    <li class="disc">인기상품</li>
				                    </c:if>
				                    <c:if test="${2000 > productVO.profit }">
									<li class="pack">알뜰상품</li>
				                    </c:if>
				                  </ul>
				                  <ul class="list-unstyled block">
				                    <li><b>${productVO.product_name }</b></li>
				                 
				                  </ul>
				                  <dl>
				                    <dt>판매가</dt>
				                    <dd>${productVO.selling_price } 원</dd>
				                    <dt class="red">혜택가</dt>
				                    <dd class="red"><fmt:parseNumber var="var3" value="${productVO.selling_price / 10 * 9 }" integerOnly="true"/>
				                    ${var3 } 원</dd>
				                  </dl>
				               </div> 
				             <!--  <div class="zoom"> 자세히 보기 </div>  --> 
				            </a>    
				         </div>  
				     </div>
				</c:forEach>
				<br>
				<br>	
		</td>		
	</tr>
	<tr>
		<td>
			<div class="col-sm-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title" align="center"> < 일반 상품 > </h3>
					</div>
				</div>
			</div>
			<br>
			<br>
		</td>
	</tr>
	<tr>
		<td>
			<div class="box-footer" align="right">
					<button id="hitBtn" class="btn btn-default">조회순</button>
					<button id="lowBtn" class="btn btn-default">가격낮은순</button>
					<button id="highBtn" class="btn btn-default">가격높은순</button>
			</div>
			<br>
			<br>
		</td>
	</tr>
	<tr>
		<td>	
			<c:forEach items="${cliProductList}" var="productVO">
				
				
				<div class="col-xs-6 col-sm-3"> 
			        <div class="thumbnail">
			           <a href="/goods/cliProductRead${pageMaker.makeProductSearch(pageMaker.cri.page)
								}&product_no=${productVO.product_no }">
			                <img src="/goods/productDisplayFile?fileName=${ productVO.filesMain}">
			                <div class="caption">
			                  <ul class="list-inline">
			                    <c:if test="${-1 < productVO.hit }">
			                    <li class="disc">인기상품</li>
			                    </c:if>
			                    <c:if test="${2000 > productVO.profit }">
								<li class="pack">알뜰상품</li>
			                    </c:if>
			                  </ul>
			                  <ul class="list-unstyled block">
			                    <li><b>${productVO.product_name }</b></li>
			                 
			                  </ul>
			                  <dl>
			                    <dt>판매가</dt>
			                    <dd>${productVO.selling_price } 원</dd>
			                    <dt class="red">혜택가</dt>
			                    <dd class="red"><fmt:parseNumber var="var3" value="${productVO.selling_price / 10 * 9 }" integerOnly="true"/>
			                    ${var3 } 원</dd>
			                  </dl>
			               </div> 
			             <!--  <div class="zoom"> 자세히 보기 </div>  --> 
			            </a>    
			         </div>  
			     </div>
			</c:forEach>
		</td>		
	</tr>
	<tr>
		<td>
				<div class="text-center">
			  		<ul class="pagination">  			
						<!-- 페이징 -->	
						
						<!-- prev -->
						<c:if test="${pageMaker.prev}">
							<li> <a href="cliProductList${pageMaker.makeProductSearch(pageMaker.startPage-1) }">&laquo;</a></li>
						</c:if>
							
						<!-- page num -->			
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
							<li <c:out value="${pageMaker.cri.page==idx? 'class =active':''}" /> >
								<a href="cliProductList${pageMaker.makeProductSearch(idx)}">${idx}</a>
							</li>
						</c:forEach>
						
						<!-- next -->
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<li><a href="cliProductList${pageMaker.makeProductSearch(pageMaker.endPage+1}">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
				
				<!-- /.box-body -->
		</td>
	</tr>
	
	
</table>
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
							
							self.location = "cliProductList"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + encodeURIComponent($('#keywordInput').val())
									+ "&cateType=${cateType}";
						});
					
				
					$('#hitBtn').on(
							"click",
							function(event){
								event.preventDefault();
								self.location = "cliProductList"
											+ '${pageMaker.makeSearch(1)}'
											+ "&cateType=${cateType}"
											+ "&conditionType=" + encodeURIComponent('t');
							});
					
					$('#lowBtn').on(
							"click",
							function(event){
								self.location = "cliProductList"
									+ '${pageMaker.makeSearch(1)}'
									+ "&cateType=${cateType}"
									+ "&conditionType=" + encodeURIComponent('l');
							});
					
					$('#highBtn').on(
							"click",
							function(event){
								self.location = "cliProductList"
									+ '${pageMaker.makeSearch(1)}'
									+ "&cateType=${cateType}"
									+ "&conditionType=" + encodeURIComponent('h');
							});
					
					$('#moreBtn').on(
							"click",
							function(event){
								self.location = "cliBestProductList"
									+ '${pageMaker.makeSearch(1)}'
									+ "&cateType=${cateType}";
							});
			});
			
	
</script>


<%@include file="../include/footer.jsp"%>