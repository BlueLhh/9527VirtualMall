<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>主页面</title>
<!-- Latest Bootstrap min CSS -->
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
<!--  baguetteBox -->
<link rel="stylesheet" href="assets/css/baguetteBox.css">
<!-- Owl Carousel Assets -->
<link href="assets/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="assets/owl-carousel/owl.theme.css" rel="stylesheet">
<script src="assets/js/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script type="text/javascript" src="scripts/order.js"></script>
<script type="text/javascript" src="scripts/findpro.js"></script>
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

	<!-- 第一次复制 -->

	<section class="top-md-menu">
	<div class="container">
		<div class="col-sm-3">
			<div class="logo">
				<h6>
					<img src="assets/images/logo.png" alt="logo" />
				</h6>
			</div>
		</div>
		<div class="col-sm-6">
			<!-- 搜索框  开始 -->
			<form>
				<div class="well carousel-search hidden-phone">
					<div class="btn-group">
						<a class="btn dropdown-toggle btn-select" data-toggle="dropdown"
							href="#">游戏名称 <span class="caret"></span></a>
						<ul class="dropdown-menu" id="gamename">
							<li><a href="#">地下城与勇士</a></li>
							<li><a href="#">英雄联盟</a></li>
							<li><a href="#">征途2</a></li>
							<li><a href="#">冒险岛2</a></li>
							<li><a href="#">剑侠情缘</a></li>
							<li><a href="#">蜀门</a></li>
							<li class="divider"></li>
							<li><a href="#">王者农药</a></li>
							<li><a href="#">阴阳师</a></li>
							<li><a href="#">崩坏3</a></li>
							<li><a href="#">穿越火线：枪战王者</a></li>
						</ul>
					</div>
					<div class="search">
						<!-- 搜索框关键字 -->
						<input type="text" placeholder="道具关键词" id="keyword" />
					</div>
					<div class="btn-group">
						<!-- 搜索框按钮 -->
						<button type="button" id="btnSearch" class="btn btn-primary"
							onclick="findProdcut();">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</form>
			<!-- 搜索框  结束 -->
		</div>
		<div class="col-sm-3">
			<!-- cart-menu -->
			<div class="cart-menu">
				<ul>
					<li><c:choose>
							<c:when test="${sessionScope.user!=null }">
								<a href="sellproduct.jsp"><i class="icon-heart icons"> </i></a>
								<strong>我要卖</strong>
							</c:when>
							<c:when test="${sessionScope.user==null }">
								<a href="index.jsp"><i class="icon-heart icons"> </i></a>
								<strong>我要卖</strong>
							</c:when>
						</c:choose>
					</li>
					<li><a href="prolist.action"> <i
							class="icon-basket-loaded icons"> </i></a><strong>我要买</strong></li>
				</ul>
			</div>
			<!-- cart-menu End -->
		</div>
		<div class="main-menu">
			<!--  nav  -->
			<nav class="navbar navbar-inverse navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1" data-hover="dropdown"
				data-animations=" fadeInLeft fadeInUp fadeInRight">
				<ul class="nav navbar-nav">
					<li class="all-departments dropdown"><a href="index.html"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-expanded="false"> <span> 热门游戏</span> <i
							class="fa fa-bars" aria-hidden="true"> </i></a>
						<ul class="dropdown-menu dropdownhover-bottom all-open"
							role="menu">
							<li class="dropdown"><a href="prolist.action?gamename=地下城与勇士"><img
									src="assets/images/menu-icon1.png" alt="menu-icon1"
									style="margin-top: -7px;" /> 地下城与勇士 <i
									class="fa fa-angle-right" aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=地下城与勇士&cate=币">游戏币</a></li>
									<li><a href="pro_cate.action?gamename=地下城与勇士&cate=深渊票">深渊票</a></li>
									<li><a href="pro_cate.action?gamename=地下城与勇士&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=地下城与勇士&cate=道具">道具</a></li>
									<li><a href="pro_cate.action?gamename=地下城与勇士&cate=装备">装备</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=征途2"><img
									src="assets/images/menu-icon2.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 征途2 <i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=征途2&cate=币">游戏币</a></li>
									<li><a href="pro_cate.action?gamename=征途2&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=征途2&cate=装备">装备</a></li>
									<li><a href="pro_cate.action?gamename=征途2&cate=玄兽">玄兽</a></li>
									<li><a href="pro_cate.action?gamename=征途2&cate=其他">其他</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=冒险岛2"><img
									src="assets/images/menu-icon3.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 冒险岛2 <i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=冒险岛2&cate=币">游戏币</a></li>
									<li><a href="pro_cate.action?gamename=冒险岛2&cate=激活码">激活码</a></li>
									<li><a href="pro_cate.action?gamename=冒险岛2&cate=宝石">宝石</a></li>
									<li><a href="pro_cate.action?gamename=冒险岛2&cate=坐骑">坐骑</a></li>
									<li><a href="pro_cate.action?gamename=冒险岛2&cate=宠物">宠物</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=英雄联盟"><img
									src="assets/images/menu-icon7.png" alt="menu-icon2"
									style="margin-top: -7px;" />英雄联盟<i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=英雄联盟&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=英雄联盟&cate=代练">代练</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=王者农药"><img
									src="assets/images/menu-icon4.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 王者农药 <i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=王者农药&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=王者农药&cate=代练">代练</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=阴阳师"><img
									src="assets/images/menu-icon5.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 阴阳师 <i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=阴阳师&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=阴阳师&cate=SSR式神">SSR式神</a></li>
									<li><a href="pro_cate.action?gamename=阴阳师&cate=激活码">激活码</a></li>
								</ul></li>
							<li class="dropdown"><a href="prolist.action?gamename=崩坏3"><img
									src="assets/images/menu-icon6.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 崩坏3 <i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=崩坏3&cate=账号">账号</a></li>
									<li><a href="pro_cate.action?gamename=崩坏3&cate=道具">道具</a></li>
									<li><a href="pro_cate.action?gamename=崩坏3&cate=激活码">激活码</a></li>
								</ul>
							</li>
							<li class="dropdown"><a href="prolist.action?gamename=剑侠情缘"><img
									src="assets/images/menu-icon8.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 剑侠情缘<i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=剑侠情缘&cate=激活码">激活码</a></li>
								</ul>
							</li>
							<li class="dropdown"><a href="prolist.action?gamename=穿越火线：枪战王者"><img
									src="assets/images/menu-icon9.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 穿越火线：枪战王者<i class="fa fa-angle-right"
									aria-hidden="true"></i></a>
								<ul class="dropdown-menu right">
									<li><a href="pro_cate.action?gamename=穿越火线：枪战王者&cate=激活码">激活码</a></li>
								</ul>
							</li>
							<li><a href="prolist.action?gamename=蜀门"><img
									src="assets/images/menu-icon10.png" alt="menu-icon2"
									style="margin-top: -7px;" /> 蜀门</a>
							</li>
						</ul>
					</li>
					<li><a href="prolist.action">全部</a></li>
					<!-- 选中种类时将游戏名，搜索关键词，商品类名传递到地址栏 -->
					<li><a
						href="pro_cate.action?cate=币&gamename=${param.gamename}&keyword=${param.keyword}">游戏币</a></li>
					<li><a
						href="pro_cate.action?cate=账号&gamename=${param.gamename}&keyword=${param.keyword}">账号</a></li>
					<li><a
						href="pro_cate.action?cate=代练&gamename=${param.gamename}&keyword=${param.keyword}">代练</a></li>
					<li><a
						href="pro_cate.action?cate=装备&gamename=${param.gamename}&keyword=${param.keyword}">装备</a></li>
					<li><a
						href="pro_cate.action?cate=道具&gamename=${param.gamename}&keyword=${param.keyword}">道具</a></li>
					<li><a
						href="pro_cate.action?cate=点券&gamename=${param.gamename}&keyword=${param.keyword}">点券</a></li>
					<li><a
						href="pro_cate.action?cate=其他&gamename=${param.gamename}&keyword=${param.keyword}">其他</a></li>
					<!-- 横向游戏道具 -->
				</ul>
				<!-- /.navbar-collapse -->
			</div>
			</nav>
			<!-- /nav end -->
		</div>
	</div>
	</section>
	<!-- header-outer -->
	<section class="header-outer"> <!-- header-slider -->
	<div class="header-slider">
		<div id="home-slider" class="carousel slide carousel-fade"
			data-ride="carousel">
			<!-- .home-slider -->
			<div class="carousel-inner">
				<div class="item active"
					style="background-image: url(assets/images/home-header1.jpg); background-repeat: no-repeat; background-position: top;">
				</div>
				<div class="item"
					style="background-image: url(assets/images/home-header2.jpg); background-repeat: no-repeat; background-position: top;">
				</div>
				<div class="item"
					style="background-image: url(assets/images/home-header3.jpg); background-repeat: no-repeat; background-position: top;">
				</div>
				<div class="item"
					style="background-image: url(assets/images/home-header4.jpg); background-repeat: no-repeat; background-position: top;">
				</div>
				<div class="item"
					style="background-image: url(assets/images/home-header5.jpg); background-repeat: no-repeat; background-position: top;">
				</div>
			</div>
			<!-- indicators -->
			<ol class="carousel-indicators">
				<li data-target="#home-slider" data-slide-to="0" class="active"></li>
				<li data-target="#home-slider" data-slide-to="1"></li>
				<li data-target="#home-slider" data-slide-to="2"></li>
				<li data-target="#home-slider" data-slide-to="3"></li>
				<li data-target="#home-slider" data-slide-to="4"></li>
			</ol>
			<!-- /indicators -->
			<!-- /.home-slider -->
		</div>
	</div>
	<!-- /header-slider end --> </section>
	<!-- /header-outer -->
	</header>
	<!-- banner -->
	<section class="banner">
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4 col-md-4">
				<!-- banner-img -->
				<a href="#" class="banner-img"></a>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4">
				<!-- banner-img -->
				<a href="#" class="banner-img banner-img2"></a>
			</div>
			<div class="col-xs-12 col-sm-4 col-md-4">
				<!-- banner-img -->
				<a href="#" class="banner-img banner-img3"></a>
			</div>
		</div>
	</div>
	</section>
	<!-- /banner -->
	<!-- 商品推荐 -->
	<!-- all-product -->
	<section class="all-product">
	<div class="container" style="margin-top: -40px">
		<div class="row">
			<div class="title">
				<h2>大家都在搜</h2>
			</div>
			<!-- /title -->
			<!-- FASHION -->
			<div class="electonics">

				<div class="brd2 col-xs-12 col-sm-3 col-md-3">
					<div id="home-slider3" class="carousel slide carousel-fade"
						data-ride="carousel">
						<!-- .home-slider -->
						<div class="carousel-inner" style="margin-left: 37px;">
							<div class="item active">
								<a class="ads" href="#"> <img
									src="assets/images/fs-add-banner1.jpg" alt="add-banner" />
								</a>
							</div>
							<div class="item">
								<a class="ads" href="#"> <img
									src="assets/images/fs-add-banner2.jpg" alt="add-banner" />
								</a>
							</div>
							<div class="item">
								<a class="ads" href="#"> <img
									src="assets/images/fs-add-banner3.jpg" alt="add-banner" />
								</a>
							</div>
						</div>
						<!-- indicators -->
						<ol class="carousel-indicators">
							<li data-target="#home-slider3" data-slide-to="0" class="active"></li>
							<li data-target="#home-slider3" data-slide-to="1"></li>
							<li data-target="#home-slider3" data-slide-to="2"></li>
						</ol>
						<!-- /indicators -->
						<!-- /.home-slider -->
					</div>
				</div>
				<div class="col-xs-12 col-sm-9 col-md-9">
					<div class="row">
						<!-- tab-content -->
						<div class="tab-content">
							<!-- tab-pane -->
							<div id="men" class="tab-pane fade in active">
								<div class="owl-demo-outer">
									<!-- #owl-demo -->
									<!-- 预计 forEach嵌套开始位置 owl-demo9 -->
									<div id="owl-demo9" class="deals-wk2">
										<div class="item" style="width: 100%">
											<!-- forEach开始 -->
											<c:forEach items="${requestScope.plist }" var="product"
												begin="0" end="3">
												<div class="bdr col-xs-12 col-sm-12 col-md-6"
													style="width: 50%">
													<!-- e-product -->
													<div class="e-product">
														<div class="pro-img">
															<img src="${product.fileName }" alt="2">
														</div>
														<div class="pro-text-outer">
															<span>${product.account.game_name }</span> <a href="#">
																<h4>${product.product_name }</h4>
															</a>
															<p class="wk-price">￥${product.price }</p>
															<!-- 判断用户登没登录 -->
															<c:choose>
																<c:when test="${sessionScope.user!=null }">
																	<!-- 跳到订单页面 -->
																	<a href="shop.action?proid=${product.id }"
																		class="add-btn">立即购买</a>
																</c:when>
																<c:otherwise>
																	<!-- 带着商品id先跳回到登录界面 -->
																	<a href="index.jsp?proid=${product.id }"
																		class="add-btn">立即购买</a>
																</c:otherwise>
															</c:choose>
														</div>
													</div>
													<!-- /e-product -->
												</div>
											</c:forEach>
											<!-- forEach结束 -->
										</div>
										<div class="item" style="width: 100%">
											<!-- forEach开始 -->
											<c:forEach items="${requestScope.plist }" var="product"
												begin="4" end="7">
												<div class="bdr col-xs-12 col-sm-12 col-md-6"
													style="width: 50%">
													<!-- e-product -->
													<div class="e-product">
														<div class="pro-img">
															<img src="${product.fileName }" alt="2">
														</div>
														<div class="pro-text-outer">
															<span>${product.account.game_name }</span> <a href="#">
																<h4>${product.product_name }</h4>
															</a>
															<p class="wk-price">￥${product.price }</p>
															<c:choose>
																<c:when test="${sessionScope.user!=null }">
																	<!-- 跳到订单页面 -->
																	<a href="shop.action?proid=${product.id }"
																		class="add-btn">立即购买</a>
																</c:when>
																<c:when test="${sessionScope.user==null }">
																	<!-- 带着商品id先跳回到登录界面 -->
																	<a href="index.jsp?proid=${product.id }"
																		class="add-btn">立即购买</a>
																</c:when>
															</c:choose>
														</div>
													</div>
													<!-- /e-product -->
												</div>
											</c:forEach>
											<!-- forEach结束 -->
										</div>
									</div>
									<!-- owl-demo9 div结束 -->
								</div>
							</div>
							<!-- /tab-pane -->
							<!-- 购买结束 -->
						</div>
						<!-- /tab-content -->
					</div>
					<!-- 修改 -->
				</div>
			</div>
		</div>
		<!-- /FASHION -->
		<!-- full-banner -->
		<div class="half-banner">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<a href="#" class="banner animated wow slideInUp"
						data-wow-delay="0ms" data-wow-duration="1500ms"> <img
						src="assets/images/add-banner-large.jpg" alt="add banner large">
					</a>
				</div>
			</div>
		</div>
		<!-- /full-banner -->
		<footer>
		<div class="container" style="margin-top: -100px">
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-md-4"
					style="width: 100%; margin-left: 80px;">
					<!-- f-weghit2 -->
					<div class="f-weghit2" style="width: 20%;">
						<h4>买家指南</h4>
						<ul>
							<li><a href="#">如何注册</a></li>
							<li><a href="#">如何购买</a></li>
							<li><a href="#">搜索商品</a></li>
							<li><a href="#">支付方式</a></li>
						</ul>
					</div>
					<!-- /f-weghit2 -->
					<!-- f-weghit2 -->
					<div class="f-weghit2" style="width: 20%;">
						<h4>卖家指南</h4>
						<ul>
							<li><a href="#">出售商品</a></li>
							<li><a href="#">寄售交易</a></li>
							<li><a href="#">担保交易</a></li>
							<li><a href="#">收费标准</a></li>
						</ul>
					</div>
					<!-- /f-weghit2 -->
					<!-- 脚注信息-->
					<div class="f-weghit2" style="width: 20%;">
						<h4>安全交易</h4>
						<ul>
							<li><a href="#">钓鱼防骗</a></li>
							<li><a href="#">预防盗号</a></li>
							<li><a href="#">谨防诈骗</a></li>
							<li><a href="#">实名认证</a></li>
						</ul>
					</div>
					<!-- 脚注信息 -->
					<div class="f-weghit2" style="width: 20%;">
						<h4>常见问题</h4>
						<ul>
							<li><a href="#">如何充值</a></li>
							<li><a href="#">如何提现</a></li>
							<li><a href="#">忘记密码</a></li>
							<li><a href="#">真假客服</a></li>
						</ul>
					</div>
					<div class="f-weghit2" style="width: 20%;">
						<h4>服务中心</h4>
						<ul>
							<li><a href="#">我要咨询</a></li>
							<li><a href="#">我要建议</a></li>
							<li><a href="#">我要投诉</a></li>
							<li><a href="#">我要充钱</a></li>
						</ul>
					</div>
				</div>
				<!-- copayright -->
				<div class="copayright">
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-6">
							Copyright &copy; 2017.Company name Mao hzu dalao.<a href="#">vshop</a>
						</div>
						<div class="text-right col-xs-12 col-sm-6 col-md-6">
							<img src="assets/images/payment-img.jpg" alt="payment-img" />
						</div>
					</div>
				</div>
				<!-- /copayright -->
			</div>
		</div>
		</footer>
		<!-- 修改"container"的标签结束 -->
	</div>
	<!-- 分享专栏 --> <!-- JiaThis Button BEGIN -->
	<div class="jiathis_share_slide jiathis_share_32x32"
		id="jiathis_share_slide" style="margin-left: 1165px;">
		<div class="jiathis_share_slide_top" id="jiathis_share_title"></div>
		<div class="jiathis_share_slide_inner">
			<div class="jiathis_style_32x32">
				<a class="jiathis_button_qzone"></a> <a class="jiathis_button_tsina"></a>
				<a class="jiathis_button_tqq"></a> <a class="jiathis_button_weixin"></a>
				<a class="jiathis_button_renren"></a> <a
					href="http://1887383oe5.iask.in/vshop/index.jsp"
					class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
				<script type="text/javascript">
					var jiathis_config = {
						slide : {
							divid : 'jiathis_main',
							pos : 'left'
						}
					};
				</script>
				<script type="text/javascript"
					src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
				<script type="text/javascript"
					src="http://v3.jiathis.com/code/jiathis_slide.js" charset="utf-8"></script>
			</div>
		</div>
	</div>
	<!-- 分享专栏 END --> <script src="assets/js/bootstrap.min.js"></script> <script
		src="assets/js/bootstrap-dropdownhover.min.js"></script> <!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script> <script
		src="assets/js/wow.min.js"></script> <!-- owl carousel --> <script
		src="assets/owl-carousel/owl.carousel.js"></script> <!--  Custom Theme JavaScript  -->
	<script src="assets/js/custom.js"></script>
</body>
</html>
