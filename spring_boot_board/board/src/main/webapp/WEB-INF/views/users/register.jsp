<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">	
	<section class="content">    
	<div class="register-box">
		<!-- 등록 로고 -->
	  <div class="register-logo">	  	
	    <p><b>Welcome</b>&nbsp;&nbsp;<i class="fa fa-mobile"></i></p>
	  </div> <!-- /. 등록 로고 -->
	
	 <!-- 등록 폼 -->
	  <div class="register-box-body">
	    <p class="login-box-msg">Register a new membership</p>
	    <form role="registForm" action="/users/register" method="POST" class="registForm">
	      <!-- 아이디 -->
	      <div class="form-group has-feedback">
	        <input type="text" name="userId" class="form-control" placeholder="User ID" value="${memberVO.userId}">
	        <span class="glyphicon glyphicon-user form-control-feedback">
	        </span>
	      </div>
	      <!-- 이메일 -->
	      <div class="form-group has-feedback">
	        <input type="email" name="email" class="form-control" placeholder="Email" value="${memberVO.email}">
	        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
	      </div>
	      <!-- 암호 -->
	      <div class="form-group has-feedback">
	        <input type="password" name="password" class="form-control" placeholder="Password" id="password">
	        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
	      </div>	      
	      <!-- 암호 -->
	      <div class="form-group has-feedback">
	      	<div id="confirmDiv">
	        	<input type="password" id="confirmPwd" class="form-control" placeholder="Retype password">
	        </div>
	        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>	        
	      </div>
	      
	      <div class="row">
	        <div class="col-xs-8">
	          <div class="checkbox icheck">
	            <label>&nbsp;&nbsp;
	              <input type="checkbox">I agree to the <a href="#">terms</a>
	            </label>
	          </div>
	        </div>	        
	        <!-- /.col -->
	        <div class="col-xs-4">
	          <button type="button" class="btn btn-primary btn-block btn-flat" id="btnRegister">Register</button>
	        </div>
	        <!-- /.col -->
	      </div>	      
	    </form>
	    
	    <div class="social-auth-links text-center">
	      <p>- OR -</p>
	      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign up using
	        Facebook</a>
	      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign up using
	        Google+</a>
	    </div>
			
		
		<c:if test="${not empty result}">			    	
		    <div class="social-auth-links text-center">
		    	<c:if test="${result == 'fail'}">
			    <!-- 실패 메시지 -->            
		            <div class="alert alert-warning alert-dismissible registMessage">
		            	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>            	
		                ${message}
		            </div>
		            </c:if>
		            
		            <c:if test="${result == 'success'}">
		            <!-- 성공 메시지 -->
		            <div class="alert alert-success alert-dismissible registMessage"">
		            	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		                Success to Join :) &nbsp;&nbsp;&nbsp;<a href="/login">Sign in</a>		                
		            </div>
				</c:if>            
		    </div>
		</c:if>			    		
	    <a href="/login" class="text-center">I already have a membership</a>
	  </div>
	  <!-- /.form-box -->
	</div>
	<!-- /.register-box -->
</section>
</div><!-- /. content-wrapper -->
		
	<!-- iCheck -->
	<script src="/resources/plugins/iCheck/icheck.min.js"></script>
	<script>	
		$(function() {
			
			var formObj = $("form[role='registForm']");
			
			function matchedPassword() {
				var password = $('#password').val();
				var confirm = $('#confirmPwd').val();
				return password === confirm;
			}
			
			// 확인 비밀 번호 
			$('#confirmPwd').keyup(function(event) {				
				if( !matchedPassword() ) {					
					$('#confirmDiv').addClass('has-error');
				} else {
					$('#confirmDiv').removeClass('has-error');
				}				
			});						
			
			//등록 
			$('#btnRegister').on('click', function(event) {
				event.preventDefault();
				if( !matchedPassword() ) {
					return;
				}
				formObj.submit();								
			});
		});	
	 
	</script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
