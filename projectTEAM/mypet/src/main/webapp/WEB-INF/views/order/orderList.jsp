<!-- source : http://bootsnipp.com/snippets/featured/shopping-cart-bs-3  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<br/><br/><br/><br/>

<div class="container">
	<input type="hidden" id="cart_no">
	
    <div class="row">
    	<div class="col-sm-12 col-md-10 col-md-offset-1">
			<table class="table table-hover">
			<colgroup>
			<!-- <col width="75px;" /> -->
			<col width="467px;" />
			<col width="95px;" />
			<col width="95px;" />
			<col width="78px;" />
			<col width="90px" />
			</colgroup>
				<tr>
				<h2><b>주문 내역서</b></h2>
				</tr>
				<tr>
					<!-- <th style="width: 50px">번호</th> -->
					<th scope="col" class="line" align="center">상품정보</th>
					<th>수량</th>
					<th>가격</th>
					<th>총 결제금액</th>
				</tr>
	
				<c:forEach items="${orderList}" var="orderVO">
	
					<tr>
						<!-- <td>${orderVO.order_no}</td> -->
						<td><img src="/goods/displayFile?fileName=${productVO.filesMain}"></td>
						<td><a href='/admin/goods/productRead${pageMaker.makeSearch(pageMaker.cri.page)
						}&product_no=${productVO.product_no }'>${productVO.product_name }</a></td>
						<%-- <td>${productVO.cost_price }</td>
						<td>${productVO.selling_price }</td> --%>
						<td>${orderVO.quantity }</td>
					</tr>
	
				</c:forEach>
	
			</table>
			
			<br><br>
			<!-- 배송지 정보 -->
			<h4>배송지 정보 입력</h4>
			<hr align="left" size="10" width="99%" color="#0000FF" />
			<div>
            	<label>수령인</label> 
            	<input type="text" name="mname" size="20"
            	placeholder="수령인을 입력해주세요">
           	</div>
           	<div>	                    	
            	<label>연락처</label>
            	<input type="text" name="phone1" class="box" size="3" maxlength="3"> -
				<input type="text" name="phone2" class="box" size="4" maxlength="4"> -
				<input type="text" name="phone3" class="box" size="4" maxlength="4">
           	</div>
           	<div>
            	<label>배송지</label>
            	<input type="text" name="address" size="40">
            	<input type="text" name="address" size="20">
            <div>
            	<label>배송 메시지</label>
            	<input type="text" name="memo" size="60" 
            	placeholder="택배 기사님께 전달할 배송 메시지를 입력해 주세요" >
            </div>

            <br><br>
		</div>
	</div>
	

	
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">        
        	
            <table class="table table-hover"> 
            	<!-- 상단 th -->            
            	
                <thead>
                    <tr>                    	                    	
                    	<th scope="col" class="line" align="center">Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                </thead> <!-- .상단 th 끝 -->
								                   
                <c:set var="totalPrice" value="0"/>
				
				<!-- 하단 목록 -->
		        <tbody>
					<c:forEach var="vo" items="${orderList}">
						<c:set var="totalPrice"
							value="${totalPrice + (vo.selling_price*vo.count)}" />
						<!-- 주문 리스트 -->
						<tr id="orderRow${vo.order_no}">
							<td class="col-sm-8 col-md-6">
								<!-- 상품 이미지 + 상품 이름-->
								<div class="media">
									<a class="thumbnail pull-left" href="#"> <img
										class="media-object" src="/displayFile?fileName={vo.fileMain}"
										style="width: 72px; height: 72px;">
									</a>
									<div class="media-body">
										<h4 class="media-heading">
											<a href="#">${vo.product_name}</a>
										</h4>
										<!-- <h5 class="media-heading"> by <a href="#">Brand name</a></h5>  -->
										<span>Status: </span><span class="text-success"><strong>In
												Stock</strong></span>
									</div>
								</div>
							</td>
							<!-- .상품 이미지 + 상품 이름 끝 -->

							<!-- 수량 -->
							<td class="col-sm-1 col-md-1" style="text-align: center">
							<button type="button">+
	                         	<span class="glyphicon glyphicon-play"></span>
	                        </button>
							<input
								type="email" class="form-control" id="exampleInputEmail1"
								value="${vo.count}"></td>
							<button type="button">-
	                         	<span class="glyphicon glyphicon-play"></span>
	                        </button>
							<!-- 가격 -->
							<td class="col-sm-1 col-md-1 text-center"><strong>
									<fmt:formatNumber value="${vo.selling_price}" pattern="#.###" />원
							</strong></td>

							<!-- 토탈 -->
							<td class="col-sm-1 col-md-1 text-center"><strong>
									<fmt:formatNumber value="${vo.selling_price}" pattern="#.###" />원
									${vo.selling_price * vo.count}
							</strong></td>

							<!-- remove btn -->
							<td class="col-sm-1 col-md-1">
								<button type="button" class="btn btn-danger"
									data-src="${order_no}" id="removeBtn">
									<span class="glyphicon glyphicon-remove removeBtn"></span> Remove
								</button>
							</td>
						</tr>
					</c:forEach> <!-- .주문 리스트 끝 -->
					<!-- Subtotal -->
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h5>Subtotal</h5></td>
	                        <td class="text-right"><h5><strong>
	                        <fmt:formatNumber value="${vo.selling_price}" pattern="#.###"/>원
	                        	${totalPrice}
	                        	</strong></h5></td>
	                    </tr> <!-- .Subtotal 끝-->
	                    
	                    <!-- 배송비 -->
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h5>Estimated shipping</h5></td>
	                        <td class="text-right"><h5><strong>
	                        	<font color="red"><fmt:formatNumber value="3000" pattern="#.###"/>원</font>
	                        	</strong></h5></td>
	                        	<c:set var="totalPrice" value="${totalPrice+3000}"/>
	                    </tr> <!-- .배송비 끝 --> 
	                    
	                    <!-- total -->
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td><h3>Total</h3></td>
	                        <td class="text-right"><h3><strong>
	                        	${totalPrice}</strong></h3></td>
	                    </tr>
	                    
	                    
 
	                    
						
                    	
	                    
	                    
	                    
	                    <!-- 하단 버튼 -->
	                    <tr>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                        <td>   </td>
	                                                
	                        <td>
	                        <button type="button" class="btn btn-success">
	                            	결제하기 <span class="glyphicon glyphicon-play"></span>
	                        </button></td>
	                    </tr>
	                </tbody><!-- .하단 목록 끝-->                                           
            </table>
        </div>
    </div>
</div>

<script>

	//결제하기 버튼
	$('#goPaymentBtn').on('click',function(event) {
		//위의 버튼에 onclick='self.location=/경로';
		 self.location="/order/orderDetails";
	});	

</script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %>