<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>全部商品</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
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
<!-- Style CSS -->
<link rel="stylesheet" href="assets/css/slider.css" type="text/css">
<!--  baguetteBox -->
<link rel="stylesheet" href="assets/css/baguetteBox.css">
<!-- Owl Carousel Assets -->
<link href="assets/owl-carousel/owl.carousel.css" rel="stylesheet">
<link href="assets/owl-carousel/owl.theme.css" rel="stylesheet">
<script src="assets/js/jquery.js"></script>
<script type="text/javascript" src="scripts/order.js"></script>
<script type="text/javascript" src="scripts/findpro.js"></script>
<script type="text/javascript">
	$(function() {
		// 把选中的类别加粗
		var type = "${requestScope.proname}";
		var $cate = $(".navbar-nav>li>a[href*='" + type + "']");
		if ($cate.length == 0) {
			//全部
			$(".navbar-nav>li>a[href='prolist.action']").css("font-weight", "bold");
		} else {
			//其他
			$cate.css("font-weight", "bold");
		}
	})
</script>
</head>
<body>
	<!--  Preloader  -->
	<div id="preloader">
		<div id="loading"></div>
	</div>
	<header class="header2"> <!--  top-header  -->
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
		<div class="col-sm-3">
			<div class="logo">
				<h6>
					<img src="assets/images/logo.png" alt="logo" />
				</h6>
			</div>
		</div>
		<div class="col-sm-6">
			<!-- Search box Start -->
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
						<input type="text" placeholder="道具关键词" id="keyword" />
					</div>
					<div class="btn-group">
						<button type="button" id="btnSearch" class="btn btn-primary"
							onclick="findProdcut();">
							<i class="fa fa-search" aria-hidden="true"></i>
						</button>
					</div>
				</div>
			</form>
			<!-- Search box End -->
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
			<!-- 我要买    我要卖 -->
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
						aria-expanded="false"><span>热门游戏</span> <i class="fa fa-bars"
							aria-hidden="true"></i> </a>
						<ul class="dropdown-menu dropdownhover-bottom" role="menu">
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
			<!-- 游戏列表 -->
		</div>
	</div>
	</section> </header>
	<!-- newsletter -->
	<section class="grid-shop"> <!-- .grid-shop -->
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 10px"></div>
			<div class="col-sm-3 col-md-3">
				<div class="weight"></div>
				<div class="weight">
					<div class="title" style="margin-bottom: -40px">
						<h2>精品推荐</h2>
					</div>
					<div class="ads-lft">
						<img src="assets/images/add-banner2.jpg" alt="add banner">
					</div>
				</div>
				<div class="weight">
					<div class="title">
						<h2>跳楼大甩卖</h2>
					</div>
					<div class="toprating-box">
						<ul>
							<li>
								<div class="e-product">
									<div class="pro-img">
										<img src="assets/images/products/digital/5.jpg" alt="2">
									</div>
									<div class="pro-text-outer">
										<span>DNF 精品搬砖号</span> <a href="#">
											<h4>全职业满级，搬砖四帝任你选择</h4>
										</a>
										<p class="wk-price">￥290.00</p>
									</div>
								</div>
							</li>
							<li>
								<div class="e-product">
									<div class="pro-img">
										<img src="assets/images/products/digital/9.jpg" alt="2">
									</div>
									<div class="pro-text-outer">
										<span>LOL 人机满级小号</span> <a href="#">
											<h4>无任何匹配记录 装逼虐菜必备</h4>
										</a>
										<p class="wk-price">￥250.00</p>
									</div>
								</div>
							</li>
							<li>
								<div class="e-product">
									<div class="pro-img">
										<img src="assets/images/products/digital/12.jpg" alt="2">
									</div>
									<div class="pro-text-outer">
										<span>崩坏3 脱宅当现充</span> <a href="#">
											<h4>找个有缘人，托付女儿们</h4>
										</a>
										<p class="wk-price">￥390.00</p>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9">
				<div class="col-md-12 grid-banner">
					<img src="assets/images/Grid-banner.png" alt="Grid-banner" />
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
				<!-- forEach开始 -->
				<c:if test="${requestScope.selectPro.size() > 0}">
					<c:forEach items="${requestScope.selectPro }" var="product" end="9">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<!-- .pro-text -->
							<div class="pro-text">
								<div class="col-xs-12 col-sm-5 col-md-5">
									<!-- .pro-img -->
									<div class="pro-img">
										<img src="${product.fileName }" alt="2">
										<c:if test="${product.user.id ==sessionScope.user.id}">
											<sup class="sale-tag">You&nbsp;&nbsp;sale!</sup>
										</c:if>
										<c:if test="${product.user.id !=sessionScope.user.id}">
											<sup class="sale-tag">Sale!</sup>
										</c:if>
									</div>
									<!-- /.pro-img -->
								</div>
								<div class="col-xs-12 col-sm-7 col-md-7">
									<div class="pro-text-outer list-pro-text">
										<strong><span>${product.account.game_name }</span></strong>
										<h4>商品：${product.product_name }</h4>
										<div class="star2">
											<ul>
												<li class="yellow-color"><strong>库存：${product.stock }</strong></li>
											</ul>
										</div>
										<p class="wk-price">单价：${product.price }元</p>
										<p>游戏区服：${product.account.game_region }&nbsp;&nbsp;&nbsp;${product.account.game_server }</p>
										<p>简介：${product.describe }</p>
										<c:choose>
											<c:when test="${sessionScope.user!=null }">
												<!-- 订单页面 -->
												<a href="shop.action?proid=${product.id }" class="add-btn">立即购买</a>
											</c:when>
											<c:otherwise>
												<!-- 带着商品id跳转到登录界面 -->
												<a href="index.jsp?proid=${product.id }" class="add-btn">立即购买</a>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>
							<!-- /.pro-text -->
						</div>
					</c:forEach>
					<!-- forEach结束 -->
				</c:if>
				<c:if test="${requestScope.selectPro.size() <= 0}">
					<div>
						<img alt="logo" src="images/logo-02.jpg" /> <strong><font
							color="black" size="5px;">很抱歉您查看的商品找不到了！</font></strong>
						<div style="margin-left: 235px;">
							<p>您可以：</p>
							<p>1.检查刚才的输入</p>
							<p>
								2.去其他地方逛逛 <a
									href="pro_cate.action?cate=币&gamename=${param.gamename}">游戏币</a>
								|&nbsp;&nbsp;<a
									href="pro_cate.action?cate=账号&gamename=${param.gamename}">账号</a>
							</p>
						</div>
					</div>
				</c:if>
				<!-- 商品结束 -->
				<div class="col-xs-12">
					<div class="grid-spr pag">
						<!-- .pagetions -->
						<div class="col-xs-12 col-sm-6 col-md-6 text-left">
							<ul class="pagination">
								<c:forEach begin="1" end="${requestScope.pageCount }"
									varStatus="status">
									<!-- 生成页码 -->
									<!-- 翻页时将游戏名，搜索关键词，商品类名，页码传递到地址栏 -->
									<c:choose>
										<c:when test="${status.index == param.page}">
											<li class="active"><a
												href="pro_page.action?gamename=${param.gamename}&keyword=${param.keyword}&page=${status.index}&cate=${param.cate}">${status.index}</a></li>
										</c:when>
										<c:when test="${param.page==null && status.index==1}">
											<li class="active"><a
												href="pro_page.action?gamename=${param.gamename}&keyword=${param.keyword}&page=${status.index}&cate=${param.cate}">${status.index}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="pro_page.action?gamename=${param.gamename}&keyword=${param.keyword}&page=${status.index}&cate=${param.cate}">${status.index}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
						<!-- /.pagetions -->
						<!-- .Showing -->
						<div class="col-xs-12 col-sm-6 col-md-6 text-right">
							<strong>商品超值！！<span>你值得拥有！！！</span></strong>
						</div>
						<!-- /.Showing -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<hr>
	<!-- /.grid-shop --> </section>
	<footer>
	<div class="container" style="margin-top: -90px;">
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
	<!-- 分享专栏 -->
	<!-- JiaThis Button BEGIN -->
	<div class="jiathis_share_slide jiathis_share_32x32"
		id="jiathis_share_slide" style="margin-left: 1375px;">
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
	<!-- 分享专栏 END -->
	<p id="back-top">
		<a href="#top"><i class="fa fa-chevron-up" aria-hidden="true"></i></a>
	</p>
	<!-- Bootstrap Core JavaScript -->
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/bootstrap-dropdownhover.min.js"></script>
	<!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<!-- owl carousel -->
	<script src="assets/owl-carousel/owl.carousel.js"></script>
	<!--  Custom Theme JavaScript  -->
	<script src="assets/js/filter-price.js"></script>
	<script src="assets/js/custom.js"></script>
</body>

</html>
