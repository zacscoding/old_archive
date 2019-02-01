<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="/WEB-INF/views/include/header.jsp" %>




<div class="container">
	<div class="row">
		<button class="btn btn-primary" id="modalBtn">
						changepassword</button>
	</div>	
	<input type="hidden" name="feed_no" id="feed_no" value="20">	
	<!-- feed modal -->	
	<div class="row">
        <div class='modal fade' id='myModal'>
            <div class='modal-dialog'>
                <div class='modal-content'>
                    <div class='modal-header'>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>                        
                        <h4 class='modal-title'>                        
                          <strong>2Soolove_</strong>
                        </h4>
                    </div>              
                    <!-- / modal-header -->
                    <div class='modal-body'>
                        <img class="img-responsive" src="http://placehold.it/600x350&text=MODAL" />
                        <p>
                        	Content
                        </p>
                        <hr>
                        
                    </div> <!-- / modal-body -->                    
                   <div class='modal-footer'>                   		                   		
						<div class="input-group">
							<input type="text" class="form-control" name="replyText"
								id="replyText" placeholder="comment">
							<span class="input-group-btn"> <input type="button" 
								class="btn btn-primary replyAddBtn" value="ADD"></span>
						</div>
					</div> 
				 	<!--/ modal-footer -->
					                  	
                </div> <!-- / modal-content -->                
          </div> <!--/ modal-dialog -->          
        </div> <!-- / modal -->        
	</div> <!-- .// feed modal 끝 -->
</div> <!-- / container -->



<script>

$(document).ready(function() {
	$('#modalBtn').on('click',function(event) {
		var feed_no = $('#feed_no').val();
		
		$.getJSON("/feed/"+feed_no,function(data) {
			//alert(data.feed_no);	
			//alert(data.user_id_fk);
		});		
		$('#myModal').modal();
		
	});	
    
});
   
  


</script>


<%@ include file="/WEB-INF/views/include/header.jsp" %>