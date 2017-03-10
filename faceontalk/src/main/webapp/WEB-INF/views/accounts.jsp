<!-- bootstrap source by : http://bootsnipp.com/snippets/featured/portfolio-gallery-with-filtering-category -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<style>

/*	top css	*/
/*	css source :  http://bootsnipp.com/snippets/Ba91N	*/
.profile-teaser-left {
    float: left; width: 20%; margin-right: 1%; margin-left: 10%;
}
.profile-img img {
    width: 100; height: 100;
}

.profile-teaser-main {
    float: left; width: 70;
}

.info { display: inline-block; margin-right: 10px; color: #777; }
.info span { font-weight: bold; }

/*	images css	*/
.gallery_product {
    margin-bottom: 30px;
}


</style>

 <div class="container">
         <!-- 상단 profile -->
		<div class="row">
		<div class="list-group">
            <div class="list-group-item clearfix">
                <div class="profile-teaser-left">
                    <div class="profile-img">
                    	<img class="img-circle" src="https://secure.gravatar.com/avatar/b2ce7fb8c6a55a148824baa1d0c40a98?s=100&d=retro&r=g"/>
                    	<!-- <img class="img-rounded img-responsive" src="">  -->
                    </div>
                </div>
                <div class="profile-teaser-main">
                	<!-- 유저 아이디 -->
                    <h2 class="profile-name">USER ID
                    	<!-- 팔로우 하기 버튼 --> 
                    	<button type="button" class="btn btn-primary">Follow</button>
                    	<button type="button" class="btn btn-success">Following</button>
                    </h2>
                    <div class="profile-info">                    	                    
                    	<div class="info">
                        	<h4><span class="">게시글</span>&nbsp;200개</h4>
                        </div>
                        <div class="info">	
                        	<h4><span class="">팔로워</span>&nbsp;200명</h4>
                        </div>
                        <div class="info">
                        	<h4><span class="">팔로잉</span>&nbsp;200명</h4>
                        </div>
                    </div>
                </div>
            </div><!-- item -->
        </div>
	</div>
  		<!-- .// 상단 profile 끝-->   
           
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
	
	
    
</script>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>