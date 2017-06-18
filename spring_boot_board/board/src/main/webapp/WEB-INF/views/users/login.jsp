<!-- 로그인 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">	
	<section class="content">
	<div class="login-box">
  <div class="login-logo">
    <a href="/"><b>Zaccoding</b>`s :)</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>

    <form role="loginForm" action="/loginPOST" method="POST">
    	<!-- id -->
      <div class="form-group has-feedback">      
        <input type="text" name="id" class="form-control" placeholder="id">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      	<!-- password -->
      <div class="form-group has-feedback">
        <input type="password" name="password" class="form-control" placeholder="Password">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="useCookie">Remember Me 
            </label>
          </div>          
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
        	<button type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>          
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
      <p>- OR -</p>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
        Facebook</a>
      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
        Google+</a>
    </div>
    <!-- /.social-auth-links -->

    <a href="/">I forgot my password</a><br>
    <a href="/users/register" class="text-center">Register a new membership</a>
  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<script>
  $(function () {
	  
  });
</script> 	
	</section>	
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
