<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
   <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>

<style>
.fileDrop {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
.fileDropMain {
  width: 80%;
  height: 100px;
  border: 1px dotted gray;
  background-color: lightslategrey;
  margin: auto;
  
}
</style>

<form id='registerForm' role="form" method="post">
	<div>
		<div>
			<label>상품명</label> <input type="text"
				name="product_name" placeholder="상품명 입력">
		</div>
		<div>
			<label>카테고리명</label>
			<select name="category_no_fk">
				<c:forEach items="${categoryList }" var="categoryVO">
					<option value="${categoryVO.category_no }">
					${categoryVO.category_no } - ${categoryVO.cate_name}</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<label>원가</label> <input type="text"
				name="cost_price" placeholder="구분자 제외 숫자만 입력">
		</div>
		<div>
			<label>판매가</label> <input type="text"
				name="selling_price" placeholder="구분자 제외 숫자만 입력">
		</div>
		<div>
			<label>수량</label> <input type="text"
				name="qty" placeholder="구분자 제외 숫자만 입력">
		</div>
		<div>
			<label>베스트 상품</label>
			<select name="is_best">
				<option value="n" selected>일반</option>
				<option value="y">베스트</option>
			</select>
		</div>
		<div>
			<label>상세 내용</label>
			<textarea name="content" cols="50" rows="5" placeholder="상품 상세 내용 입력"></textarea>
		</div>

		<div class="form-group" id="mainImage" style="display:block">
			<label for="exampleInputEmail1">Main DROP Here</label>
			<div class="fileDropMain"></div>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Content DROP Here</label>
			<div class="fileDrop"></div>
		</div>
	</div>

	<!-- /.box-body -->

	<div class="box-footer">
		<div>
			<hr>
		</div>

		<ul class="mailbox-attachments clearfix uploadedList">
		</ul>

		<button type="submit" class="btn btn-primary">Submit</button>

	</div>
</form>



<!-- <script type="text/javascript" src="/resources/js/upload.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>


<script id="template" type="text/x-handlebars-template">
<li>
  <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
  <div class="mailbox-attachment-info">
	<a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
	<a href="{{fullName}}" 
     class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
	</span>
  </div>
</li>                
</script>    

<script>


function checkImageType(fileName){
	
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);

}

function getFileInfo(fullName){
		
	var fileName,imgsrc, getLink;
	
	var fileLink;
	
	if(checkImageType(fullName)){
		imgsrc = "/goods/displayFile?fileName="+fullName;
		fileLink = fullName.substr(14);
		
		var front = fullName.substr(0,12); // /2015/07/01/ 
		var end = fullName.substr(14);
		
		getLink = "/goods/displayFile?fileName="+front + end;
		
	}else{
		
	}
	fileName = fileLink.substr(fileLink.indexOf("_")+1);
	
	return  {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
	
}

var template = Handlebars.compile($("#template").html());


$(".fileDrop").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDrop").on("drop", function(event){
	event.preventDefault();
	
	var files = event.originalEvent.dataTransfer.files;

	var file = files[0];
	
	var formData = new FormData();
	
	formData.append("file", file);
	
	
	$.ajax({
		  url: '/goods/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  
			  var html = template(fileInfo);
			  
			  $(".uploadedList").append(html);
		  }
		});	
});


$(".fileDropMain").on("dragenter dragover", function(event){
	event.preventDefault();
});


$(".fileDropMain").on("drop", function(event){
	event.preventDefault();
	
	var filesMain = event.originalEvent.dataTransfer.files;
	
	var fileMain = filesMain[0];
	
	var formData = new FormData();
	
	formData.append("fileMain", fileMain);	
	
	
	$.ajax({
		  url: '/goods/uploadAjaxMain',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  var fileInfo = getFileInfo(data);
			  
			  var html = template(fileInfo);
			  
			  $(".uploadedList").append(html);
			  $('#mainImage').css('display','none');
		  }
		});	
});



$("#registerForm").submit(function(event){
	event.preventDefault();
	
	var that = $(this);
	
	var str ="";
	$(".uploadedList .delbtn").each(function(index){
		 str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href") +"'> ";
	});
	
	that.append(str);

	that.get(0).submit();
});


$(".uploadedList").on("click", ".delbtn" ,function(event){

	event.preventDefault();

	var that = $(this);

	$.ajax({
	url:"/goods/deleteFile",
	type:"post",
	data: {fileName:$(this).attr("href")},
	dataType:"text",
	success:function(result){
	console.log("RESULT: " + result);
	if(result == 'deleted'){
	that.closest("li").remove();
	}
	}
	});
});



</script>