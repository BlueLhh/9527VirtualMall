<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>VShop - 注册页</title>
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
<link href="${pageContext.request.contextPath}/css/index.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" />
<script src="scripts/jquery-2.1.0.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/index.js"></script>
</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="register" class="wrap">
		<div class="shadow">
			<em class="corner lb"></em> <em class="corner rt"></em>
			<div class="box">
				<h1>
					欢迎注册<i>VShop</i>
				</h1>
				<ul class="steps clearfix">
					<li class="current"><em></em>填写注册信息</li>
					<li class="last"><em></em>注册成功</li>
				</ul>
				<!-- 注意表单提交路径 action="" -->
				<form id="regForm" method="post" action="user_register.action"
					onsubmit="return checkForm(this)">
					<!-- 使用隐藏域，传递一个键值对到后台，判断是注册、登陆登操作 -->
					<input type="hidden" value="reg" name="option" />
					<table>
						<tr>
							<td class="field">用户名：</td>
							<td><input id="username" class="text" type="text"
								name="username" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" maxlength="15" /><span id="uname"></span></td>
						</tr>
						<tr>
							<td class="field">登录密码：</td>
							<td><input class="text" type="password" id="password"
								name="password" onfocus="FocusItem(this)"
								onblur="CheckItem(this);" /><span></span></td>
						</tr>
						<tr>
							<td class="field">确认密码：</td>
							<td><input class="text" type="password" name="rePassword"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
						</tr>
						<tr>
							<td class="field">性别：</td>
							<td><input type="radio" name="sex" style="border: 0px;"
								checked="checked" value="0" />男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="radio" name="sex" style="border: 0px;" value="1" />女<span></span></td>
						</tr>
						<tr>
							<td class="field">出生日期：</td>
							<td><select size="1" id="year" name="year">
									<option>1917</option>
							</select>&nbsp;&nbsp;&nbsp; <select size="1" id="month" name="month">
									<option>1</option>
							</select>&nbsp;&nbsp;&nbsp; <select size="1" id="day" name="day">
									<option>1</option>
							</select> <span></span></td>
						</tr>
						<tr>
							<td class="field">身份证：</td>
							<td><input class="text" type="text" name="identity"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
						</tr>
						<tr>
							<td class="field">手机：</td>
							<td><input class="text" type="text" name="mobile"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
						</tr>
						<tr>
							<td class="field">QQ：</td>
							<td><input class="text" type="text" name="qq"
								onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span
								id="uqq"></span></td>
						</tr>
						<tr>
							<td class="field">验证码：</td>
							<td><input class="text verycode" type="text" name="veryCode"
								onfocus="FocusItem(this)" maxlength="4" /><img id="veryCode"
								src="regcode.jsp" onclick="change()" /><span id="Code"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><label class="ui-green"><input type="submit"
									name="submit" value="提交注册" /></label></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2017.Company name Mao hzu
		dalao.vshop</div>
</body>
</html>

