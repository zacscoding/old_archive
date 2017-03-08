<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.io.*"%>
<%@ include file="/WEB-INF/views/admin/include/header.jsp" %>	
<script>

function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "displayFile?fileName="+front + end;
		
	}else{
		
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<!-- Main content -->
    <style type="text/css">
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
  	
    </style>

    <div class='popup back' style="display:none;"></div>
    <div id="popup_front" class='popup front' style="display:none;">
     <img id="popup_img">
    </div>

<section class="content">
	
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" action="modifyPage" method="post">

					<input type='hidden' name='product_no' value="${productVO.product_no}">
				</form>

				<div class="box-body">
					<table border="1">
						<tr>	
							<td colspan="2" align="center">[${productVO.product_name }] 상품정보</td>
						</tr>
						<tr>
							<td>
								<img src="/goods/displayFile?fileName=${ productVO.filesMain}">
							</td>
							<td>
								상품번호 : ${productVO.product_no }<br>
								상품코드 : ${productVO.category_no_fk }<br>
								원가 : ${productVO.cost_price }<br>
								판매가 : ${productVO.selling_price }<br>
								차액 : ${productVO.profit }<br>
								수량 : ${productVO.qty }<br>
								조회수 : ${productVO.hit }<br>
								상품구분 : ${productVO.is_best }<br>
								(y: 베스트, n : 일반)<br>
								등록일 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
										value="${productVO.reg_date}" /><br>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							상품홍보
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">			
							<ul class="mailbox-attachments clearfix uploadedList"></ul>
							</td>
						</tr>
					</table>
				</div>
				<!-- /.box-body -->



 <div class="box-footer">
   <button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
   <button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
   <button type="submit" class="btn btn-primary" id="goListBtn">GO LIST </button>
 </div>


	
</section>
<!-- /.content -->


<script id="templateAttach" type="text/x-handlebars-template">
<li data-src='{{fullName}}'>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	</span>
  </div>
</li>                
</script>  


<script>
$(document).ready(function(){
	
	var formObj = $("form[role='form']");
	
	console.log(formObj);
	
	$("#modifyBtn").on("click", function(){
		formObj.attr("action", "/sboard/modifyPage");
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$("#removeBtn").on("click", function(){
		
		
		/* var arr = [];
		$(".uploadedList li").each(function(index){
			 arr.push($(this).attr("data-src"));
		});
		
		if(arr.length > 0){
			$.post("/goods/deleteAllFiles",{files:arr}, function(){
				
			});
			
		}
		 */
		formObj.attr("action", "/admin/goods/removePage");
		formObj.submit();
	});	
	
	$("#goListBtn ").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/admin/goods/productList");
		formObj.submit();
	});
	
	var pno_fk = ${productVO.product_no};
	var template = Handlebars.compile($("#templateAttach").html());
	
	$.getJSON("/admin/goods/getFile/"+pno_fk,function(list){
		$(list).each(function(){
			
			var fileInfo = getFileInfo(this);
			
			var html = template(fileInfo);
			
			 $(".uploadedList").append(html);
			
		});
	});

	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink = $(this).attr("href");
		
		if(checkImageType(fileLink)){
			
			event.preventDefault();
					
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
					
			$(".popup").show('slow');
			imgTag.addClass("show");		
		}	
	});
	
	$("#popup_img").on("click", function(){
		
		$(".popup").hide('slow');
		
	});	
	
		
	
});
</script>

<%@ include file="/WEB-INF/views/admin/include/footer.jsp" %>