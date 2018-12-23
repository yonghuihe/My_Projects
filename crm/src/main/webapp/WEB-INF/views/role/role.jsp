<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<script type="text/javascript" src="/static/js/role/role.js"></script>
</head>
<body>
	<table id="role_dataGrid"></table>

	<div id="role_toolbar">
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="add">添加</a> <a
			class="easyui-linkbutton"
			data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		<a class="easyui-linkbutton" id="remove_btn"
			data-options="iconCls:'icon-remove',plain:true" data-cmd="remove">删除</a>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="searchAll">刷新</a>
	</div>

	<div id="role_dialog">
		<form id="role_form" action="role_form" method="post">
			<input type="hidden" name="id">
			<table align="center" style="margin-top: 20px">
				<tbody>
					<tr>
						<td>角色编号：<input type="text" name="sn"></td>
						<td>角色名称：<input type="text" name="name"></td>
					</tr>
					<tr>
						<td><table id="all_permission"></table></td>
						<td><table id="self_permission"></table></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id=loadAllPermissions>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:false"
			data-cmd="loadAllPermissions">加载所有权限</a>
	</div>
	<div id=removeSelfPermissions>
		<a class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:false"
			data-cmd="removeSelfPermissions">清空已有权限</a>
	</div>

	<div id="role_button">
		<a herf="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a herf="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>