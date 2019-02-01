<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    	<title>Face on talk</title>

    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>    
  </head>  	  
  <style>
  /*
  	.fileDrop {
  		width : 100%;
  		height : 250px;
  		border = 1px dotted blue;
  	}
  	*/
  	
  	/* layout.css Style */
	.fileDrop {
	  height: 10%;
	  border-width: 2px;
	  margin-bottom: 20px;
	}
	
	/* skin.css Style*/
	.fileDrop{
	  color: #ccc;
	  border-style: dashed;
	  border-color: #ccc;
	  line-height: 200px;
	  text-align: center
	}
  	
  </style>	
<head>
<body>
<div class="container">
	<div class="row">
    <br/><br/><br/>
    	
    	<!-- 링크 -->        
        <a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#contact" data-original-title>
          	UPLOAD
        </a> <!-- 기본 링크 끝. --> 
        <div class="modal fade" id="contact" tabindex="-1" role="dialog" aria-labelledby="contactLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="panel panel-primary">
                	
                	<!-- header -->
                    <div class="panel-heading">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="panel-title" id="contactLabel"><span class="glyphicon glyphicon-picture"></span>&nbsp;Register feed</h4>
                    </div>
                                        
	                    <form action="/feed/register" method="post" id="registFeedForm">
		                <input class="form-control" name="user_id" value="${login.user_id}" type="hidden"/>                    
                    	<!-- body -->
                    	<div class="modal-body" style="padding: 5px;">		
                    		<div class="row">					
			                    	<!-- user id -->
									<div class="col-lg-6 col-md-6 col-sm-6" style="padding-bottom: 10px;">	            
			                            		<label class="form-controll">${login.user_id}</label>
									</div>
		                    </div>                              
		                   	<!-- content -->
		                           <div class="row">
		                               <div class="col-lg-12 col-md-12 col-sm-12">
		                                   <textarea style="resize:vertical;" class="form-control" placeholder="Content" rows="6" name="content"></textarea>
		                               </div>
		                          </div>
		                                                        
		                    <!-- picutre preview-->
							<div class="row fileDrop">
								<!-- 
								<div class="col-lg-6 col-md-6 col-sm-6" style="padding-bottom: 10px;">
									<label class="btn btn-default btn-file">
										upload <input type="file" style="display:none">
									</label>
								</div>
								 -->
							</div>
							<div class="row">
								<div class='uploaded'>
								</div>
							</div>	
						</div>
						<!-- footer -->						                           
                        <div class="panel-footer" style="margin-bottom:-14px;">
                            <input type="submit" class="btn btn-primary" value="Regist"/>
                            <input type="reset" class="btn btn-default" value="Clear" />
                            <button style="float: right;" type="button" class="btn btn-default btn-close" data-dismiss="modal">Close</button>
                        </div>                                          
						</form>    	
                    </div>                    
                </div>
            </div> <!-- model. 끝 -->            
        </div>        
	</div> <!-- container 끝. -->
	
<script>
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault();
	});
	
	//file drop event
	$('.fileDrop').on('drop',function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;	
		var file = files[0];
		
		var formData = new FormData();
		formData.append('file',file);
		console.log(file);
		$.ajax({
			 url: '/feed/uploadPic',
			  data: formData,
			  dataType:'text',
			  processData: false,
			  contentType: false,
			  type: 'POST',
			  success: function(data){
				alert(data);
			}
		});
	});
	
	//submit event
	$(function(){
		$('#registFeedForm').on('click',function(){
			event.preventDefault();
			
		});
		
		
		
	});
</script>
	
</body>
</html>
	