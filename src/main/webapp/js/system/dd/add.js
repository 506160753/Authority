$(function() {
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form, {// 验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
							parent.layer.close(parent.pageii);
							return false;
						});
						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		rules : {
			"ddFormMap.dmlx" : {
				required : true
			},
			"ddFormMap.lxms" : {
				required : true
			},
			"ddFormMap.dm" : {
				required : true,
				remote : {
					type : "POST",
					url : 'isExist.shtml',
					data : {
						dm : function() {
							return $("#dm").val();
						},
						dmlx : function() {
							return $("#dmlx").val();
						}
					}
				}
			},
			"ddFormMap.dmms" : {
				required : true
			}
		},
		messages : {
			"ddFormMap.dmlx" : {
				required : "请输入字典类型"
			},
			"ddFormMap.lxms" : {
				required : "请输入类型描述"
			},
			"ddFormMap.dm" : {
				required : "请输入字典",
				remote : "该字典类型 字典重复"
			},
			"ddFormMap.dmms" : {
				required : "请输入字典描述"
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
});
