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
	switch (obj.name) {
	case "qq":
		if (obj.value == "") {
			msgBox.innerHTML = "游戏账号不能为空！";
			msgBox.className = "error";
			return false;
		}
		break;
	case "password":
		if (obj.value == "" || regPass.test(obj.value) == false) {
			msgBox.innerHTML = "密码不能为空！";
			msgBox.className = "error";
			return false;
		}
		break;
	case "repassword":
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
	case "rolename":
		if (obj.value == "" || regIdentity.test(obj.value) == false) {
			msgBox.innerHTML = "角色名不能为空！";
			msgBox.className = "error";
			return false;
		}
		break;
	case "rerolename":
		if (obj.value == "") {
			msgBox.innerHTML = "确认角色名不能为空";
			msgBox.className = "error";
			return false;
		} else if (obj.value != document.getElementById("rolename").value) {
			msgBox.innerHTML = "两次输入的用户名不相同";
			msgBox.className = "error";
			return false;
		}
		break;
	}
	return true;
}