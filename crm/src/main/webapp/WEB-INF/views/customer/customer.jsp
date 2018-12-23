<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>customer管理</title>
<%@include file="/WEB-INF/views/common/common.jsp" %>
<script type="text/javascript" src="/static/js/customer/customer.js"></script> 
</head>
<body>
	<!-- 数据表格 -->
	<table id="customer_datagrid">
		<thead>
			<tr>
				<th data-options="field:'id',width:1,align:'center'">编号</th>
				<th data-options="field:'name',width:1,align:'center'">客户姓名</th>
				<th data-options="field:'age',width:1,align:'center'">年龄</th>
				<th data-options="field:'gender',width:1,align:'center'">性别</th>
				<th data-options="field:'tel',width:1,align:'center'">电话号码</th>
				<th data-options="field:'email',width:1,align:'center'">邮箱</th>
				<th data-options="field:'qq',width:1,align:'center'">QQ</th>
				<th data-options="field:'wechat',width:1,align:'center'">微信</th>
				<th data-options="field:'job',width:1,align:'center'">职业</th>
				<th data-options="field:'salaryLevel',width:1,align:'center'">收入水平</th>
				<th data-options="field:'customerSource',width:1,align:'center'">客户来源</th>
				<th data-options="field:'inChargeUser',width:1,align:'center'">负责人</th>
				<th data-options="field:'inputUser',width:1,align:'center'">创建人</th>
				<th data-options="field:'inputTime',width:1,align:'center'">创建时间</th>
				<th data-options="field:'status',width:1,align:'center'">状态</th>
				<th data-options="field:'becomeTime',width:1,align:'center'">转正时间</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="customer_dialog">
		<form id="customer_form" method="post">
			<input type="hidden" name="id">
			<table align="center" style="margin-top: 15px;">
				<tr>
					<td>salaryLevel</td>
					<td><input type="text" name="salaryLevel"></td>
				</tr>
				<tr>
					<td>status</td>
					<td><input type="text" name="status"></td>
				</tr>
				<tr>
					<td>inputTime</td>
					<td><input type="text" name="inputTime"></td>
				</tr>
				<tr>
					<td>tel</td>
					<td><input type="text" name="tel"></td>
				</tr>
				<tr>
					<td>job</td>
					<td><input type="text" name="job"></td>
				</tr>
				<tr>
					<td>inChargeUser</td>
					<td><input type="text" name="inChargeUser"></td>
				</tr>
				<tr>
					<td>inputUser</td>
					<td><input type="text" name="inputUser"></td>
				</tr>
				<tr>
					<td>customerSource</td>
					<td><input type="text" name="customerSource"></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>name</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>age</td>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<td>gender</td>
					<td><input type="text" name="gender"></td>
				</tr>
				<tr>
					<td>wechat</td>
					<td><input type="text" name="wechat"></td>
				</tr>
				<tr>
					<td>qq</td>
					<td><input type="text" name="qq"></td>
				</tr>
				<tr>
					<td>becomeTime</td>
					<td><input type="text" name="becomeTime"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="customer_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="customer_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>