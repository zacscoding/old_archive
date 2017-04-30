<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Zaccoding`s Multi Articles :D </title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/resources/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="/resources/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<!-- ADD THE CLASS sidedar-collapse TO HIDE THE SIDEBAR PRIOR TO LOADING THE SITE -->
<body class="hold-transition skin-blue sidebar-collapse sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
  <header class="main-header">
    <!-- Logo -->
    <a href="/" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>Z</b>AC</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Zac</b>coding</span>
    </a>
    
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
		
		<!-- 로그인 메뉴 -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img src="/resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="/resources/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="../../dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">Followers</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>

  <!-- =============================================== -->
  	
	<!-- 왼쪽 사이드 바 -->
  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
        	<a href="/">
          		<img src="/resources/imgs/logo.png" class="thumbnail" alt="User Image" style="width:100%;">
          	</a>
        </div>        
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
           
		<!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">Welcome :D </li>
        
        <!-- 로그인 -->
        <li class="treeview">
        	<a href="#">
            	<i class="fa fa-users"></i> <span>Accounts</span>
            	<span class="pull-right-container">
              	<i class="fa fa-angle-left pull-right"></i>
            	</span>
          	</a>
          	<ul class="treeview-menu">
            	<li><a href="../../index.html"><i class="fa fa-circle-o"></i>Log in</a></li>
            	<li><a href="/users/register"><i class="fa fa-circle-o"></i>Join Us</a></li>
          	</ul>        
        </li>
        
        <li class="treeview">
          <a href="#">
            <i class="fa fa-edit"></i> <span>Community</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="../../index.html"><i class="fa fa-circle-o"></i>All</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>공지사항</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>사는애기</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>포럼</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>정기모임/스터디</a></li>
            <li><a href="../../index2.html"><i class="fa fa-circle-o"></i>학원/홍보</a></li>            
          </ul>
        </li>        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside> <!-- /. 왼쪽 사이드 바 끝 -->
  
  <!-- =============================================== -->
    	
  <!-- Content Wrapper. Contains page content -->
  <!-- 
  <div class="content-wrapper">
    <section class="content-header">
	-->
    
  <!-- /.content-wrapper -->
	<!-- jQuery 2.2.3 -->
	<script src="/resources/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script src="/resources/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="/resources/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="/resources/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="/resources/dist/js/demo.js"></script>  
	
	

  