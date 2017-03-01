<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../include/header.jsp" %>

<c:if test="${not empty msg}">
		Reason : ${msg}		
</c:if>

<div class="container">

	<h3 class="text-primary">Join us..</h3>
	
	<form class="form-horizontal" role="form" action="/user/join" method="post">
    
    <fieldset>
          <legend>기본정보 </legend>
    <div class="form-group">   
        <label for="user_id">아이디</label>
        <input type="text" class="form-control col-sm-4  col-lg-4" placeholder="아이디" name="user_id" id="user_id">
        <button type="button" class="btn btn-info btn-sm" id="checkUserId">중복체크</button> 
    </div>
    
    <div class="form-group">   
        <label for="password">비밀번호</label>
        <input type="password" class="form-control" placeholder="비밀번호" name="user_password"> 
    </div>
        
    <div class="form-group">   
        <label for="name">이름</label>
        <input type="text" class="form-control" placeholder="이름" name="user_name"> 
    </div>     
    
    <div class="form-group">
        <label for="emailaddress">이메일</label>
        <input type="email" class="form-control" placeholder="이메일" name="user_email"> 
    </div>
	
	<div class="form-group">
		<label for="phone">전화번호</label>
		<input type="text" class="form-control" placeholder="전화번호" name="user_phone">
	</div>
	     
    </fieldset>
    
    <fieldset disabled> 
         <legend>부가정보 </legend>    
    <div class="form-group">   
        <label for="name">우편번호</label>
        <input type="text" class="form-control" placeholder="우편번호" name="postcode_fk" value="111-111"> 
    </div>    
         
    <div class="form-group">   
        <label for="addr">주소</label>
        <input type="text" class="form-control" placeholder="주소" name="address" value="address.."> 
    </div>
    </fieldset>          
    
    <button type="submit" class="btn btn-primary">가입</button>
    <button type="reset" class="btn btn-primary">취소</button>
    </form> 
</div>


<script>	
	
	$(document).ready(function() { 		
		//아이디 중복 체크
		$('#checkUserId').on('click',function(){
			var user_id = $('#user_id').val();
			$.getJSON('/user/check_id/'+user_id,function(data) {
					alert(data);				
				});
		});		
	});	
</script>


<%@ include file="../include/footer.jsp" %>





