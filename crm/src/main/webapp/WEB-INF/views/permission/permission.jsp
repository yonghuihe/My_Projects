<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/static/js/permission/permission.js"></script>
</head>
<body>
	<table id="permission_dataGrid"></table>

	<div id="permission_toolbar" align="center">
		<input name="keyword"> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">关键字查询</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchAll">查询全部</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="searchPermissions">重新加载权限</a>

	</div>

</body>
</html>