<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ include file="../include/header.jsp" %>


<div class="container">
	
	<div class="page-header">
      <h1> 로그인 하십시오.</h1>      
    </div>
    
    <div align="center">
    
    <form class="form-horizontal" role="form" action="/user/login" method="post">
    	<h3 class="text muted"><strong>MyPET 로그인</strong></h3> 
    	
    	<div class="form-group">   
    	    <label for="user_id">아이디</label>
	        <input type="text" class="form-control col-sm-4  col-lg-4" placeholder="아이디" name="user_id" id="user_id">	
    	</div>
    	
    	<div class="form-group">   
        	<label for="password">비밀번호</label>
        	<input type="password" class="form-control" placeholder="비밀번호" name="user_password"> 
    	</div>
    	
    	<p class="text-primary pull-left">	    
	    	<a href='/'>
	    		ID또는암호를 잊으셨습니까?
	    	</a>
    	</p>
    	
    	<button type="submit" class="btn btn-primary">로그인</button>    
   	</form>    	    
    </div>
</div>

<%@ include file="../include/footer.jsp" %>