<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人中心</title>
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
<link rel="stylesheet" href="assets/css/bootstrap.min.css"
	type="text/css">
<!-- Dropdownhover CSS -->
<link rel="stylesheet" href="assets/css/bootstrap-dropdownhover.min.css"
	type="text/css">
<!-- latest fonts awesome -->
<link rel="stylesheet" href="assets/font/css/font-awesome.min.css"
	type="text/css">
<!-- simple line fonts awesome -->
<link rel="stylesheet"
	href="assets/simple-line-icon/css/simple-line-icons.css"
	type="text/css">
<!-- stroke-gap-icons -->
<link rel="stylesheet"
	href="assets/stroke-gap-icons/stroke-gap-icons.css" type="text/css">
<!-- Animate CSS -->
<link rel="stylesheet" href="assets/css/animate.min.css" type="text/css">
<!-- Style CSS -->
<link rel="stylesheet" href="assets/css/style.css" type="text/css">
<!-- Style CSS -->
<link rel="stylesheet" href="assets/css/slider.css" type="text/css">
<!--  baguetteBox -->
<link rel="stylesheet" href="assets/css/baguetteBox.css">
<!-- Owl Carousel Assets -->
<link href="assets/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="assets/owl-carousel/owl.theme.css" rel="stylesheet">
<link href="css/userinfo.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.1.0.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="assets/js/jquery.js"></script>
</head>
<body>
	<!--  Preloader  -->
	<div id="preloader">
		<div id="loading"></div>
	</div>
	<div class="top-header">
		<div class="container">
			<div class="col-md-6">
				<div class="top-header-left">
					<ul>
						<c:choose>
							<c:when test="${sessionScope.user.name!=null }">
								<!-- 使用隐藏域，传递一个键值对到后台，判断是注册、登陆登操作 -->
								<input type="hidden" value="logout" name="option" />
								<li>
									<div class="dropdown">
										<a href="#" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" data-hover="dropdown">
											${sessionScope.user.name }欢迎您 <i class="fa fa-angle-down"
											aria-hidden="true"></i>
										</a>
										<ul class="dropdown-menu">
											<li><a href="userinfo.action">个人中心</a></li>
											<li><a href="user_logout.action">退出</a></li>
										</ul>
									</div>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<div class="dropdown">
										<a href="#" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" data-hover="dropdown"> VShop商城欢迎您！
											<i class="fa fa-angle-down" aria-hidden="true"></i>
										</a>
										<ul class="dropdown-menu">
											<li><a href="index.jsp">登录</a></li>
											<li><a href="register.jsp">注册</a></li>
										</ul>
									</div>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<div class="col-md-6">
				<div class="top-header-right">
					<ul>
						<li><i aria-hidden="true"></i> <a href="main.jsp"
							style="color: white;">首页</a></li>
						<li><i class="icon-settings icons" aria-hidden="true"></i>
							服务中心</li>
						<li><i class="icon-note icons" aria-hidden="true"></i> 意见反馈</li>
						<li>
							<div class="dropdown">
								<a href="#" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" data-hover="dropdown"> <i
									class="icon-location-pin icons" aria-hidden="true"></i> 网站导航
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">DNF 官网</a></li>
									<li><a href="#">LOL 官网</a></li>
									<li><a href="#">王者荣耀</a></li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--  /top-header  -->
	</div>
	<section class="top-md-menu">
	<div class="container">
		<div class="main-menu">
			<!--  nav  -->
			<nav class="navbar navbar-inverse navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1" data-hover="dropdown"
				data-animations=" fadeInLeft fadeInUp fadeInRight"
				style="margin-top: -13px;">
				<ul class="nav navbar-nav">
					<li class="all-departments dropdown"><a href="userinfo.action"
						class="dropdown-toggle"><font size="5px">我的VShop</font></a>
						<ul class="dropdown-menu dropdownhover-bottom all-open"
							role="menu">
							<li class="dropdown" style="padding-left: 15px;"><strong><font
									size="3px">我是买家</font></strong></li>
							<li class="dropdown" style="padding-left: 1px;"><a
								href="prolist.action">我要买</a></li>
							<li><a href="myorder.action">我购买的商品</a></li>
							<li><a href="#">中奖记录</a></li>
							<li><a href="#">我领取的福利</a></li>
							<li><a href="#">代练招募</a></li>
							<li class="divider"></li>
							<li class="dropdown" style="padding-left: 15px;"><strong><font
									size="3px">我是卖家</font></strong></li>
							<li><a href="sellproduct.jsp"> 我要卖</a></li>
							<li><a href="mysell.action"> 我出售的商品</a></li>
							<li><a href="#"> 我的置顶卡</a></li>
							<li><a href="#"> 商品批量发布</a></li>
							<li><a href="#"> 代练发布</a></li>
							<li class="divider"></li>
							<li class="dropdown" style="padding-left: 15px;"><strong><font
									size="3px">客服中心</font></strong></li>
							<li><a href="#">帮助中心</a></li>
							<li><a href="#">我的投诉</a></li>
							<li><a href="#">我要充钱</a></li>
						</ul></li>
					<li style="padding-top: 25px; padding-left: 15px;"><font
						size="5px">个人信息</font></li>
				</ul>
				<!-- /.navbar-collapse -->
			</div>
			</nav>
			<!-- /nav end -->
		</div>
		<div class="row">
			<div class="col-md-12" style="margin-top: 10px"></div>
			<div class="col-sm-3 col-md-3"></div>
			<div class="col-sm-9 col-md-9">
				<div class="userinfo">
					<img src="images/personal.png">
					<p class="nameinfo">
						<strong>${sessionScope.user.name }</strong>，欢迎您！用户状态：【我在线上】
					</p>
					<p class="levelinfo">
						我的信誉：
						<c:choose>
							<c:when test="${requestScope.level ==1}">
								<img alt="2" src="assets/images/level-icon1.png">
							</c:when>
							<c:when test="${requestScope.level ==2}">
								<img alt="2" src="assets/images/level-icon2.png">
							</c:when>
							<c:when test="${requestScope.level ==3}">
								<img alt="2" src="assets/images/level-icon3.png">
							</c:when>
							<c:when test="${requestScope.level ==4}">
								<img alt="2" src="assets/images/level-icon4.png">
							</c:when>
							<c:when test="${requestScope.level ==5}">
								<img alt="2" src="assets/images/level-icon5.png">
							</c:when>
							<c:otherwise>
								新用户
							</c:otherwise>
						</c:choose>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						我的订单：确认收货（<a href="status_option.action?status=0">${requestScope.osum}</a>）;
					</p>
					<p class="infoimg">
						<img src="images/info.png">
					</p>
					<p class="balance">
						账号余额：<strong><font color="red">${requestScope.balance }</font></strong>元
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a data-toggle="modal"
							data-target="#myModal" href="#" style="text-decoration: none;"><font color="blue">充值</font></a>
					</p>
				</div>
				<div style="margin-left: -9px;">
					<img src="images/idFalse.png" alt="Grid-banner" />
				</div>
			</div>
		</div>
	</div>
	<!-- 充值弹窗开始 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 300px;">
			<div class="modal-content" style="background: url('images/timg.jpg') no-repeat;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong><font color="yellow">为梦想充值</font></strong>
					</h3>
				</div>
				<div class="modal-body">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong><font color="yellow">金额：</font></strong>
					<input type="text" id="sum" name="sum"
						style="margin-top: 50px; margin-bottom: 27px;">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick="ensure();">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 充值弹窗结束 -->
	<script src="assets/js/bootstrap.min.js"></script> <script
		src="assets/js/bootstrap-dropdownhover.min.js"></script> <!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script> <script
		src="assets/js/wow.min.js"></script> <!-- owl carousel --> <script
		src="assets/owl-carousel/owl.carousel.js"></script> <!--  Custom Theme JavaScript  -->
	<script src="assets/js/custom.js"></script>
</body>
</html>
