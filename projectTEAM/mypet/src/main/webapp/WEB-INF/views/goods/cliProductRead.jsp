<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.*"%>

<%@ include file="../include/header.jsp" %>

    
<script>


function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
	
}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "productDisplayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "productDisplayFile?fileName="+front + end;
		
	}else{
		
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- Main content -->

<div align="center">
<table width="85%">
	<tr>
		<%@ include file="cliCategory.jsp" %>
		
		<td width="30%" align="center">
			<img src="/goods/productDisplayFile?fileName=${ productVO.filesMain}">
		</td>
		<td width="50%">
		<form role="form" action="cart" method="post">
			<table>
				<input type="hidden" name="product_no" value="${productVO.product_no }">
				<tr>
					<th>
						상품이름
					</th>
					<td>
						${productVO.product_name }
						<br>
						<br>
					</td>
				</tr>
				<tr>
					<th>
						판매가
					</th>
					<td>
						<input type="text" id="selling_price" value="${productVO.selling_price }" readonly>
						<br>
						<br>
					</td>
				</tr>
				<tr>
					<th>
						수량
					</th>
					<td style="text-align: center">
						<input type="number" class="form-control" name="count" id="count"
								value="0" min="0" max="${productVO.qty }" oninput="calc()">
						* 재고 초과 시 최대수량이 제한될 수 있습니다.
						<br>
						<br>
					</td>
				</tr>
				<tr>
					<th>
						결제액
					</th>
					<td>
						<p id="price"></p>
					<br>
					<br>
					</td>
					
				</tr>
				<tr>
					<td>
						<button type="submit" class="btn btn-primary" id="goCartBtn">GO CART</button>
						<button type="submit" class="btn btn-primary" id="goBuyBtn">GO BUY</button>
					</td>
				</tr>
			</table>
		</td>
		
	</tr>
	<tr>
		<td colspan="2">
			상품홍보
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<ul class="mailbox-attachments clearfix uploadedList"></ul>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			 <div class="box-footer">
			   <button type="submit" class="btn btn-primary" id="goListBtn">GO LIST </button>
			 </div>
			 <br>
			 <br>
		</td>
	</tr>
	
	</form>
</table>


<%@ include file="../reviewList.jsp" %>

</div>

<!-- /.content -->


<script id="templateAttach" type="text/x-handlebars-template">

  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	</span>
  </div>
               
</script>  


<script>

function calc(){
	var price = document.getElementById("selling_price").value * document.getElementById("count").value;
	document.getElementById("price").innerHTML = 
		price + "원" ;
}

$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$("#goListBtn ").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/goods/cliProductList");
		formObj.submit();
	});
	
	$("#goCartBtn ").on("click", function(){
		formObj.attr("method", "post");
		formObj.attr("action", "/cart/cart");
		formObj.submit();
	});
	
	$("#goBuyBtn ").on("click", function(){
		formObj.attr("method", "post");
		formObj.attr("action", "/order");
		formObj.submit();
	});
	
	
	var pno_fk = ${productVO.product_no};
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/goods/cliGetFile/"+pno_fk,function(list){
		$(list).each(function(){
			
			var fileInfo = getFileInfo(this);
			
			var html = template(fileInfo);
			
			 $(".uploadedList").append(html);
			
		});
	});			
});
</script>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>


