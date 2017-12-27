<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的商品</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
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
<link href="css/orderconfirm.css" rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/accountinfo.js"></script>
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
						<li><i class="icon-settings icons" aria-hidden="true"></i> <a
							href="#" style="color: white; text-decoration: none;">服务中心</a></li>
						<li><i class="icon-note icons" aria-hidden="true"></i> <a
							href="#" style="color: white; text-decoration: none;">意见反馈</a></li>
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
						size="5px">上架商品</font></li>
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
				<div class="col-md-12 grid-banner">
					<img src="assets/images/Grid-banner2.jpg" alt="Grid-banner" />
				</div>
				<div id="address" class="address" style="margin-top: 20px;">
					<form name="addrForm" id="addrForm" action="proSell.action"
						method="post">
						<div class="grid-spr">
							<div class="col-sm-6 col-md-6" style="width: 100%;">
								<div class="col-sm-6 col-md-6 text-right"
									style="margin-left: -60px; margin-top: -18px; width: 25%;">
									<strong style="font-size: 15px;">请选择游戏：</strong>
								</div>
								<div class="select-option"
									style="width: 25%; margin-right: 15px; margin-top: -17px;">
									<select id="game" name="gamename" onchange="changeRe()">
										<option>------------请选择------------</option>
										<option>地下城与勇士</option>
										<option>英雄联盟</option>
										<option>征途2</option>
										<option>王者农药</option>
										<option>冒险岛2</option>
										<option>蜀门</option>
										<option>阴阳师</option>
										<option>崩坏3</option>
										<option>剑侠情缘</option>
										<option>穿越火线：枪战王者</option>
									</select>
								</div>

								<div class="select-option"
									style="width: 25%; margin-top: -17px; margin-left: -15px;">
									<select id="region" name="gameregion" onchange="changeSe()"
										style="display: none">
									</select>
								</div>

								<div class="select-option"
									style="width: 25%; margin-top: -17px; margin-left: -36px;">
									<select id="server" name="gameserver" style="display: none">
									</select>
								</div>
							</div>
							<div class="col-sm-6 col-md-6"
								style="width: 100%; margin-top: 30px;">
								<div class="col-sm-6 col-md-6 text-right"
									style="margin-left: -60px; margin-top: -18px; width: 25%;">
									<strong style="font-size: 15px;">请选择道具：</strong>
								</div>
								<div class="select-option"
									style="width: 20%; margin-right: 15px; margin-top: -17px;">
									<select id="proname" name="proname" onchange="changePro()">
										<option>-------请选择-------</option>
									</select>
								</div>
								<div class="select-option"
									style="width: 20%; margin-top: -17px; margin-left: 10px;">
									<strong style="font-size: 15px;">数量：</strong> <input
										type="text" id="count" name="count"
										style="width: 50px; height: 20px;" /> <strong
										style="font-size: 12px; color: gray;">件</strong>
								</div>
								<div class="select-option"
									style="width: 20%; margin-top: -17px; margin-left: -30px;">
									<strong style="font-size: 15px;">单价：</strong> <input
										type="text" id="price" name="price"
										style="width: 40px; height: 20px;" /> <strong
										style="font-size: 12px; color: gray;">元</strong>
								</div>
								<div class="select-option" id="golden"
									style="display: none; width: 20%; margin-top: -17px; margin-left: -30px;">
									<strong style="font-size: 15px;">金额：</strong> <input
										type="text" id="goldensum" name="goldensum"
										style="width: 60px; height: 20px;" /> <strong
										style="font-size: 12px; color: gray;">W金币</strong>
								</div>
							</div>
							<div class="col-sm-6 col-md-6"
								style="width: 100%; margin-top: 30px;">
								<div class="col-sm-6 col-md-6 text-right"
									style="margin-left: 10px; margin-top: -18px; width: 25%;">
									<strong style="font-size: 15px;">商品描述（200字内）：</strong>
								</div>
								<textarea rows="3" cols="80" id="describe" name="describe"
									style="margin-left: 145px;"></textarea>
							</div>
						</div>
						<!-- 账号信息 -->
						<section class="all-product"> <!-- all-product --> <!-- 复制 -->

						<h4 style="margin-left: 50px; margin-bottom: 10px;">
							<font color="red">请填写账号资料：</font><span class="manage-address"></span>
						</h4>
						<ul id="address-list" class="address-list">
							<li class="J_Addr J_MakePoint clearfix  J_DefaultAddr ">
								<div class="address-info" style="margin-left: 15px;">
									游戏账号：<font color="red">*</font><input type="text" id="qq"
										name="qq" style="height: 25px; margin-left: 4px;"
										onfocus="FocusItem(this)" onblur="CheckItem(this);"> <span
										style="color: red"></span>
								</div>
							</li>
							<li class="J_Addr J_MakePoint clearfix">
								<div class="address-info" style="margin-left: 15px;">
									账号密码：<font color="red">*</font><input type="password"
										id="password" name="password"
										style="height: 25px; margin-left: 4px;"
										onfocus="FocusItem(this)" onblur="CheckItem(this);"> <span
										style="color: red"></span>
								</div>
							</li>
							<li class="J_Addr J_MakePoint clearfix">
								<div class="address-info" style="margin-left: 15px;">
									确认密码：<font color="red">*</font><input type="password"
										id="repassword" name="repassword"
										style="height: 25px; margin-left: 4px;"
										onfocus="FocusItem(this)" onblur="CheckItem(this);"> <span
										style="color: red"></span>
								</div>
							</li>
							<li class="J_Addr J_MakePoint clearfix">
								<div class="address-info" style="margin-left: 15px;">
									二级密码：<input type="password" id="sepassword" name="sepassword"
										style="height: 25px; margin-left: 10px;">
								</div>
							</li>
							<li class="J_Addr J_MakePoint clearfix">
								<div class="address-info">
									账号角色名：<font color="red">*</font><input type="text"
										id="rolename" name="rolename"
										style="height: 25px; margin-left: 5px;"
										onfocus="FocusItem(this)" onblur="CheckItem(this);"> <span
										style="color: red"></span>
								</div>
							</li>
							<li class="J_Addr J_MakePoint clearfix">
								<div class="address-info">
									确认角色名：<font color="red">*</font><input type="text"
										id="rerolename" name="rerolename"
										style="height: 25px; margin-left: 5px;"
										onfocus="FocusItem(this)" onblur="CheckItem(this);"> <span
										style="color: red"></span>
								</div>
							</li>
						</ul>
						<input type="reset" class="add-btn" value="重置信息"
							style="height: 25px; width: 60px; padding: 0px 3px 1px 3px; margin-left: 208px;" />
						<input type="submit" class="add-btn" value="上架商品"
							style="height: 25px; width: 60px; padding: 0px 3px 1px 3px" />
					</form>
				</div>
				<!-- 包裹表单的盒子 -->
				<!--游戏账号的信息结束-->
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.js"></script> <!-- Bootstrap Core JavaScript -->
	<script src="assets/js/bootstrap.min.js"></script> <script
		src="assets/js/bootstrap-dropdownhover.min.js"></script> <!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script> <script
		src="assets/js/wow.min.js"></script> <!-- owl carousel --> <script
		src="assets/owl-carousel/owl.carousel.js"></script> <!--  Custom Theme JavaScript  -->
	<script src="assets/js/custom.js"></script> <script
		src="scripts/proinfo.js"></script>
</body>
</html>
