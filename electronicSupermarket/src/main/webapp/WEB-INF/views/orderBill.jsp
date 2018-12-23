<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>orderBill管理</title>
<%@include file="/static/common/common.jsp" %>
<script type="text/javascript" src="/static/js/orderBill.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="orderBill_datagrid">
		<thead>
			<tr>

			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="orderBill_dialog">
		<form id="orderBill_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>订单编号:</td>
				<td><input type="text" name="sn"></td>
			</tr>
			<tr>
				<td>货品名称:</td>
		<%--		<td><input type="text" name="product.id" class="easyui-combobox"
						   data-options="url:'/product_list.do',valueField:'id',textField:'productName',panelHeight:'auto'">
				</td>--%>
				<td><input type="text" name="product.id" class="easyui-combobox"
						   data-options="url:'/product_list.do',valueField:'id',textField:'text',panelHeight:'auto'">
				</td>
			</tr>
			<tr>
				<td>单价:</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>数量:</td>
				<td><input type="text" name="totalNumber"></td>
			</tr>
			<tr>
				<td>订单总金额:</td>
				<td><input type="text" name="totalAmount"></td>
			</tr>
			<tr>
				<td>收货人:</td>
				<td><input type="text" name="user.id" class="easyui-combobox" data-options="url:'/userInfo_list.do',valueField:'id',textField:'nickname',panelHeight:'auto'">
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="orderBill_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add2" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit2" plain="true" data-cmd="edit">編輯</a>
			<a class="easyui-linkbutton" iconCls="icon-delete1" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="orderBill_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>