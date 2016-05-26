var pageii = null;
var grid = null;
$(function(){
	grid = lyGrid({
		id : 'paging',
		l_column : [{
			colkey : "id",
			name : "id",
			whidth : "50px",
			hide :true
		},{
			colkey : "dm",
			name : "字典"
		},{
			colkey : "dmms",
			name : "字典描述"
		},{
			colkey : "dmlx",
			name : "字典类型",
			isSort:true
		},{
			colkey : "lxms",
			name : "类型描述"
		}],
		jsonUrl :　rootPath + '/dd/findByPage.shtml',
		checkbox : true
	});
	
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	$("#addDd").click("click", function() {
		addDd();
	});
	$("#editDd").click("click", function() {
		editDd();
	});
	$("#delDd").click("click", function() {
		delDd();
	});
});
function addDd() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "60%" ],
		content : rootPath + '/dd/addUI.shtml'
	});
}
function editDd(){
	var cbox = grid.getSelectedCheckbox();
	if(cbox.length > 1 || cbox == ""){
		layer.msg("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "60%" ],
		content : rootPath + '/dd/editUI.shtml?id=' + cbox
	});
}
function delDd() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/dd/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}