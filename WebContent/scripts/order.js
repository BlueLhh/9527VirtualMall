function subAccount(price) {
	// 输入商品的总数
	var $account = $("#account");
	// 输出商品总价的控件
	var $sum = $("#sum");
	if ($account.val() > 1) {
		$account.val($account.val() - 1);
		$sum.html($account.val() * price)
	}
}

function addAccount(price, stock) {
	// 输入商品的总数
	var $account = $("#account");
	// 输出商品总价的控件
	var $sum = $("#sum");
	if ($account.val() < stock) {
		var account = parseInt($account.val()) + parseInt(1);
		$account.val(account);
		$sum.html(account * price)
	}
}

// 判断用户的账户余额是否足够，购买的是否是自己的商品
function check(balance) {
	// 购买的商品总额
	var $sum = $("#sum").html();
	// 获取当前登录用户的id
	var $userid = $("#userid").val();
	// 获取当前商品出售人id
	var $prouid = $("#prouid").val();
	// 获取当前库存
	var $stock = parseInt($("#stock").html());
	// 输入商品的总数
	var $account = parseInt($("#account").val());
	if (balance > $sum && $userid != $prouid
			&& ($stock > $account || $stock == $account)) {
		return true;
	} else if ($stock < $account) {
		alert("商品库存不足！");
		return false;
	} else if ($userid == $prouid) {
		alert("您的不能购买自己出售的商品！");
		return false;
	} else if(balance < $sum){
		alert("您的余额不足，请充值！");
		return false;
	}
}


