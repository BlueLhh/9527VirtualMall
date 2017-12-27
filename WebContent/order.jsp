<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购买页面</title>
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
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
<!-- 订单详细布局CSS -->
<link rel="stylesheet" href="css/tasp.css" />
<link rel="stylesheet" href="css/shop.css"> 
<link href="css/orderconfirm.css" rel="stylesheet" />
<script type="text/javascript" src="scripts/jquery-2.1.0.js"></script>
<script type="text/javascript" src="scripts/order.js"></script>
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
							 <a href="#" style="color: white; text-decoration: none;">服务中心</a></li>
						<li><i class="icon-note icons" aria-hidden="true"></i> 
						     <a href="#" style="color: white; text-decoration: none;">意见反馈</a></li>
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
	<section class="all-product"> <!-- all-product --> <!-- 复制 -->
	<!-- 订单可以从这里开始写-->
	<div id="page">
		<div id="content" class="grid-c">
			<div id="address" class="address" style="margin-top: 20px;">
				<form name="addrForm" id="addrForm" action="orderCommit.action" method="post">
					<h3>
						<font color="red">确认收货信息 </font><span class="manage-address"></span>
					</h3>
					<ul id="address-list" class="address-list">
						<li class="J_Addr J_MakePoint clearfix  J_DefaultAddr ">
							<div class="address-info">
								电话号码：<font color="red">*</font><input type="text" id="phone"
									name="phone" value="${sessionScope.user.phone }"
									style="height: 25px; margin-left: 5px;">
							</div>
						</li>
						<li class="J_Addr J_MakePoint clearfix">
							<div class="address-info">
								Q Q 号码：<font color="red">*</font><input type="text" id="qq"
									name="qq" value="${sessionScope.user.qq }"
									style="height: 25px; margin-left: 5px;">
							</div>
						</li>
						<li class="J_Addr J_MakePoint clearfix">
							<div class="address-info">
								收货角色名：<input type="text" id="rolename" name="rolename"
									style="height: 25px;">
							</div>
						</li>
						<li class="J_Addr J_MakePoint clearfix">
							<div class="address-info">
								确认角色名：<input type="text" id="rerolename" name="rerolename"
									style="height: 25px;">
							</div>
						</li>
					</ul>
					<div style="width: 100%">
						<hr>
					</div>
					<h3 class="dib" style="margin-top: 30px;margin-bottom: -22px;"><font color="red">确认订单信息</font></h3>
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
								<th class="s-agio">数量
									<hr />
								</th>
								<th class="s-amount">卖家信誉
									<hr />
								</th>
								<th class="s-total">小计
									<hr />
								</th>
							</tr>
						</thead>
						<tbody class="J_Shop">
							<tr class="item">
								<td class="s-title"><a href="#" target="_blank"
									class="J_MakePoint">
										<img src="${product.fileName }" class="itempic">
										<span class="title J_MakePoint">${requestScope.gameAccount.game_name }：${requestScope.product.product_name }</span></a>
									<div class="props">
										<span>游戏区服：</span> <span>${requestScope.gameAccount.game_region }
										</span> <span>${requestScope.gameAccount.game_server } </span>
									</div>
									<div>
										<!-- 显示商品描述 -->
										<span style="color: gray;">简介：${requestScope.product.describe }</span>
									</div></td>
								<td class="s-price"><span class='price '> <em
										class="style-normal-small-black J_ItemPrice" id="price">${requestScope.product.price }元</em>
								</span></td>
								<td class="s-amount">
									<!-- 购买数量 -->
									 <input type="button" value=" - " id="sub" name="sub" onclick="subAccount(${requestScope.product.price });" /> 
									 <input type="text" value="1" id="account" name="account" style="width: 23px" /> 
									 <input type="button" value="+" id="add" name="add"	onclick="addAccount(${requestScope.product.price },${requestScope.product.stock });" />
									<div class="props">
										库存：<span id="stock">${requestScope.product.stock }</span>
									</div>
								</td>
								<td class="s-agio">
									<div class="J_Promotion promotion" data-point-url="">
										<!-- 卖家信誉 -->
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
									</div>
								</td>
								<td class="s-total" style="padding-right: 10px"><span class='price '> <em
										class="style-normal-bold-red J_ItemTotal "><span id="sum">${requestScope.product.price }</span>元</em>
								</span>
								</td>
							</tr>
							<tr class="blue-line" style="height: 2px;">
								<td colspan="5"></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="5">
									<div class="order-go" data-spm="4">
										<div class="J_AddressConfirm address-confirm">
											<!-- 设置隐藏域，传递商品id到OrderServlet类 -->
											<input type="hidden" value="${requestScope.product.id }" id="proid"name="proid"/>
											<!-- 设置隐藏域，记录商品出售人id，用于js中check()方法的判断 -->
											<input type="hidden" value="${requestScope.product.user.id }" id="prouid" />
											<!-- 设置隐藏域，记录当前登录用户的id，用于js中check()方法的判断 -->
											<input type="hidden" value="${sessionScope.user.id }" id="userid" />
											<input type="submit" value="提交订单" style="font-size: 20px;padding-left: 7px;padding-right: 7px;" onclick="return check(${sessionScope.user.balance});"/>
										</div>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
					</form>
				</div>
		</div>
	<div id="footer"></div>
	</div>
	<!-- 填写订单结束 -->
	<!-- 网站信息开始 -->
	<hr>
	<div class="container" style="margin-top: -10px; margin-left: 190px;">
		<div class="row">
			<div class="title"></div>
		</div>
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
	<script src="assets/js/jquery.js"></script> <!-- Bootstrap Core JavaScript -->
	<script src="assets/js/bootstrap.min.js"></script> <script
		src="assets/js/bootstrap-dropdownhover.min.js"></script> <!-- Plugin JavaScript -->
	<script src="assets/js/jquery.easing.min.js"></script> <script
		src="assets/js/wow.min.js"></script> <!-- owl carousel --> <script
		src="assets/owl-carousel/owl.carousel.js"></script> <!--  Custom Theme JavaScript  -->
	<script src="assets/js/custom.js"></script>
</body>
</html>
