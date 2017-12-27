//根据选择的游戏改变第二个游戏区下拉框属性
function changeRe() {
	// 获取选中的游戏
	var $game = $("#game option:checked").text();
	// 获取游戏区下拉框
	var $region = $("#region");
	$region.show();
	// 获取游戏服务器下拉框
	var $server = $("#server");
	$server.hide();
	// 获取道具类型下拉框
	var $proname = $("#proname");
	$proname.show();
	// 这样生成子菜单太麻烦，以后考虑从数据库读取实现
	if ($game == "地下城与勇士") {
		// 先移除之前的选择项
		$region.empty();
		// 创建游戏区选择下拉框子菜单
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "广西区" + "</option>");
		var $option3 = $("<option>" + "广东区" + "</option>");
		var $option4 = $("<option>" + "上海区" + "</option>");
		$region.append($option1);
		$region.append($option2);
		$region.append($option3);
		$region.append($option4);
		// 创建道具子选项
		// 先移除之前的选择项
		$proname.empty();
		var $option10 = $("<option>" + "-------请选择-------" + "</option>");
		var $option5 = $("<option>" + "游戏币" + "</option>");
		var $option6 = $("<option>" + "深渊票" + "</option>");
		var $option7 = $("<option>" + "账号" + "</option>");
		var $option8 = $("<option>" + "装备" + "</option>");
		var $option9 = $("<option>" + "道具" + "</option>");
		$proname.append($option10);
		$proname.append($option5);
		$proname.append($option6);
		$proname.append($option7);
		$proname.append($option8);
		$proname.append($option9);
	} else if ($game == "英雄联盟") {
		$region.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "艾欧尼亚" + "</option>");
		var $option3 = $("<option>" + "诺克萨斯" + "</option>");
		var $option4 = $("<option>" + "德玛西亚" + "</option>");
		$region.append($option1);
		$region.append($option2);
		$region.append($option3);
		$region.append($option4);
		// 创建道具子选项
		// 先移除之前的选择项
		$proname.empty();
		var $option10 = $("<option>" + "-------请选择-------" + "</option>");
		var $option5 = $("<option>" + "账号" + "</option>");
		var $option6 = $("<option>" + "代练" + "</option>");
		$proname.append($option10);
		$proname.append($option5);
		$proname.append($option6);
		$server.hide();
		$server.empty();
	} else if ($game == "王者农药") {
		$region.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "红莲斗篷" + "</option>");
		var $option3 = $("<option>" + "最终兵器" + "</option>");
		var $option4 = $("<option>" + "欲望之月" + "</option>");
		$region.append($option1);
		$region.append($option2);
		$region.append($option3);
		$region.append($option4);
		// 创建道具子选项
		// 先移除之前的选择项
		$proname.empty();
		var $option10 = $("<option>" + "-------请选择-------" + "</option>");
		var $option5 = $("<option>" + "账号" + "</option>");
		var $option6 = $("<option>" + "代练" + "</option>");
		$proname.append($option10);
		$proname.append($option5);
		$proname.append($option6);
		$server.hide();
		$server.empty();
	} else if ($game == "征途2") {
		$region.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "天下第一区" + "</option>");
		var $option3 = $("<option>" + "双线区" + "</option>");
		var $option4 = $("<option>" + "电信区" + "</option>");
		$region.append($option1);
		$region.append($option2);
		$region.append($option3);
		$region.append($option4);
		// 创建道具子选项
		// 先移除之前的选择项
		$proname.empty();
		var $option10 = $("<option>" + "-------请选择-------" + "</option>");
		var $option5 = $("<option>" + "游戏币" + "</option>");
		var $option6 = $("<option>" + "账号" + "</option>");
		var $option7 = $("<option>" + "玄兽" + "</option>");
		var $option8 = $("<option>" + "装备" + "</option>");
		var $option9 = $("<option>" + "其他" + "</option>");
		$proname.append($option10);
		$proname.append($option5);
		$proname.append($option6);
		$proname.append($option7);
		$proname.append($option8);
		$proname.append($option9);
		$server.hide();
		$server.empty();
	} else {
		//大区下拉框清空
		$region.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "其他游戏区" + "</option>");
		$region.append($option1);
		$region.append($option2);
		// 创建道具子选项
		// 先移除之前的选择项
		$proname.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		var $option2 = $("<option>" + "账号" + "</option>");
		var $option3 = $("<option>" + "激活码" + "</option>");
		$proname.append($option1);
		$proname.append($option2);
		$proname.append($option3);
		$server.hide();
		$server.empty();
	}
}

function changeSe() {
	// 获取选中的大区
	var $region = $("#region option:checked").text();
	// 获取游戏服务器下拉框
	var $server = $("#server");
	$server.show();
	if ($region == "广西区") {
		// 先移除之前的选择项
		$server.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		$server.append($option1);
		// 创建服务器选择下拉框子菜单
		for (var i = 1; i < 6; i++) {
			var $option = $("<option>" + "广西" + i + "区" + "</option>");
			$server.append($option);
		}
	} else if ($region == "广东区") {
		// 先移除之前的选择项
		$server.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		$server.append($option1);
		// 创建服务器选择下拉框子菜单
		for (var i = 1; i < 6; i++) {
			var $option = $("<option>" + "广东" + i + "区" + "</option>");
			$server.append($option);
		}
	} else if ($region == "上海区") {
		// 先移除之前的选择项
		$server.empty();
		var $option1 = $("<option>" + "-------请选择-------" + "</option>");
		$server.append($option1);
		// 创建服务器选择下拉框子菜单
		for (var i = 1; i < 6; i++) {
			var $option = $("<option>" + "上海" + i + "区" + "</option>");
			$server.append($option);
		}
	} else {
		$server.hide();
		$server.empty();
	}
}

function changePro() {
	// 获取选中的游戏道具类型
	var $proname = $("#proname option:checked").text();
	// 获取游戏金额控件
	var $golden = $("#golden");
	if ($proname == "游戏币") {
		$golden.show();
	} else {
		$golden.hide();
	}
}

