<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Regist Article 
        <small>from zac`s page</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="/"><i class="fa fa-dashboard"></i>Articles</a></li>
        <li><a href="#">Category</a></li>
        <li class="active">Editors</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-md-12">
          <div class="box box-info">
            <div class="box-header">
              <h3 class="box-title">CK Editor
                <small>Advanced and full of features</small>
              </h3>
              <!-- tools box -->
              <div class="pull-right box-tools">
                <button type="button" class="btn btn-info btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse">
                  <i class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-info btn-sm" data-widget="remove" data-toggle="tooltip" title="Remove">
                  <i class="fa fa-times"></i></button>
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
                </div> <!-- /. 카테고리 끝 -->
                
                <!-- 제목 -->
                <div class="form-group">
                	<input type="text" class="form-control" placeholder="Title">                                
                </div> <!-- /. 제목 끝 -->
                
                <!-- 태그 -->
                <div class="form-group">
                	<input type="text" class="form-control" placeholder="Tags">                                
                </div> <!-- /. 태그 끝 -->
                
                <!-- 파일 -->
                <div class="form-group">
                	<input type="file" id="exampleInputFile">
                </div><!-- /. 파일 끝-->
                
                <!-- 텍스트 에디터 -->
                <div class="form-group">
                	<textarea id="editor1" name="editor1" rows="50" cols="70"></textarea>
                </div> <!-- 텍스트 에디터 끝-->
                
                <input type="text" id="editor2" name="editor2" style="display:none;">
                
                <!-- 버튼 -->                
                <div class="form-group">
                	<div class="pull-left">
                		<button class="btn btn-default btn-lg">Reset</button>
                	</div>                	
                	<div class="pull-right">
                		<button class="btn btn-info btn-lg" id="btnRegister">Submit</button>
                	</div>                	
                </div> <!-- /. 버튼 끝 -->                
			</form> <!-- /. 폼 끝 -->              
            </div>
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col-->
      </div>
      <!-- ./row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <div class="control-sidebar-bg"></div>
<!-- ./wrapper -->

<!-- CK Editor -->
<script src="https://cdn.ckeditor.com/4.5.7/standard/ckeditor.js"></script>

<script>
  $(function () {
    // Replace the <textarea id="editor1"> with a CKEditor
    // instance, using default configuration.    
    CKEDITOR.replace('editor1');
    
    
    
    
  });
</script>
</body>
</html>

