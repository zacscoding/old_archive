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

    <!-- Bootstrap -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
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
  </head>
<head>

	<style>
		.topMenu{
			/*	위,오른쪽,아래,왼쪽 padding:5px;*/
			/*	padding: 5px 5px 5px 5px;	*/
			padding : 5px 60px;
		}
	</style>

<body>
<div class="container">
	<!-- 어두운색, top fix  -->
	
	<nav class="navbar navbar-inverse navbar-fixed-top">  
	<!--  
	<nav class="navbar navbar-default navbar-fixed-top">
	-->
	  <div class="container-fluid ">
	    <div class="navbar-header">	    
	    	<!-- 마크업 API로 메뉴 동작과 연동 -->
	    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
	          <span class="sr-only">Toggle navigation</span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	          <span class="icon-bar"></span>
	        </button>
	        
	      <a class="navbar-brand" href='/admin/main'> ADMIN PAGE </a>
	    </div>
	    	    
	    <div class="collapse navbar-collapse navbar-ex1-collapse">
		    <!--<ul class="nav  nav-justified nav-back collapse navbar-collapse navbar-ex1-collapse">-->
		     <ul class="nav navbar-nav">   
		    	<!-- 제품,카테고리 관리 -->	
		      <li class="topMenu"><a href="/admin/categories/main">
		      	제품 관리(+카테고리)</a></li>
		      	
		      	<!-- 회원 관리 -->		      
		      <li class="topMenu"><a href='/admin/users/list'>
		      	회원 관리</a></li>
		      	
		      	<!-- 주문관리 -->
		      <li class="topMenu"><a href="#">
		      	주문관리</a></li>
		      	
		      	<!-- 게시글 관리 -->
		      <li class="topMenu"><a href="#">
		      	</a></li>
		      		
			  <li class="topMenu"><a href="/">
		      	SHOP PAGE</a></li>		      	
		      		      	
		    </ul>
	    </div>
	  </div>
	</nav>
</div> <!-- container 끝 -->