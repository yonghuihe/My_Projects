<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售订单明细管理</title>
<%@ include file="/static/common/common.jsp" %>
<script type="text/javascript" src="/static/js/orderBillItem.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="ordBillItem_datagrid">
		<thead>
			<tr>
				<th data-options="field:'product',width:1,align:'center',formatter:function(value, row, index){
				return value? value.name:'';
				}">商品名称</th>
				<th data-options="field:'number',width:1,align:'center'">数量</th>
				<th data-options="field:'costPrice',width:1,align:'center'">成本价</th>
				<th data-options="field:'bill',width:1,align:'center',formatter:function(value, row, index){
				return value? value.sn:'';
				}">采购订单编号</th>
				<th data-options="field:'amount',width:1,align:'center'">金额小计</th>
				<th data-options="field:'remark',width:1,align:'center'">备注</th>
			</tr>
		</thead>
	</table>
	<!-- 新增编辑对话框 -->
	<div id="ordBillItem_dialog">
		<form id="ordBillItem_form" method="post">
		<table align="center" style="margin-top: 15px;">
			<input type="hidden" name="id">
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="product.id" class="easyui-combobox" placeholder="请选择商品"
			data-options="url:'/pro_list',valueField:'id',textField:'name',panelHeight:'auto'"></td>
			</tr>
			<tr>
				<td>数量</td>
				<td><input type="text" name="number" id="number" data-con="number" class="easyui-validatebox" data-options="required:true"></td>
			</tr>
			<tr>
				<td>成本价</td>
				<td><input type="text" name="costPrice" id="costprice" data-con="cost" class="easyui-validatebox" data-options="required:true"></td>
			</tr>
			<tr>
				<td>金额小计</td>
				<td><input type="text" id="amount" name="amount" disabled="disabled"></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><input type="text" name="remark"></td>
			</tr>
		</table>
		</form>
	</div>
	<!-- 数据表格CRUD按钮 -->
	<div id="ordBillItem_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add2" plain="true" data-cmd="add">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit2" plain="true" data-cmd="edit">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-delete1" plain="true" data-cmd="del">刪除</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
		</div>
	</div>
	<!-- 对话框保存取消按钮 -->
	<div id="ordBillItem_dialog_bt">
		<a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>