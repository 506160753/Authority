var pageii = null;
var grid = null;
$(function() {
	//获取表数据 
	//表头
	grid = lyGrid({
		pagId : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
		}, {
			colkey : "userName",
			name : "用户名",
			isSort:true,
		}, {
			colkey : "accountName",
			name : "账号",
			isSort:true,
		}, {
			colkey : "roleName",
			name : "所属角色"
		}, {
			colkey : "locked",
			name : "账号状态",
			width : '90px',
			isSort:true
		}, {
			colkey : "description",
			name : "描述"
		}, {
			colkey : "createTime",
			name : "时间",
			isSort:true,
			renderData : function(rowindex,data, rowdata, column) {
				return new Date(data).format("yyyy-MM-dd hh:mm:ss");
			}
		}, {
			name : "操作",
			renderData : function( rowindex ,data, rowdata, colkeyn) {
				return "测试渲染函数";
			}
		} ],
		//方法
		jsonUrl : rootPath + '/user/findByPage.shtml',
		//选择框 头序
		checkbox : true,
		serNumber : true
	});
	//根据账号查询
	$("#search").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();// 初始化传参数
		grid.setOptions({
			data : searchParams
		});
	});
	//打开添加窗口
	$("#addAccount").click("click", function() {
		addAccount();
	});
	//打开修改窗口
	$("#editAccount").click("click", function() {
		editAccount();
	});
	//打开删除窗口
	$("#delAccount").click("click", function() {
		delAccount();
	});
	//打开权限分配窗口
	$("#permissions").click("click", function() {
		permissions();
	});
});
function editAccount() {
	//判断选中个数
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("只能选中一个");
		return;
	}
	//后台获取数据
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "90%" ],
		content : rootPath + '/user/editUI.shtml?id=' + cbox
	});
}
//后台打开新增界面
function addAccount() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/user/addUI.shtml'
	});
}
function delAccount() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.msg("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/user/deleteEntity.shtml';
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
function permissions() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.msg("请选择一个对象！");
		return;
	}
	var url = rootPath + '/resources/permissions.shtml?userId='+cbox;
	pageii = layer.open({
		title : "分配权限",
		type : 2,
		area : [ "700px", "80%" ],
		content : url
	});
}