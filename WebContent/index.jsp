<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>VShop交易平台</title>
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes"/>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<link href="css/demo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript">
	$(function() {

		$(".i-text").focus(function() {
			$(this).addClass('h-light');
		});

		$(".i-text").focusout(function() {
			$(this).removeClass('h-light');
		});

		$("#username").focus(function() {
			var username = $(this).val();
			if (username == '请输入用户名') {
				$(this).val('');
			}
		});

		$("#username").focusout(function() {
			var username = $(this).val();
			if (username == '') {
				$(this).val('请输入用户名');
			}
		});

		$("#yzm").focus(function() {
			var $yzm = $(this).val();
			if ($yzm == '输入验证码') {
				$(this).val('');
			}
		});

		$("#yzm").focusout(function() {
			var $yzm = $(this).val();
			if ($yzm == '') {
				$(this).val('输入验证码');
			}
		});
	});

	function check() {
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		var yzm = document.getElementById("yzm");
		var error = document.getElementById("error");
		if (username.value == "" || username.value == "请输入用户名") {
			error.innerHTML = "<font color=\"red\">用户名不能为空！</font>";
			username.focus();
			return false;
		} else if (password.value == "") {
			error.innerHTML = "<font color=\"red\">密码不能为空！</font>";
			password.focus();
			return false;
		} else {
			//验证验证码
			return checkCode(yzm);
		}
	}

	function checkCode(self) {
		var flag = true;
		/* 获取验证码 */
		var $yzm = $(self).val();
		var param = "code=" + $yzm;
		/* 验证验证码的servlet类的路径 */
		var url = "${pageContext.request.contextPath}/CheckCodeServlet";
		$.ajax({
			url : url,// 请求的servlet地址
			type : "POST",// 请求方式
			async : false,//是否为异步请求
			data : param,// 发送到服务器的数据
			dataType : "text",// 设置返回数据类型
			success : function(data) {
				//获取输出错误信息的控件
				var $error = $("#error");
				//根据返回的结果判断验证码是否正确
				if (data == "false") {
					//验证码错误
					$error.html("验证码错误");
					//刷新验证码
					change();
					//刷新验证码输入框
					$(self).val('输入验证码');
					flag = false;
				} else {
					$error.html("");
				}
			},// 响应成功后执行的回调方法data响应文本
			complete : function(XMLHttpRequest, statusText) {

			},// 响应完成后执行的回调方法
			error : function(XMLHttpRequest, statusText) {
				
			}// 响应失败后执行的回调方法
		})
		return flag;
	}

	//刷新验证码
	function change() {
		var url = "CodeServlet" + "?id=" + new Date().getTime();
		var $pic = $("#pic");
		$pic.attr("src", url);
	}
</script>
</head>
<body>
	<div class="header">
		<h1 class="headerLogo">
			<a title="虚拟道具交易平台" target="_blank" href="main.jsp"> <img
				alt="logo" src="images/logo.jpg" /></a>
		</h1>
		<div class="headerNav">
			<a target="_blank" href="main.jsp">首页</a> <a>关于我们</a> <a>意见反馈</a> <a>帮助</a>
		</div>
	</div>

	<div class="banner">
		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout: fixed;">
				<div class="reg">
					还没有vshop账号？10秒<a href="register.jsp">注册</a>
				</div>
				<!-- 输入返回的错误信息 -->
				<div id="error" style="color: red;">${requestScope.error }</div>
				<form class="registerform" action="user_login.action"
					onsubmit="return check()" method="post">
					<div class="fm-item">
						<label for="logonId" class="form-label">用户登陆：</label> <input
							type="text" value="请输入用户名" maxlength="100" name="username"
							id="username" class="i-text" />
						<div class="ui-form-explain"></div>
					</div>

					<div class="fm-item">
						<label for="logonId" class="form-label">登陆密码：</label> <input
							type="password" value="" maxlength="100" name="password"
							id="password" class="i-text" />
						<div class="ui-form-explain"></div>
					</div>

					<div class="fm-item pos-r">
						<label for="logonId" class="form-label">验证码</label> <input
							type="text" value="输入验证码" maxlength="100" id="yzm" name="yzm"
							class="i-text yzm" />
						<div class="ui-form-explain">
							<img src="CodeServlet" id="pic" class="yzm-img"
								onclick="change()" />
						</div>
					</div>

					<div class="fm-item">
						<label for="logonId" class="form-label"></label> <input
							type="submit" value="" tabindex="4" id="send-btn"
							class="btn-login" />
						<div class="ui-form-explain"></div>
					</div>
				</form>
			</div>
		</div>

		<div class="bd">
			<ul>
				<li
					style="background: url(themes/theme-pic.jpg) #CCE1F3 center 0 no-repeat;"></li>
			</ul>
		</div>

		<div class="hd">
			<ul></ul>
		</div>
	</div>
	<script type="text/javascript">
		$(".banner").slide({
			titCell : ".hd ul",
			mainCell : ".bd ul",
			effect : "fold",
			autoPlay : true,
			autoPage : true,
			trigger : "click"
		});
	</script>

	<div class="banner-shadow"></div>

	<div class="footer">
		<p>
			Copyright &copy; 2017.Company name Mao hzu dalao.<a target="_blank">vshop</a>
		</p>
	</div>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>
