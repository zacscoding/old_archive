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
    
    <style>
		    .container{
		    margin-top:20px;
		}
		.image-preview-input {
		    position: relative;
			overflow: hidden;
			margin: 0px;    
		    color: #333;
		    background-color: #fff;
		    border-color: #ccc;    
		}
		.image-preview-input input[type=file] {
			position: absolute;
			top: 0;
			right: 0;
			margin: 0;
			padding: 0;
			font-size: 20px;
			cursor: pointer;
			opacity: 0;
			filter: alpha(opacity=0);
		}
		.image-preview-input-title {
		    margin-left:2px;
		}
    
    </style>
        
  </head>
  

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
                    
                    	<!-- body -->
                    	<div class="modal-body" style="padding: 5px;">							
						<div class="row">												                    		                                        	
	                    	<form action="/feed/register" method="post" id="registFeedForm">
	                    	<input class="form-control" name="user_id" value="${login.uid}" type="hidden"/>                    
		                    	<!-- user id -->
	                    		<div class="row">									                    			                    		
		                            <div class="col-lg-6 col-md-6 col-sm-6" style="padding-bottom: 10px;">	            
		                            		<label class="form-controll">user_id</label>
									</div>
	                            </div>                               
	                            <!-- content -->
	                            <div class="row">
	                                <div class="col-lg-12 col-md-12 col-sm-12">
	                                    <textarea style="resize:vertical;" class="form-control" placeholder="Content" rows="6" name="content"></textarea>
	                                </div>
	                            </div>
	                                                        
	                            <!-- picutre preview-->
	                            <div class="row">
	                            	<!--    
									 <div class="col-lg-6 col-md-6 col-sm-6" style="padding-bottom: 10px;">
										<label class="btn btn-default btn-file">
											upload <input type="file" style="display:none">
										</label>
									</div>
									 -->								 
							    </div>						    
	                        </form>                             
						</div>
						
						<div class="row">
							<div class="input-group image-preview">
					                <input type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
					                <span class="input-group-btn">
					                    <!-- image-preview-clear button -->
					                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
					                        <span class="glyphicon glyphicon-remove"></span> Clear
					                    </button>
					                    <!-- image-preview-input -->
					                    <div class="btn btn-default image-preview-input">
					                        <span class="glyphicon glyphicon-folder-open"></span>
					                        <span class="image-preview-input-title">Browse</span>
					                        <input type="file" accept="image/png, image/jpeg, image/gif" name="input-file-preview"/> <!-- rename it -->
					                    </div>
					                </span>
					            </div><!-- /input-group image-preview [TO HERE]-->
					            							
						</div>						
						</div>
						<!-- footer -->						                           
                        <div class="panel-footer" style="margin-bottom:-14px;">
                            <input type="submit" class="btn btn-primary" value="Regist"/>
                            <input type="reset" class="btn btn-default" value="Clear" />
                            <button style="float: right;" type="button" class="btn btn-default btn-close" data-dismiss="modal">Close</button>
                        </div>                                          
                    </div>                    
                </div>
            </div> <!-- model. 끝 -->            
        </div>       
	
    <div class="row">    
        <div class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">  
            <!-- image-preview-filename input [CUT FROM HERE]-->
            
        </div>
    </div>
</div> <!-- container 끝. -->

<script>
$(document).on('click', '#close-preview', function(){ 
    $('.image-preview').popover('hide');
    // Hover befor close the preview
    $('.image-preview').hover(
        function () {
           $('.image-preview').popover('show');
        }, 
         function () {
           $('.image-preview').popover('hide');
        }
    );    
});

$(function() {
    // Create the close button
    var closebtn = $('<button/>', {
        type:"button",
        text: 'x',
        id: 'close-preview',
        style: 'font-size: initial;',
    });
    closebtn.attr("class","close pull-right");
    // Set the popover default content
    $('.image-preview').popover({
        trigger:'manual',
        html:true,
        title: "<strong>Preview</strong>"+$(closebtn)[0].outerHTML,
        content: "There's no image",
        placement:'bottom'
    });
    // Clear event
    $('.image-preview-clear').click(function(){
        $('.image-preview').attr("data-content","").popover('hide');
        $('.image-preview-filename').val("");
        $('.image-preview-clear').hide();
        $('.image-preview-input input:file').val("");
        $(".image-preview-input-title").text("Browse"); 
    }); 
    // Create the preview image
    $(".image-preview-input input:file").change(function (){     
        var img = $('<img/>', {
            id: 'dynamic',
            width:250,
            height:200
        });      
        var file = this.files[0];
        var reader = new FileReader();
        // Set preview image into the popover data-content
        reader.onload = function (e) {
            $(".image-preview-input-title").text("Change");
            $(".image-preview-clear").show();
            $(".image-preview-filename").val(file.name);            
            img.attr('src', e.target.result);
            $(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
        }        
        reader.readAsDataURL(file);
    });  
    
    
    
});

</script>

</body>
</html>
