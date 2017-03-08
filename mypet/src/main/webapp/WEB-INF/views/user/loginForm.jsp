<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
    <div class="container">    
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
	    								<a href='/'>ID또는암호를 잊으셨습니까?</a></p>
                                    <button type="submit" class="btn btn-primary btn-lg pull-right">로그인</button>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-md-12 control">
                                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                        <a href='/user/join'>회원가입하기</a>                                        
                                        </div>
                                    </div>
                                </div>    
                            </form>
                        </div>                     
                    </div>  
        </div>        
    </div>
    
    <%@ include file="/WEB-INF/views/include/footer.jsp" %>
    