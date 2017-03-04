<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<head>

<body>
	
	<div class="container">		
		<!-- 가입 폼 시작 -->
		<div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Sign Up</div>                            
                        </div>  
                        <div class="panel-body" style="margin-left:20%; margin-right:20%;" >
                            <form id="signupform" class="form-horizontal" role="form">                                                            
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <!-- email -->
                                <div class="form-group">
									<input type="text" class="form-control" name="user_email" id="user_email" placeholder="Email Address" >
                                </div>
                                
                                
                                <!-- id -->  
                                <div class="form-group">
                               		<input type="text" class="form-control" name="user_id" id="user_id" placeholder="User ID">
                                </div>
                                
                                <!-- phone -->    
                                <div class="form-group">
                                	<input type="text" class="form-control" name="phone" id="phone" placeholder="Phone">
                                </div>
                                
                                
                                <!-- password -->
                                <div class="form-group">
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                                </div>                                 
                                
                                <!-- Sign up -->
                                <div class="form-group">
                                    <!-- Button -->                                        
                                   	<button id="btnSignUp" type="button" class="btn btn-primary btn-lg btn-block">Sign Up</button>  
                                </div>
                                   		
								<div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:95%" >
                                        <!-- <a href='/user/join'>회원가입하기</a>  -->  
                                        <a id="signinlink" href='#' onclick="$('#signupbox').hide(); $('#loginbox').show(); return false;">
                                   		Sign In</a></div>                                      
                                        </div>
								</div>
                            </form>
                         </div>
                    </div>
		</div> <!-- 가입 폼 끝. -->         
    
	    
	    <!-- 로그인폼 시작 -->
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">Sign in</div>                        
                    </div>     
                    <div style="padding-top:30px" class="panel-body" >
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>                            
                        
                        
                        <form id="loginform" class="form-horizontal" role="form" action="/user/loginPost" method="post">
                        
                        	<!-- 아이디 입력 -->                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="userid" type="text" class="form-control" name="user_id" value="" placeholder="아이디">                                        
                                    </div>
                                    
                            <!-- 암호 입력 -->    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="password" type="password" class="form-control" name="password" placeholder="암호">
                                    </div>
                            	
                                <div style="margin-top:10px" class="form-group">
                                    <!-- Button -->
                                    <div class="col-sm-12 controls">
                                	<p class="text-primary pull-left">	    
	    								<a href='/'>ID 또는 암호를 잊으셨습니까?</a></p>
                                    <button type="submit" class="btn btn-primary btn-lg pull-right" id="btnSignIn">Sign in</button>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:95%" >
                                        <!-- <a href='/user/join'>회원가입하기</a>  -->  
                                        <a id="signinlink" href='#' onclick="$('#signupbox').show(); $('#loginbox').hide(); return false;">
                                        	Sign up</a>                                      
                                        </div>
                                    </div>
                                </div>    
                            </form>
                        </div>                     
                    </div>  
        </div><!-- loggin div 끝. -->
        
        <!-- 성공 메시지 -->
        <div class="alert alert-success" style="display:none" id="successAlert">
  			<div class="text primary">
  			<strong>Success to join</strong>Please check ur email authentication.
  				<h4><a href='/index'>HOME</a></h4>
  			</div>
		</div>
		
		<!-- 아이디 중복 -->
		<div class="alert alert-warning" style="display:none" id="duplicateIdAlert">
  			<strong>Duplicate id.</strong> Please write another id.
		</div>
		
		<!-- 런타임 에러 -->
		<div class="alert alert-danger" style="display:none" id="failAlert">
			<strong>Failed to join</strong>
		</div>
		
	</div><!-- container.끝 -->
        
<script>
	$(document).ready(function(){
		
		/*		회원 가입 (데이터가 적어서 JSON 처리)		*/
		$('#btnSignUp').on('click',function() {
			
			event.preventDefault();
			
			var user_email = $('#user_email').val();
			var user_id = $('#user_id').val();
			var phone = $('#phone').val();
			var password = $('#password').val();	
			
			$.ajax({
				type:'post',
				url:'/accounts/join',
				headers: { 
				      "Content-Type": "application/json",
				      "X-HTTP-Method-Override": "POST" },
				dataType:'text',
				data: JSON.stringify({user_email:user_email, user_id:user_id, phone:phone, password:password}),
				success:function(result){ //성공시
					console.log("result: " + result);
					if(result == 'SUCCESS') {						
						$('#successAlert').css('display','block');
						$('#duplicateIdAlert').css('display','none');
						$('#failAlert').css('display','none');
					} else if(result == 'DUPLICATED_ID') {
						$('#successAlert').css('display','none');
						$('#failAlert').css('display','none');
						$('#duplicateIdAlert').css('display','block');	
					}						
				},
				error:function(result) {
					alert(result);
					$('#failAlert').css('display','block');
					
					$('#successAlert').css('display','none');
					$('#duplicateIdAlert').css('display','none');					 					
				}
			});			
		});
	});

</script>
        
</body>
</html>



