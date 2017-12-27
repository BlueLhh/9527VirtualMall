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
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script type="text/javascript">
	$(function() {
		//把选中的订单状态类别的下划线去掉
		var menu = "${requestScope.status}";
		var $status = $(".ostatus>ul>li>a[href*='" + menu + "']");
		if ($status.length == 0) {
			//全部
			$(".ostatus>ul>li>a[href='mysell.action']").parent().css(
					"border-bottom", "solid #fff");
		} else {
			$status.parent().css("border-bottom", "solid #fff");
		}
	})
</script>
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
						size="5px">我的商品</font></li>
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
				<div class="grid-spr">
					<div class="col-sm-6 col-md-6" style="width: 100%;">
						<div class="col-sm-6 col-md-6 text-right"
							style="margin-left: -60px; margin-top: 7px; width: 25%;">
							<strong style="font-size: 20px;">请选择游戏：</strong>
						</div>
						<div class="select-option" style="width: 25%; margin-right: 15px">
							<a class="btn btn-default btn-select options2"> <input
								type="hidden" class="btn-select-input" id="1" name="1" value="" />
								<span class="btn-select-value">Select an Item</span> <span
								class="btn-select-arrow fa fa-angle-down"></span>
								<ul>
									<li class="selected">游戏名称</li>
									<li>地下城与勇士</li>
									<li>英雄联盟</li>
									<li>征途2</li>
									<li>冒险岛2</li>
									<li>剑侠情缘</li>
									<li>蜀门</li>
									<li>王者农药</li>
									<li>阴阳师</li>
									<li>崩坏3</li>
									<li>穿越火线：枪战王者</li>
								</ul>
							</a>
						</div>

						<div class="select-option" style="width: 25%; margin-right: 15px">
							<a class="btn btn-default btn-select options2"> <input
								type="hidden" class="btn-select-input" id="1" name="1" value="" />
								<span class="btn-select-value">Select an Item</span> <span
								class="btn-select-arrow fa fa-angle-down"></span>
								<ul>
									<li class="selected">游戏区</li>
								</ul>
							</a>
						</div>

						<div class="select-option" style="width: 25%;">
							<a class="btn btn-default btn-select options2"> <input
								type="hidden" class="btn-select-input" id="1" name="1" value="" />
								<span class="btn-select-value">Select an Item</span> <span
								class="btn-select-arrow fa fa-angle-down"></span>
								<ul>
									<li class="selected">服务器</li>
								</ul>
							</a>
						</div>
					</div>
				</div>
				<!-- 商品状态列表 -->
				<div class="ostatus">
					<ul>
						<li><a href="mysell.action">全部</a></li>
						<li><a href="mypro_option.action?status=-1">已撤销</a></li>
						<li><a href="mypro_option.action?status=0">拍卖中</a></li>
						<li><a href="mypro_option.action?status=2">交易完成</a></li>
					</ul>
				</div>
				<div class="rlborder"></div>
				<!-- 左右边框线 -->
				<div style="width: 96%; margin-left: 19px;" class="dlist">
					<form name="addrForm" id="addrForm" action="EnsureOrderServlet"
						method="post">
						<table cellspacing="0" cellpadding="0" class="order-table"
							id="J_OrderTable" summary="统一下单订单信息区域">
							<caption style="display: none">统一下单订单信息区域</caption>
							<thead>
								<tr>
									<th class="s-title">商品信息
										<hr />
									</th>
									<th class="s-price">单价
										<hr />
									</th>
									<th class="s-agio">库存
										<hr />
									</th>
									<th class="s-agio">成交
										<hr />
									</th>
									<th class="s-total">订单状态
										<hr />
									</th>
								</tr>
							</thead>
							<tbody class="J_Shop">
								<!-- forEach开始 -->
								<c:forEach items="${requestScope.plist}" var="product">
									<tr class="item">
										<td class="s-title"><a href="#" class="J_MakePoint">
												<img src="${product.fileName }" class="itempic"> <span
												class="title J_MakePoint">${product.account.game_name }：${product.product_name }</span>
										</a>
											<div class="props">
												<span>游戏区服：</span> <span>${product.account.game_region }
												</span> <span>${product.account.game_server } </span>
											</div>
											<div>
												<!-- 显示商品描述 -->
												<span style="color: gray;">简介：${product.describe }</span>
											</div></td>
										<td class="s-price"><span class='price'> <em
												class="style-normal-small-black J_ItemPrice" id="price">${product.price}元</em>
										</span> <span>${product.up_date }</span></td>
										<td class="s-amount">
											<!-- 库存数量 --> ${product.stock }
										</td>
										<td>${product.sellCount }笔</td>
										<td class="s-total" style="padding-right: 10px">
											<!-- 订单状态 --> <c:choose>
												<c:when test="${product.stock>0 && product.status==0}">
													<!-- 把选中商品的id，购买数量，订单id，传到对应的servlet -->
													<a href="prodown.action?proid=${product.id }&option=down"
														class="add-btn"
														style="height: 22px; width: 56px; padding: 0px 3px 0px 0px;">取消上架</a>
													<p>拍卖中</p>
												</c:when>
												<c:when test="${product.status==2 }">
													<span>交易完成</span>
												</c:when>
												<c:when test="${product.status==-1 }">
													<span>已撤销</span>
												</c:when>
											</c:choose>
										</td>
									</tr>
									<!-- 商品行信息结束 -->
									<tr class="blue-line" style="height: 2px;">
										<td colspan="5"></td>
									</tr>
									<!-- 该行为那个蓝色虚线 -->
								</c:forEach>
								<!-- forEach结束 -->
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="assets/js/jquery.js"></script> <!-- Bootstrap Core JavaScript -->
	<script src="assets/js/bootstrap.min.js"></script> <script
		src="assets/js/bootstrap-dropdownhover.min.js"></script> <!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script> <script
		src="assets/js/wow.min.js"></script> <!-- owl carousel --> <script
		src="assets/owl-carousel/owl.carousel.js"></script> <!--  Custom Theme JavaScript  -->
	<script src="assets/js/custom.js"></script>
</body>
</html>
