<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userInfo管理</title>
<%@include file="/static/common/common.jsp" %>
<script type="text/javascript" src="/static/js/userInfo.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="userInfo_datagrid">
		<thead>
			<tr>
				<th data-options="field:'openid',width:1,align:'center'">openid</th>
				<th data-options="field:'sex',width:1,align:'center'">性别</th>
				<th data-options="field:'nickname',width:1,align:'center'">昵称</th>
				<th data-options="field:'privilege',width:1,align:'center'">特权信息</th>
				<th data-options="field:'province',width:1,align:'center'">省份</th>
				<th data-options="field:'headimgurl',width:1,align:'center'">头像</th>
				<th data-options="field:'country',width:1,align:'center'">国家</th>
				<th data-options="field:'unionid',width:1,align:'center'">unionid</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="userInfo_dialog">
		<form id="userInfo_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>性别</td>
				<td><input type="text" name="sex"></td>
			</tr>
			<tr>
				<td>昵称</td>
				<td><input type="text" name="nickname"></td>
			</tr>
			<tr>
				<td>特权信息</td>
				<td><input type="text" name="privilege"></td>
			</tr>
			<tr>
				<td>省份</td>
				<td><input type="text" name="province"></td>
			</tr>
			<tr>
				<td>唯一标识</td>
				<td><input type="text" name="openid"></td>
			</tr>
			<tr>
				<td>头像</td>
				<td><input type="text" name="headimgurl"></td>
			</tr>
			<tr>
				<td>国家</td>
				<td><input type="text" name="country"></td>
			</tr>
			<tr>
				<td>unionid</td>
				<td><input type="text" name="unionid"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="userInfo_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add2" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit2" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-delete1" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="userInfo_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>