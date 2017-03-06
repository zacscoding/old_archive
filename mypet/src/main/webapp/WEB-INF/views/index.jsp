<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="include/header.jsp" %>
	
	<!-- 
	<c:forEach var="vo" items="${carouselList}" varStatus = "status">
		<c:if test="${stats==1}">
			<img src="${vo.image}">	
		</c:if>		
	</c:forEach>
	 -->	
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
      </div> <!-- .캐러셀 부분 끝  -->
    	
    
    <!-- 상품 컨테이너 -->
     <div class="container">
     <!-- 헤더 -->
    <header> <!-- 카테고리 이름 -->
      <h1><i class="fa fa-thumbs-o-up"></i> 추천상품</h1>
    </header><!-- .헤더 끝 -->    
    
    <div class="row">
    	<!-- 한개의 상품 -->
      <div class="col-xs-6 col-sm-3"> 
        <div class="thumbnail">
           <a href="#">
                <img src="/resources/bootstrap/imgs/sec_SM-T800NZWKKOO_007_front_white.jpg" alt="">
                <div class="caption">
                  <ul class="list-inline">
                    <li class="pack">패키지</li>
                    <li class="disc">즉시할인쿠폰</li>
                  </ul>
                  <ul class="list-unstyled block">
                    <li><b>삼성 갤럭시 탭S</b></li>
                    <li><b>SM-T800NZWKKOO</b></li>
                    <li> </li>
                  </ul>
                  <dl>
                    <dt>판매가</dt>
                    <dd>848,000 원</dd>
                    <dt class="red">혜택가</dt>
                    <dd class="red">759,000 원</dd>
                  </dl>
               </div> 
            </a>    
         </div>  
        </div><!-- .한개의 상품 끝 -->
        
        <div class="col-xs-6 col-sm-3">        
          <div class="thumbnail">
             <a href="#">
                  <img src="/resources/bootstrap/imgs/sec_NT910S5J-K20W_018_Front-Open_white.jpg" alt="">
                  <div class="caption">
                    <ul class="list-inline">
                      <li class="pack">패키지</li>
                      <li class="disc">즉시할인쿠폰</li>
                    </ul>
                    <ul class="list-unstyled">
                      <li><b>삼성 아티브 북9 Style</b></li>
                      <li><b>NT910S5J-K20W</b></li>
                       <li><b>(39.6cm LED 디스플레이)</b></li>
                    </ul>
                    <dl>
                      <dt>판매가</dt>
                      <dd> 1,280,000 원</dd>
                      <dt class="red">혜택가</dt>
                      <dd class="red"> 990,000 원</dd>
                    </dl>
                 </div> 
<!--                <div class="zoom"> 자세히 보기 </div> -->
              </a>    
           </div>   
        </div>            
        <div class="col-xs-6 col-sm-3">
          <div class="thumbnail">
             <a href="#">
                  <img src="/resources/bootstrap/imgs/sec_EX-S30ANW-KR_000000001_Front_white.jpg" alt="">
                  <div class="caption">
                    <ul class="list-inline">
                      <li class="pack">패키지</li>
                      <li class="disc">즉시할인쿠폰</li>
                    </ul>
                    <ul class="list-unstyled block">
                      <li><b>삼성 표준 단렌즈 </b></li>
                      <li><b>30mm F2.0</b></li>
                      <li><b>EX-S30ANW/KR</b></li>
                    </ul>
                    <dl>
                      <dt>판매가</dt>
                      <dd>350,000 원</dd>
                      <dt class="red">혜택가</dt>
                      <dd class="red"> 249,000 원</dd>
                    </dl>
                 </div> 
              </a>    
           </div>             
        </div>
        <div class="col-xs-6 col-sm-3">
         <div class="thumbnail">
             <a href="#">
                  <img src="/resources/bootstrap/imgs/sec_EO-SB330EWEG_001_Dynamic_white.jpg" alt="">
                  <div class="caption">
                    <ul class="list-inline">
                      <li class="pack">패키지</li>
                      <li class="disc">즉시할인쿠폰</li>
                    </ul>
                    <ul class="list-unstyled block">
                      <li><b>삼성 블루투스 스피커</b></li>
                      <li><b>EO-SB330EWEG</b></li>
                    </ul>
                    <dl>
                      <dt>판매가</dt>
                      <dd>199,000 원</dd>
                      <dt class="red">혜택가</dt>
                      <dd class="red">99,000 원</dd>
                    </dl>
                 </div> 
              </a>    
           </div>             
        </div>
      </div> <!-- .row끝 -->
   </div> <!-- .상품 컨테이너 끝-->
	
    
<%@include file="include/footer.jsp"%>