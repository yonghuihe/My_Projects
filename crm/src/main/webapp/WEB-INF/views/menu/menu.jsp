<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menu管理</title>
<%@include file="/WEB-INF/views/common/common.jsp" %>
<script type="text/javascript" src="/static/js/menu/menu.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="menu_datagrid">
		<thead>
			<tr>
				<th data-options="field:'text',width:1,align:'center'">菜单名称</th>
				<th data-options="field:'url',width:1,align:'center'">url</th>
				<th data-options="field:'parent',width:1,align:'center',
					formatter:function(value,row,index){
						console.log(value)
						return value?value.text:'';
					}">父菜单</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="menu_dialog">
		<form id="menu_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>菜单名称</td>
				<td><input type="text" name="text"></td>
			</tr>
			<tr>
				<td>url</td>
				<td><input type="text" name="url"></td>
			</tr>
			<tr>
				<td>父菜单：</td>
				<td><input type="text" name="parent.id" class="easyui-combobox" placeholder="请选择父菜单"
					data-options="panelHeight:'auto',valueField:'id',textField:'text',url:'/menu_loadTree'">
				</td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="menu_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="menu_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>