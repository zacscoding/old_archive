<!-- source : http://bootsnipp.com/snippets/featured/shopping-cart-bs-3  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="include/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">        	
            <table class="table table-hover">
            	
            	<!-- 상단 th -->            
                <thead>
                    <tr>
                    	<!-- 1.글씨 적용 -->
                    	<!-- 
                    	<th class="text-info"><h4>Product</h4></th>
                    	<th class="text-info"><h4>Quantity</h4></th>
                    	<th class="text-info"><h4>Price</h4></th>
                    	<th class="text-info" align="right"><h4>Total</h4></th>
                    	<th class="text-info"> </th>
                    	-->
                    	
                    	<!-- 2.글씨 적용X -->                    	
                    	<th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                         
                        
                    </tr>
                </thead> <!-- .상단 th 끝 -->
                
                <!-- 하단 목록 -->
                <tbody>
                	<!-- 카트 리스트 -->
                    <tr>
                        <td class="col-sm-8 col-md-6">
                        <!-- 상품 이미지 + 상품 이름-->	
                        <div class="media">                        	
                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><a href="#">Product name</a></h4>
                                <h5 class="media-heading"> by <a href="#">Brand name</a></h5>
                                <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                            </div>
                        </div></td> <!-- .상품 이미지 + 상품 이름 끝 -->
                        
                        <!-- 수량 -->                        
                        <td class="col-sm-1 col-md-1" style="text-align: center">
                        	<input type="email" class="form-control" id="exampleInputEmail1" value="3">
                        </td>
                        
                        <!-- 가격 -->
                        <td class="col-sm-1 col-md-1 text-center"><strong>$4.87</strong></td>
                        
                        <!-- 토탈 -->
                        <td class="col-sm-1 col-md-1 text-center"><strong>$14.61</strong></td>
                        
                        <!-- remove btn -->
                        <td class="col-sm-1 col-md-1">
                        <button type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-remove"></span> Remove
                        </button></td>
                    </tr> <!-- .카트 리스트 끝 -->
					                                        
                    <!-- Subtotal -->
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Subtotal</h5></td>
                        <td class="text-right"><h5><strong>$24.59</strong></h5></td>
                    </tr> <!-- .Subtotal 끝-->
                    
                    <!-- 배송비 -->
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h5>Estimated shipping</h5></td>
                        <td class="text-right"><h5><strong>$6.94</strong></h5></td>
                    </tr> <!-- .배송비 끝 --> 
                    
                    <!-- total -->
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><strong>$31.53</strong></h3></td>
                    </tr>
                    
                    <!-- 하단 버튼 -->
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>
                        <button type="button" class="btn btn-default" id="goShoppingBtn" ">
                            <span class="glyphicon glyphicon-shopping-cart"></span> 쇼핑계속하기
                        </button></td>
                        
                                                
                        <td>
                        <button type="button" class="btn btn-success">
                            	구매하기 <span class="glyphicon glyphicon-play"></span>
                        </button></td>
                    </tr>
                </tbody><!-- .하단 목록 끝-->                
            </table>
        </div>
    </div>
</div>

<script>

	$('#goShoppingBtn').on('click',function(event) {
		alert('쇼핑 계속하기 버튼 클릭');		
		//위의 버튼에 onclick='self.location=/';로 할지 경로 유지할 지 고민하기
	});
	
	
	
	

	
	
</script>



<%@ include file="include/footer.jsp" %>