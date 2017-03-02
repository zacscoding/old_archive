<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<html >
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
                            <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
                        </div>  
                        <div class="panel-body" >
                            <form id="signupform" class="form-horizontal" role="form">
                                                            
                                <div id="signupalert" style="display:none" class="alert alert-danger">
                                    <p>Error:</p>
                                    <span></span>
                                </div>
                                    
                                <!-- email -->
                                <div class="form-group">
                                    <div class="col-sm-4  col-lg-4">
                                        <input type="text" class="form-control" name="user_email" placeholder="Email Address">
                                    </div>
                                </div>
                                
                                
                                <!-- id -->  
                                <div class="form-group">
                                    <div class="col-sm-4  col-lg-4">
                                        <input type="text" class="form-control" name="user_id" placeholder="User ID">
                                    </div>
                                </div>
                                
                                <!-- phone -->    
                                <div class="form-group">
                                    <div class="col-sm-4  col-lg-4">
                                        <input type="text" class="form-control" name="phone" placeholder="Phone">
                                    </div>
                                </div>
                                
                                
                                <!-- password -->
                                <div class="form-group">
                                	<div class="col-sm-4  col-lg-4">
                                        <input type="password" class="form-control" name="password" placeholder="Password">
                                    </div>
                                </div>                                 
                                
                                <!-- Sign up -->
                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="button" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp; Sign Up</button>
                                        <span style="margin-left:8px;">or</span>  
                                    </div>
                                </div>
                                                                
                                <div style="border-top: 1px solid #999; padding-top:20px"  class="form-group">                                    
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-fbsignup" type="button" class="btn btn-primary">Sign Up</button>
                                    </div>                                           
                                        
                                </div>
                                
                                
                                
                            </form>
                         </div>
                    </div>
		</div> <!-- 가입 폼 끝. -->         
    </div>
	    
	    <!-- 로그인폼 시작 -->
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
                        <div class="panel-title">로그인 하십시오.</div>                        
                    </div>     
                    <div style="padding-top:30px" class="panel-body" >
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>                            
                        
                        
                        <form id="loginform" class="form-horizontal" role="form" action="/user/login" method="post">
                        
                        	<!-- 아이디 입력 -->                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="userid" type="text" class="form-control" name="userid" value="" placeholder="아이디">                                        
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
                                    <button type="submit" class="btn btn-primary btn-lg pull-right">로그인</button>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        <!-- <a href='/user/join'>회원가입하기</a>  -->  
                                        <a id="signinlink" href="#" onclick="$('#signupbox').show(); $('#loginbox').hide()">Sign up</a>                                      
                                        </div>
                                    </div>
                                </div>    
                            </form>
                        </div>                     
                    </div>  
        </div><!-- loggin div 끝. -->
        
        
</body>
</html>