<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../include/header.jsp" %>

<c:if test="${not empty msg}">
		Reason : ${msg}		
</c:if>

<div class="container">

	<h3 class="text-primary">회 원 가 입</h3>
	
	<form class="form-horizontal" role="form" action="/user/join" method="post">
    
    <fieldset>
          <legend>기본정보 </legend>
    <div class="form-group">
        <label for="user_id">아이디</label>
        <input type="text" class="form-control col-sm-4  col-lg-4" placeholder="아이디" name="user_id" id="user_id">
        <button type="button" class="btn btn-info btn-xs" id="checkUserId">중복체크</button>        
    </div>
    
    <!-- 중복 검사 결과  -->
    <div class="form-group">    
    	<div class="alert alert-success simple-alert" style="display: none"
			id="possibleIdAlert">
			<div class="text primary">
				<strong>사용가능 </strong>한 아이디 입니다.			
		</div>
	</div>
		
	<div class="form-group">
		<div class="alert alert-warning simple-alert" style="display: none"
			id="duplicateIdAlert">
				<strong>이미 사용 중</strong> 인 아이디 입니다.
		</div>
		</div> 
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
    
	<!-- <fieldset disabled>  -->
	<fieldset>
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
    <button type="reset" class="btn btn-primary" id="btnHome">홈으로</button>
    </form>
    
</div><!-- container.끝 -->


<script>	
	
	$(document).ready(function() { 		
		//아이디 중복 체크
		$('#checkUserId').on('click',function() {
			var user_id = $('#user_id').val();
			if(user_id.length <= 0)
				return;
			
			//수정해야됨			
			$.ajax({
				url : '/user/check_id/'+user_id,
			    type : 'get',
			    headers:  {
				      "Content-Type": "application/json" },
			    dataType:'text',
			    success : function(result) {   
			    	if(result == 'POSSIBLE') { 
						$('#possibleIdAlert').css('display','block');
						$('#duplicateIdAlert').css('display','none');
					} else if(result == 'DUPLICATE') {
						$('#duplicateIdAlert').css('display','block');						
						$('#possibleIdAlert').css('display','none');						
					}
			    },
			    error : function(request,error) {
			        alert("Request: "+JSON.stringify(request));
			    }				
			});
			
			/*
			$.getJSON("/user/check_id/"+user_id,function(result){
				alert(result);
				if(result == 'POSSIBLE') { 
					$('#possibleIdAlert').css('display','block');
					$('#duplicateIdAlert').css('display','none');
				} else if(result == 'DUPLICATE') {
					$('#possibleIdAlert').css('display','none');
					$('#duplicateIdAlert').css('display','block');						
				}
			});
			*/
		});
		
		$('#btnHome').on('click',function(){
			self.location='/';			
		});	
		
	});	
</script>


<%@ include file="../include/footer.jsp" %>





