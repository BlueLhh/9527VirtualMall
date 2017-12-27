var $gamename = "";
$(function() {
	$("#gamename li").click(function() {
		$gamename = $(this).text(); // 获取点击li的值
	});
})
function findProdcut() {
	// 获取搜索框输入的值
	var $keyword = $("#keyword").val();
	// 获取选择的游戏名
	location.href = "prolist.action?gamename=" + $gamename + "&keyword=" + $keyword;
}
