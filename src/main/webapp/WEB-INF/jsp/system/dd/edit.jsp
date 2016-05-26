<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/common.jspf" %>
<script type="text/javascript" src="${ctx}/js/system/dd/edit.js">
</script>
<style type="text/css">
.col-sm-3 {
	width: 15%;
	float: left;
}

.col-sm-9 {
	width: 85%;
	float: left;
}
label[class^="btn btn-default"]{
	margin-top: -4px;
}
</style>
</head>
<body>
	<div class="l_err" style="width: 100%; margin-top: 2px;"></div>
	<form id="form" name="form" class="form-horizontal" method="post"
		action="${ctx}/dd/editEntity.shtml">
		<input type="hidden" class="form-control checkdd" value="${dd.id}"
			name="ddFormMap.id" id="id">
		<section class="panel panel-default">
		<div class="panel-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">字典类型</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkdd"
						name="ddFormMap.dmlx" readonly="readonly" value="${dd.dmlx }" id="dmlx">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">类型描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkdd"
						name="ddFormMap.lxms" value="${dd.lxms }" id="lxms">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">字典</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkdd" readonly="readonly""
						name="ddFormMap.dm" value="${dd.dm }" id="dm">
				</div>
			</div>
			<div class="line line-dashed line-lg pull-in"></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">字典描述</label>
				<div class="col-sm-9">
					<input type="text" class="form-control checkdd" 
						name="ddFormMap.dmms" value="${dd.dmms }" id="dmms">
				</div>
			</div>
		</div>
		<footer class="panel-footer text-right bg-light lter">
			<button type="submit" class="btn btn-success btn-s-xs">提交</button>
		</footer> 
		</section>
	</form>
</body>
</html>