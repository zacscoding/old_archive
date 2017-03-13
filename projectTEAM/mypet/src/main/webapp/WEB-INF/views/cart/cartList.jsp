<!-- source : http://bootsnipp.com/snippets/featured/shopping-cart-bs-3  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<div class="container">
	<input type="hidden" id="cart_no">
	
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">        	
            <table class="table table-hover">            	
            	<!-- 상단 th -->            
                <thead>
                    <tr>                    	                    	
                    	<th>상품정보</th>
                        <th>수량</th>
                        <th class="text-center">가격</th>
                        <th class="text-center">합계</th>
                        <th> </th>
                    </tr>
                </thead> <!-- .상단 th 끝 -->
								                   
                <c:set var="totalPrice" value="0"/>
				
				<!-- 하단 목록 -->
		        <tbody>
					<c:forEach var="vo" items="${cartList}">
						<c:set var="totalPrice"
							value="${totalPrice + (vo.selling_price*vo.count)}" />
						<!-- 카트 리스트 -->
						<tr id="cartRow${vo.cart_seq}">
							<td class="col-sm-8 col-md-6">
								<!-- 상품 이미지 + 상품 이름-->
								<div class="media">
									<a class="thumbnail pull-left" href="#"> <img
										class="media-object" src="/goods/productDisplayFile?fileName=${vo.filesMain}"
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
							<td class="col-sm-1 col-md-1" style="text-align: center"><input
								type="text" class="form-control" id="#rowCart${vo.count}"
								value="${vo.count}"> 
								<button type="button" class="btn btn-default"
									data-src="${vo.count}" id="countUpBtn">
									<span class="glyphicon btn-group countUpBtn" value="${vo.count}"></span>+
								</button><button type="button" class="btn btn-default"
									data-src="${vo.count}" id="countDownBtn">
									<span class="glyphicon btn-group countDownBtn" value="${vo.count}"></span>-
								</button>
                            	
                            	
								
                            	
							</td>

							<!-- 가격 -->
							<td class="col-sm-1 col-md-1 text-center"><strong>
									<fmt:formatNumber value="${vo.selling_price}" pattern="#.###" />원
							</strong></td>

							<!-- 토탈 -->
							<td class="col-sm-1 col-md-1 text-center"><strong>
									<fmt:formatNumber value="${vo.selling_price * vo.count}" pattern="#.###" />원
									<%-- ${vo.selling_price * vo.count} --%>
							</strong></td>

							<!-- remove btn -->
							<td class="col-sm-1 col-md-1">
								<button type="button" class="btn btn-danger"
									data-src="${cart_seq}" id="removeBtn">
									<span class="glyphicon glyphicon-remove removeBtn" value="${vo.cart_seq}"></span> 삭제
								</button>
							</td>
						</tr>
					</c:forEach> <!-- .카트 리스트 끝 -->
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
	                        	<fmt:formatNumber value="3000" pattern="#.###"/>원
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
	                        <td>
	                        <button type="button" class="btn btn-default" id="goShoppingBtn">
	                            <span class="glyphicon glyphicon-shopping-cart"></span> 쇼핑계속하기
	                        </button></td>
	                        
	                                                
	                        <td>
	                        <button type="button" class="btn btn-success" id="goOrderBtn">
	                            <span class="glyphicon glyphicon-play"></span> 구매하기 
	                        </button></td>
	                    </tr>
	                </tbody><!-- .하단 목록 끝-->                                           
            </table>
        </div>
    </div>
</div>

<script>


function calc(){
	var price = document.getElementById("cost").value * document.getElementById("count").value;
	document.getElementById("price").innerHTML = price + "원";
}

	//쇼핑 계속하기 버튼
	$('#goShoppingBtn').on('click',function(event) {	
		//위의 버튼에 onclick='self.location=/경로';
		 self.location="/goods/cliProductList";
	});	
	
	//결제하기 버튼
	$('#goOrderBtn').on('click',function(event) {
		//위의 버튼에 onclick='self.location=/경로';
		alert('결제 페이지로 이동합니다.')
		 self.location="/order/orderList";
	});	

 	//카트 수량 업데이트	
 	function update(cart_seq,delta) {
		$.ajax({
			type: "POST",
			url:'/cart/'+cart_seq + '/' + delta,
			headers: {
				"Content-Type": "application/json",
				"X-HTTP-Method-Override": "PUT" },
				data:JSON.stringify({cart_seq:cart_seq,delta:delta}),
				dataType:'text',
				success:function(result){
					if(result == 'SUCCESS'){
						//수량 변경하기
						$('#rowCart'+cart_seq).val();
					}
				}
		});
	} 
	
 	//+버튼 클릭 시
	$('#countUpBtn').on('click',function(event) {
		var cart_seq = $(this).attr('value');
		update(cart_seq,1);
	});
	
	//-버튼 클릭시
	$('#countDownBtn').on('click',function(event) {
		var cart_seq = $(this).attr('value');
		//유효성 검사(현재 0개 이면) return;
		update(cart_seq,-1);
	});
	
	// 삭제 버튼 클릭 시 
	$('.removeBtn').on('click',function(event) {
		var cart_seq = $(this).attr('value');
		
		$.ajax({
			type:'delete',
			url:'/cart/'+cart_seq,
			headers: { 
			      "Content-Type": "application/json",
			      "X-HTTP-Method-Override": "DELETE" },
			dataType:'text', 
			success:function(result){
				if(result == 'SUCCESS'){
					alert("삭제 되었습니다.");
					$('#rowCart'+cart_seq).remove();
				}
			}
		});		
	});
	
	
</script>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>