<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <title>Welcome to MyPET</title>

    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/nomalize.css" rel="stylesheet" type="text/css">
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    
    <style>
     @import url(http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css);
     body {  font-family: "Malgun gothic"                                      ,  sans-serif; padding-top: 70px;}
     .control { position: inherit; top: 50%; z-index: 5; display: inline-block; right: 50%;} 
     header { padding: 10px 0}
     header h1 { font-size: 30px;}
     dt { float: left;  overflow: hidden;  clear: left;  width: 50px}
     .container { padding:0 5px;}
     .container-fluid { padding-left: 0; padding-right: 0}
     .logo { background: url(/resources/bootstrap/imgs/comlogo.png) no-repeat; width:250px; height: 85px; }
     .pack { display: block; color: #fff; background-color: #0000A0; font-size: 12px}
     .disc { display: block; color: #fff; background-color: #FF0000; font-size: 12px}
    .carousel-caption { top: 2%; left: 5%; text-align: left;}
     .red { color: #FF0000}
     .black { color: #000}
     ul.block { height: 60px;}
     /* 상품 썸 네일 부분 */
     .thumbnail { border-radius: 0; position: relative; z-index: 1; border: 2px solid #fff; outline: 1px solid #ccc}     
    .thumbnail:hover { border: 2px solid red; outline: 0; }
    .thumbnail a:hover { text-decoration: none}
    .thumbnail:hover img{   position: relative; top: -4px; }
     /*네브바 부분*/
    .nav-top{ font-size: 12px;}
    .nav-top >li > a { padding:0 10px;  margin:7px 0; color: #000; background: url(/resources/bootstrap/imgs/vline.jpg) no-repeat left center;  }
    .nav-top >li:first-child > a { background: none}
    .nav-main > li > a{ font-size: 16px; font-weight: bold; }
    .navbar-toggle { border: 1px solid #ccc;}
    .navbar-toggle .icon-bar {  border: 1px solid #ccc;}
    .navbar-collapse { background-color: #fff}
    .navbar-fix { background-color: #fff}  
    .navbar-form input, .navbar-form button { padding: 0 5px; border: none; margin:0; }
    .navbar-form { border: 1px solid #ccc; padding: 0; }
    @media (max-width:992px) {
      .nav-top > li > a { padding: 0 6px}
      .nav-main > li > a  { padding: 0 14px; }
      .navbar-form { display: none;  }
    }
    .simple-alert{
    	width : 40%;
    }
    </style>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    
<body>
<div class="container-fluid">
<header>
      <!-- nav bar 부분 -->
          <nav class="navbar navbar-fix navbar-fixed-top" role="navigation" id="navbar-scroll">
            <div class="container">                
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              
            <!-- 로고 삽입 -->
            <a class="navbar-brand" href="/"><img src="/resources/bootstrap/imgs/logo.png" alt="로고"> </a>              
              
            </div>
            <div class="collapse navbar-collapse navbar-right">             
              <ul class="nav navbar-nav nav-top">
              	
              	<!-- 1단 메뉴 -->
              	<sec:authorize access="!isAuthenticated()">
              		<li><a href="/user/loginform">로그인</a></li>
              		<li><a href="<c:url value='/user/join'/>">회원가입</a></li>
              	</sec:authorize>
              	
              	
              	
              	
              	<sec:authorize access="isAuthenticated()">
              		<li><a href="/user/logout">로그아웃</a></li>
              		<li><a href="">마이페이지</a></li>              		
              	</sec:authorize>
              	
              	<sec:authorize access="hasRole('ROLE_ADMIN')">
              		<li><a href="/admin/main">관리자페이지</a></li>
              	</sec:authorize>
              	          	
              	
              	<li><a href="">이벤트</a></li>
              	<li><a href="">공지사항</a></li>
              	<li><a href="">회사소개</a></li>
				
                
              </ul> 
            </div>          
            <div class="collapse navbar-collapse navbar-right navbar-collapse1">             
              <ul class="nav navbar-nav nav-main">
              	
              	<!-- 2단 카테고리 -->
                <li><a href="#"> <i class="fa fa-shopping-cart"></i>  스토어 </a></li>  
                <li><a href="#">제품리뷰 </a></li> 
                <li><a href="#">고객지원 </a></li>                  
               	<li><a href="#">이벤트</a></li>
               	                                    
              </ul> 
              <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                  <input type="text" class="form-control" placeholder="검색">
                </div>
                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
              </form>   
            </div><!-- /.navbar-collapse -->
            </div>
          </nav>   
    <!-- // nav bar 부분 끝 -->   
</header>
</div>

