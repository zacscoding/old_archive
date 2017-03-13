<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ include file="../include/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<body>
<div align="center">
<br>
<br>
<table width="85%">
	<tr>	
		<%@ include file="cliCategory.jsp" %>
		
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
				<c:forEach items="${bestCliProductList}" var="productVO">
					<div class="col-xs-6 col-sm-3"> 
				        <div class="thumbnail">
				           <a href="/goods/cliProductRead${bestPageMaker.makeProductSearch(bestPageMaker.cri.page)
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
</table>
</div>
</body>


<%@include file="../include/footer.jsp"%>