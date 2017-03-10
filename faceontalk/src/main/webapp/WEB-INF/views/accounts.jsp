<!-- bootstrap source by : http://bootsnipp.com/snippets/featured/portfolio-gallery-with-filtering-category -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<style>

.gallery-title
{
    font-size: 36px;
    color: black;
    text-align: center;
    font-weight: 500;
    margin-bottom: 70px;
}
.gallery_product
{
    margin-bottom: 30px;
}
</style>

 <div class="container">		
 		<!-- title -->
 		<!-- 
        <div class="row">
        <div class="gallery col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <h1 class="gallery-title">Gallery</h1>
        </div>        
        <br/>
         --> 
         
         <!-- 상단 profile -->
  <div class="row">
    <div class="span12">
      <div class="well well-small clearfix">
        <div class="row-fluid">
          <div class="span2">
             <img src="http://www.gravatar.com/avatar/f6112e781842d6a2b4636b35451401ff?s=125" class="img-circle"/>         
             <h2>Jonnie Spratley</h2> 
            <ul class="unstyled">
              <li><i class="icon-phone"></i> 916-241-3613</li>
              <li><i class="icon-envelope"></i> jonniespratley@me.com</li>
              <li><i class="icon-globe"></i> http://jonniespratley.me</li>
            </ul>            
          </div>
          <div class="span6">
          <ul class="inline stats">
               <li>
                 <span>275</span>
                 Friends
              </li>
              <li>
                 <span>354</span>
                 Followers
              </li>
               <li>
                 <span>186</span>
                 Photos
              </li>
          </ul>
            <div><!--/span6-->
            </div><!--/row-->
      </div>
      <!--Body content--> 
      </div></div> 
      </div>  
         </div><!-- .// 상단 profile 끝-->
         
           
         <div class="row">
         	<!-- image list  -->             
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            
            
            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="http://webapplayers.com/inspinia_admin-v2.5/img/gallery/11.jpg" class="img-responsive">
            </div>
            

        </div>
    </div>

<script>
$(document).ready(function(){

    $(".filter-button").click(function(){
        var value = $(this).attr('data-filter');
        
        if(value == "all")
        {
            //$('.filter').removeClass('hidden');
            $('.filter').show('1000');
        }
        else
        {
//            $('.filter[filter-item="'+value+'"]').removeClass('hidden');
//            $(".filter").not('.filter[filter-item="'+value+'"]').addClass('hidden');
            $(".filter").not('.'+value).hide('3000');
            $('.filter').filter('.'+value).show('3000');
            
        }
    });

});
</script>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>