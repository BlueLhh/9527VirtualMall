// JavaScript Document
window.onload = function() {
	insertYear();
	insertMonth();
	insertDay();
}

function FocusItem(obj) {
	obj.parentNode.parentNode.className = "current";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	msgBox.innerHTML = "";
	msgBox.className = "";
}

function CheckItem(obj) {
	obj.parentNode.parentNode.className = "";
	var msgBox = obj.parentNode.getElementsByTagName("span")[0];
	var regPass = /^[a-zA-Z0-9]/;
	var regIdentity = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
	var regMobile = /^1\d{10}$/;
	var regQQ = /^\d{5,10}$/;
	switch (obj.name) {
	case "username":
		if (obj.value == "") {
			msgBox.innerHTML = "用户名不能为空，可以由任意字符组成";
			msgBox.className = "error";
			return false;
		} else {
			// 输入用户名后，判断用户是否存在
			return CheckUserExist();
		}
		break;
	case "password":
		if (obj.value == "" || regPass.test(obj.value) == false) {
			msgBox.innerHTML = "密码不能为空，并且不能含有非法字符";
			msgBox.className = "error";
			return false;
		}
		break;
	case "rePassword":
		if (obj.value == "") {
			msgBox.innerHTML = "确认密码不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("password").value) {
			msgBox.innerHTML = "两次输入的密码不相同";
			msgBox.className = "error";
			return false;
		}
		break;
	case "identity":
		if (obj.value == "" || regIdentity.test(obj.value) == false) {
			msgBox.innerHTML = "输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X";
			msgBox.className = "error";
			return false;
		}
		break;
	case "mobile":
		if (regMobile.test(obj.value) == false) {
			msgBox.innerHTML = "手机不能为空必须为11位并且只能是数字";
			msgBox.className = "error";
			return false;
		}
		break;
	case "qq":
		if (obj.value == "" || regQQ.test(obj.value) == false) {
			msgBox.innerHTML = "QQ号码只能由数字组成，长度为5到10位数字";
			msgBox.className = "error";
			return false;
		}
		break;
	case "veryCode":
		if (obj.value == "") {
			msgBox.innerHTML = "验证码不能为空";
			msgBox.className = "error";
			return false;
		} else {
			return checkValidateCode();
		}
		break;
	}
	return true;
}

// 提交表单验证
function checkForm(frm) {
	var els = frm.getElementsByTagName("input");
	for (var i = 0; i < els.length; i++) {
		if (!CheckItem(els[i]))
			return false;
	}
	return true;
}

// 判断用户是否存在
function CheckUserExist() {
	var username = document.getElementById("username");
	var flag = true
	var url = "checkName.action";
	var param = "username=" + username.value;
	$.post(url, param, function(data, status) {
		if (status = "success") {
			if (data.exist == "no") {
				var msgBox = document.getElementById("uname");
				msgBox.style.display = "inline";
				msgBox.innerHTML = "用户名已存在！";
				flag = false;
			}
		}
	}, "json");
	return flag;
}

// 验证验证码
function checkValidateCode() {
	var flag = true;
	/* 验证验证码的servlet类的路径 */
	var url = "CheckCodeServlet";
	var veryCode = $("[name=veryCode]").val();
	var param = "code=" + veryCode;
	$.ajax({
		url : url,// 请求的servlet地址
		type : "POST",// 请求方式
		async : false,
		data : param,// 发送到服务器的数据
		dataType : "text",// 设置返回数据类型
		success : function(data) {
			var msgBox = document.getElementById("Code");
			if (data == "false") {
				msgBox.style.display = "inline";
				msgBox.innerHTML = "验证码错误！";
				$("[name=veryCode]").val('');
				change();
				flag = false;
			} else {
				msgBox.style.display = "inline";
				msgBox.innerHTML = null;
			}
		},
		complete : function(XMLHttpRequest, statusText) {

		},// 响应完成后执行的回调方法
		error : function(XMLHttpRequest, statusText) {

		}// 响应失败后执行的回调方法
	})
	return flag
}

// 刷新验证码
function change() {
	var url = "regcode.jsp" + "?id=" + new Date().getTime();
	var $veryCode = $("#veryCode");
	$veryCode.attr("src", url);
}

// 自动生成年份下拉框
function insertYear() {
	// 获取年份下拉框
	var $year = $("#year");
	if ($year.children().size() <= 1) {
		for (var i = 1918; i < 2018; i++) {
			var $option = $("<option>" + i + "</option>")
			$year.append($option);
		}
	}
}
// 自动生成月份下拉框
function insertMonth() {
	// 获取月份下拉框
	var $month = $("#month");
	if ($month.children().size() <= 1) {
		for (var i = 2; i < 13; i++) {
			var $option = $("<option>" + i + "</option>")
			$month.append($option);
		}
	}
}
// 自动生成天数下拉框
function insertDay() {
	// 获取天数下拉框
	var $day = $("#day");
	if ($day.children().size() <= 1) {
		for (var i = 2; i < 32; i++) {
			var $option = $("<option>" + i + "</option>")
			$day.append($option);
		}
	}
}
// 充值金额
function ensure() {
	// 获取充值金额
	var $sum = $("#sum").val();
	location.href = "user_money.action?sum=" + $sum;
	var $divposit = $("#divposit").hide();
}
