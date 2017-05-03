<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Regist Article <small>from zac`s page</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/"><i class="fa fa-dashboard"></i> Home</a></li>
			<li><a href="#">Forms</a></li>
			<li class="active">Editors</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-12">
				<div class="box box-info">
					<div class="box-header">
						<h3 class="box-title">
							CK Editor <small>Advanced and full of features</small>
						</h3>
						<!-- tools box -->
						<div class="pull-right box-tools">
							<button type="button" class="btn btn-info btn-sm"
								data-widget="collapse" data-toggle="tooltip" title="Collapse">
								<i class="fa fa-minus"></i>
							</button>
							<!-- 
							<button type="button" class="btn btn-info btn-sm"
								data-widget="remove" data-toggle="tooltip" title="Remove">
								<i class="fa fa-times"></i>
							</button>
							 -->
						</div>
						<!-- /. tools -->
					</div>
					<!-- /.box-header -->

					<div class="box-body pad">
						<!-- 폼 -->
						<form role="registForm" action="/testRegist" method="POST">
							<!-- 카테고리 -->
							<div class="form-group">
								<select class="form-control">
									<option>Select category</option>
									<option>option 1</option>
									<option>option 2</option>
									<option>option 3</option>
									<option>option 4</option>
									<option>option 5</option>
								</select>
							</div>
							<!-- /. 카테고리 끝 -->

							<!-- 제목 -->
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Title">
							</div>
							<!-- /. 제목 끝 -->

							<!-- 태그 -->
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Tags">
							</div>
							<!-- /. 태그 끝 -->

							<!-- 파일 -->
							<div class="form-group">
								<!-- 이미지 -->
								<a class="btn btn-default" style="margin-right: 10px;"
									data-toggle="modal" data-target="#imageModal"> <i
									class="fa  fa-file-image-o"></i>Images
								</a>

								<!-- 일반 파일 -->
								<a class="btn btn-default" style="margin-right: 10px;"
									data-toggle="modal" data-target="#fileModal"> <i
									class="fa  fa-paperclip"></i>Files
								</a>
							</div>
							<!-- /. 파일 끝-->

							<!-- 텍스트 에디터 -->
							<div class="form-group">
								<textarea id="editor1" name="editor1" rows="50" cols="70"></textarea>
							</div>
							<!-- 텍스트 에디터 끝-->

							<!-- 업로드 된 파일들 -->							
							<div class="form-group">
								<div class="box-footer">
									<ul class="mailbox-attachments clearfix">
										<li><span class="mailbox-attachment-icon"><i
												class="fa fa-file-pdf-o"></i></span>

											<div class="mailbox-attachment-info">
												<a href="#" class="mailbox-attachment-name"><i
													class="fa fa-paperclip"></i> Sep2014-report.pdf</a> <span
													class="mailbox-attachment-size"> 1,245 KB <a
													href="#" class="btn btn-default btn-xs pull-right"><i
														class="fa fa-fw fa-remove"></i></a>
												</span>
											</div></li>
										<li><span class="mailbox-attachment-icon"><i
												class="fa fa-file-word-o"></i></span>

											<div class="mailbox-attachment-info">
												<a href="#" class="mailbox-attachment-name"><i
													class="fa fa-paperclip"></i> App Description.docx</a> <span
													class="mailbox-attachment-size"> 1,245 KB <a
													href="#" class="btn btn-default btn-xs pull-right"><i
														class="fa fa-fw fa-remove"></i></a>
												</span>
											</div></li>
										<li><span class="mailbox-attachment-icon has-img"><img
												src="/resources/dist/img/photo1.png" alt="Attachment"></span>

											<div class="mailbox-attachment-info">
												<a href="#" class="mailbox-attachment-name"><i
													class="fa fa-camera"></i> photo1.png</a> <span
													class="mailbox-attachment-size"> 2.67 MB <a href="#"
													class="btn btn-default btn-xs pull-right"><i
														class="fa fa-fw fa-remove"></i></a>
												</span>
											</div></li>
										<li><span class="mailbox-attachment-icon has-img"><img
												src="/resources/dist/img/photo2.png" alt="Attachment"></span>

											<div class="mailbox-attachment-info">
												<a href="#" class="mailbox-attachment-name"><i
													class="fa fa-camera"></i> photo2.png</a> <span
													class="mailbox-attachment-size"> 1.9 MB <a href="#"
													class="btn btn-default btn-xs pull-right"><i
														class="fa fa-fw fa-remove"></i></a>
												</span>
											</div></li>
									</ul>
								</div>
							</div>

							<!-- 버튼 -->
							<div class="form-group">
								<div class="pull-left">
									<button class="btn btn-default btn-lg">Reset</button>
								</div>
								<div class="pull-right">
									<button class="btn btn-info btn-lg" id="btnRegister">Submit</button>
								</div>
							</div>
							<!-- /. 버튼 끝 -->
						</form>
						<!-- /. 폼 끝 -->
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /.col-->
		</div>
		<!-- ./row -->

		<!-- 이미지 업로드 모달 -->
		<div id="imageModal" class="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>Add</strong>&nbsp;&nbsp;Image
						</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="file" id="exampleInputFile">
						</div>
						<div class="form-group imageDrop"
							style="border: 1px dotted blue; height: 70px;">
							<div align="center" style="margin-top: 25px;">
								<strong>Or drag and drop</strong> Image :)
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">ADD</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

		<!-- 파일 업로드 모달 -->
		<div id="fileModal" class="modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">
							<strong>Add</strong>&nbsp;&nbsp;File
						</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<input type="file" id="exampleInputFile">
						</div>
						<div class="form-group fileDrop"
							style="border: 1px dotted blue; height: 50px;">
							<div align="center" style="margin-top: 25px;">
								<strong>Or drag and drop</strong> files :)
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default pull-left"
							data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">ADD</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>

<script>
  $(function () {	  
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.    
    CKEDITOR.replace('editor1');
    
    
    // dragenter, dragover 이벤트 제거
    $(".fileDrop").on("dragenter dragover", function(event) {
    	event.preventDefault();
    });
    
    // 파일 드랍 이벤트 처리
    $(".fileDrop").on("drop", function(event) {
    	event.preventDefault();
    	
    	var files = event.originalEvent.dataTransfer.files;
    	var file = files[0];
    	
    	if( file == null || file.length == 0 ) {
    		return;
    	}
    	
    	var formData = new FormData();
    	formData.append("file",file);
    	
    	$.ajax({
    		url:'/uploadFile',
    		data: formData,
    		dataType: 'text',
    		processData: false,
    		contentType: false,
    		type: 'POST',
    		success: function(data) {
    			alert(data.result);
    		}
    	});
    });
    
    
    
    
  });
</script>
</body>
</html>

