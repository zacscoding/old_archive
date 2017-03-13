<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    	<title>Face on talk</title>
	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
	
    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    
    <!-- Header css -->
    <link href="/resources/bootstrap/css/header.css" rel="stylesheet" type="text/css">
        
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    
    <!-- Include Handlerbars -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>        
  </head>
<head>
	
<body>
<div class="container">	
	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#" onclick="return false;">FACE ON TALK</a>
    </div>
	
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">    
      <ul class="nav navbar-nav">   
		    	<!-- 홈버튼  <li class="active"> -->
		      <li class="topMenu"><a href="/feed/list"><i class="glyphicon glyphicon-home"></i></a></li>        
      </ul>
            
	<!-- 검색 버튼 -->	              
      <ul class="nav navbar-nav navbar-right">
      	<li>
	      <form class="navbar-form">
		       <div class="input-group" style="margin-left:20px; margin-top:5px;">	             
		        <input type="text" class="form-control" placeholder="Search" id="searchText">
		         <span class="input-group-btn"> <button class="btn btn-primary" type="submit" id="searchBtn"><i class="glyphicon glyphicon-search"></i>
		         	</button> </span>  
		    </div>
	      </form> </li>	           
		<li class="topMenu"><a href="/feed/register"> <i
			class="glyphicon glyphicon-camera"></i></a></li>

		<!-- 히스토리 -->
		<li class="topMenu"><a href="#"> <i
			class="glyphicon glyphicon-heart"></i></a></li>

		<!-- 마이페이지 -->
		<li class="topMenu"><a href="/accounts/mypage"> <i 
			class="glyphicon glyphicon-user"></i></a></li>

		</ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
  </nav>
</div>
<br/><br/><br/><br/><br/><br/>

<script>	
$('#searchBtn').on('click',function(event) {
	event.preventDefault();	
	var keyword = $('#searchText').val();
	if(keyword.length==0)
		keyword='# ';
	if(keyword.charAt(0) == '#') {
		keyword = keyword.substr(1);				
		self.location = '/feed/searchList?keyword='+keyword;				
	} else {
		self.location = '/accounts/detail?user_id='+keyword;
	}			
});

</script>
