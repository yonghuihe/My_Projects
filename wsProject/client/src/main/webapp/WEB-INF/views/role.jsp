<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp" %>
<title>Insert title here</title>
<script type="text/javascript" src="/js/role.js"></script>
</head>
<body>
	<table id="role_datagrid"></table>
	
	<div id="role_dialog">
		<form id="role_form" method="post" style="margin-top: 10px;margin-left: 5px">
			<input type="hidden" name="id"/>
			<table align="center">
				<tr>
					<td>角色编号:<input type="text" name="sn"/></td>
					<td>角色名称:<input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>
						<table id="allPermission"></table>
					</td>
					<td>
						<table id="selfPermission"></table>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	
	<div id="role_toolbar">
		<a class="easyui-linkbutton" iconCls="icon-add" plain=true data-cmd="add">新增</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain=true data-cmd="edit">编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain=true data-cmd="remove">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain=true data-cmd="reload">查询全部</a>
		<input type="text" name="keyword" />
		<a class="easyui-linkbutton" iconCls="icon-search" plain=true data-cmd="searchKey">搜索</a>
	</div>
	
	<div id="role_buttons">
		<a class="easyui-linkbutton" iconCls="icon-save" plain=true data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain=true data-cmd="cancel">取消</a>
	</div>
</body>
</html>