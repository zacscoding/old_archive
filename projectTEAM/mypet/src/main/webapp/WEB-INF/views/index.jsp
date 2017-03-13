<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
	<!-- 
	<c:forEach var="vo" items="${carouselList}" varStatus = "status">
		<c:if test="${stats==1}">
			<img src="${vo.image}">	
		</c:if>		
	</c:forEach>
	 -->	
	 
	<%--
	<!-- 캐러셀 부분 시작 -->	
    <div id="carousel-generic" class="carousel slide">
      <!-- Indicators -->
       <ol class="carousel-indicators">
       	<c:forEach  var="i" begin="0" end="${carouselSize}" step="1">
    		<li data-target="#carousel-generic" data-slide-to="${i}"
    			 <c:out value="${i==0?'class=active' : ''}"/> ></li>
    	</c:forEach>
       	 <!-- 	
         <li data-target="#carousel-generic" data-slide-to="0" class="active"></li>
         <li data-target="#carousel-generic" data-slide-to="1"></li>
         <li data-target="#carousel-generic" data-slide-to="2"></li>
         <li data-target="#carousel-generic" data-slide-to="3"></li>
         <li data-target="#carousel-generic" data-slide-to="4"></li>
           -->
       </ol>
     	<!-- Carousel items -->     
       <div class="carousel-inner">
		 <c:forEach var="vo" items="${carouselList}" varStatus="status">
		 	<div class="<c:out value="${status.index==0?'item active':'item'}"/>" >
		 		<img src="${vo.image}" style="width:1200px;height:800;">
		 		<div class="carousel-caption">
		 			${vo.content}
		 		</div>
		 	</div>
    	</c:forEach>        
       </div>
      <!-- Controls -->
        <a class="left carousel-control" href="#carousel-generic" data-slide="prev">
          <img src="/resources/bootstrap/imgs/left.png" class="control">
        </a>
        <a class="right carousel-control" href="#carousel-generic" data-slide="next">
          <img src="/resources/bootstrap/imgs/right.png" class="control">
        </a>
      </div> <!-- .캐러셀 부분 끝  -->
     --%>	
     <!-- 캐러셀 부분 시작 -->
    <div id="carousel-generic" class="carousel slide">
      <!-- Indicators -->
       <ol class="carousel-indicators">
         	<c:forEach  var="i" begin="0" end="${carouselSize}" step="1">
    		<li data-target="#carousel-generic" data-slide-to="${i}"
    			 <c:out value="${i==0?'class=active' : ''}"/> ></li>
    	</c:forEach>         
       </ol>
     <!-- Carousel items -->
       <div class="carousel-inner">
		 <c:forEach var="vo" items="${carouselList}" varStatus="status">
		 	<div class="<c:out value="${status.index==0?'item active':'item'}"/>" >
		 		<img src="${vo.image}">
		 		<div class="carousel-caption">
		 			${vo.content}
		 		</div>
		 	</div>
    	</c:forEach>        
       </div>
      <!-- Controls -->
        <a class="left carousel-control" href="#carousel-generic" data-slide="prev">
          <img src="/resources/bootstrap/imgs/left.png" class="control">
        </a>
        <a class="right carousel-control" href="#carousel-generic" data-slide="next">
          <img src="/resources/bootstrap/imgs/right.png" class="control">
        </a>
      </div>
    <!--// 캐러셀 부분 끝  -->
    <!-- 상품 컨테이너 -->
     <div class="container">
     <!-- 헤더 -->
    <header> <!-- 카테고리 이름 -->
      <h1><i class="fa fa-thumbs-o-up"></i> 추천상품</h1>
    </header><!-- .헤더 끝 -->    
    
    <div class="row">
    	<!-- 한개의 상품 -->
     <c:forEach items="${bestView}" var="productVO" begin="0" end="3">
					<div class="col-xs-6 col-sm-3"> 
				        <div class="thumbnail">
				           <a href="/goods/cliProductRead?product_no=${productVO.product_no }">
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
      </div> <!-- .row끝 -->
   </div> <!-- .상품 컨테이너 끝-->
	
    
<%@include file="/WEB-INF/views/include/footer.jsp"%>